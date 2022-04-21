#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 3e2 + 1;
int N, M;
int board[size][size], tmp[size][size];
bool visited[size][size];

const int dx[] = {0, 1, 0, -1};
const int dy[] = {1, 0, -1, 0};

void bfs(int x, int y)
{
    std::queue<pii> q;
    q.push({x, y});

    while(!q.empty())
    {
        x = q.front().first;
        y = q.front().second;
        q.pop();

        for(int i = 0; i < 4; i++)
        {
            int nX = x + dx[i];
            int nY = y + dy[i];

            if(nX >= 0 && nX < N && nY >= 0 && nY < M)
            {
                if(board[nX][nY] != 0 && !visited[nX][nY])
                {
                    q.push({nX, nY});
                    visited[nX][nY] = true;
                }
            }
        }
    }
}

void melting()
{
    memset(tmp, 0, sizeof(tmp));

    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < M; j++)
        {
            if(board[i][j] == 0) // 이미 녹아 없어진 자리라면    
                continue; // 무시

            int cnt = 0;
            //0이 아닌 칸의 인접한 칸에서
            for(int k = 0; k < 4; k++)
            {
                int nX = i + dx[k];
                int nY = j + dy[k];
                if(nX < 0 || nX >= N || nY < 0 || nY >= M)
                    continue;
                if(board[nX][nY] == 0) // 주변에 0인 칸이 있으면
                    cnt++; // 그 개수를 세어주고
            }
            tmp[i][j] = (board[i][j] - cnt > 0 ? board[i][j] - cnt : 0); // 양수면 해당 값으로, 음수면 0으로
        }
    }

    // 갱신
    for(int i = 0; i < N; i++)
        for(int j = 0; j < M; j++)
            board[i][j] = tmp[i][j];
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M;
    for(int i = 0; i < N; i++)
        for(int j = 0; j < M; j++)
            std::cin >> board[i][j];
    
    int time = 0;
    while(true)
    {
        int cnt = 0; // 빙산의 개수 bfs를 호출한 횟수
        for(int i = 0; i < N; i++)
        {
            for(int j = 0; j < M; j++)
            {
                if(!visited[i][j] && board[i][j] != 0) // 방문하지 않았으면서 0이 아닌 경우
                {    
                    bfs(i, j);
                    cnt++;
                }
            }
        }

        if(cnt == 0) // 빙산이 없다면 = 다 녹아버렸다면
        {
            std::cout << 0 << '\n';
            return 0;
        }
        else if(cnt >= 2) // 두 덩이 이상이라면
        {
            std::cout << time << '\n';
            return 0;
        }
        time++;
        melting(); // 빙산 녹이기
        memset(visited, false, sizeof(visited));
    }
    return 0;
}