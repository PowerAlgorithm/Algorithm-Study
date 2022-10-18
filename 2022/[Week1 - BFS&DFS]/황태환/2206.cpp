#include <bits/stdc++.h>
using pii = std::pair<int, int>;
using piii = std::pair<pii, int>;

constexpr int size = 1e3 + 1;
int N, M;
int graph[size][size];
int visited[size][size][2];

const int dx[] = {1, 0, -1, 0};
const int dy[] = {0, 1, 0, -1};

// zero base
int BFS()
{
    std::queue<piii> q;
    q.push({{0, 0}, 1});
    visited[0][0][1] = 1;

    while(!q.empty())
    {
        int x = q.front().first.first;
        int y = q.front().first.second;
        int b = q.front().second;
        q.pop();

        if(x == N - 1 && y == M - 1)
            return visited[x][y][b];

        for(int i = 0; i < 4; i++)
        {
            int nX = x + dx[i];
            int nY = y + dy[i];

            if(0 <= nX && nX < N && 0 <= nY && nY < M)
            {
                if(graph[nX][nY] == 1 && b)
                {
                    visited[nX][nY][b-1] = visited[x][y][b] + 1;
                    q.push({{nX, nY}, b-1});
                }
                else if(graph[nX][nY] == 0 && visited[nX][nY][b] == 0)
                {
                    visited[nX][nY][b] = visited[x][y][b] + 1;
                    q.push({{nX, nY}, b});
                }
            }
        }
    }
    return -1;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M;
    
    for(int i = 0; i<N; i++)
    {
        std::string str;
        std::cin >> str;

        for(int j = 0; j < M; j++)    
            graph[i][j] = str[j] - '0';
    }

    std::cout << BFS() << '\n';
    return 0;   
}