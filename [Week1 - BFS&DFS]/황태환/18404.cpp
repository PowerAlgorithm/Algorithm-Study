#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 5e2 + 1;
int N, M;
pii start; // 시작 좌표 
int board[size][size];

// 8 Way
const int dx[] = {-2, -2, -1, -1, 1, 1, 2, 2};
const int dy[] = {-1, 1, -2, 2, -2, 2, -1, 1};

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M >> start.first >> start.second;
    start.first--, start.second--; // zero base
    for(int i = 0; i < N; i++)
        memset(board[i], -1, sizeof(int) * N); // 배열의 모든 값을 -1로 초기화

    // BFS
    board[start.first][start.second] = 0;
    std::queue<pii> q;
    q.push(start); // 시작 정점 큐에 넣어줌

    while(!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        for(int i = 0; i < 8; i++)
        {
            int nX = x + dx[i];
            int nY = y + dy[i];

            if(nX < 0 || nX >= N || nY < 0 || nY >= N) // OOB
                continue;
            if(board[nX][nY] == -1)
            {
                board[nX][nY] = board[x][y] + 1; // 다음에 갈 수 있는 칸은 이전 칸의 수 + 1
                q.push({nX, nY}); // 갈 수 있으니 큐에 넣어줌
            }
        }
    }
    for(int i = 0, A, B; i < M; i++) // 쿼리 계산해줌
    {
        std::cin >> A >> B;
        std::cout << board[A - 1][B - 1] << ' ';
    }
    std::cout << '\n';
    return 0;
}