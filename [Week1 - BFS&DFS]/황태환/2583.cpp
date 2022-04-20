#include <bits/stdc++.h>

constexpr int size = 1e2 + 1;
int M, N, K, cnt;
int board[size][size];
bool visited[size][size];

const int dx[] = {1, -1, 0, 0};
const int dy[] = {0, 0, 1, -1};

void dfs(int y, int x)
{
    visited[y][x] = true; // 방문
    cnt++; // 넓이 1씩 증가

    for(int i = 0; i < 4; i++)
    {
        // 신규 좌표
        int nY = y + dy[i];
        int nX = x + dx[i];

        if(nY >= 0 && nY < M && nX >= 0 && nX < N) // OOB 체크
            if(!board[nY][nX] && !visited[nY][nX]) // 0이면서 방문하지 않았다면
                dfs(nY, nX); // 새로운 좌표를 기준으로 다시 dfs 수행
    }
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> M >> N >> K;

    for(int i = 0, x1, y1, x2, y2; i < K; i++)
    {
        std::cin >> x1 >> y1 >> x2 >> y2; 
        // 영역을 1로 색칠해줌
        for(int y = y1; y < y2; y++)
            for(int x = x1; x < x2; x++)
                board[y][x] = 1;
    }

    std::vector<int> res;

    for(int y = 0; y < M; y++)
    {
        for(int x = 0; x < N; x++)
        {
            if(!board[y][x] && !visited[y][x])
            {
                cnt = 0; // 한 번 돌때마다 영역의 넓이 초기화
                dfs(y, x);
                res.push_back(cnt); // res에 영역의 넓이 넣어줌
            }
        }
    }
    std::sort(res.begin(), res.end()); // 결과값들 오름차순으로 정렬

    std::cout << (int)res.size() << '\n';
    for(auto v : res)
        std::cout << v << " ";
    std::cout << '\n';
    return 0;
}