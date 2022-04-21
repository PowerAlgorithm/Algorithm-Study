#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 51;
int N, house, height[size][size];
pii start;
char board[size][size];
const int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
const int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N;

    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < N; j++)
        {
            std::cin >> board[i][j];
            if(board[i][j] == 'P') // 우체국 위치 = 시작 위치
                start = {i, j};
            else if(board[i][j] == 'K')
                house++;
        }
    }
    std::vector<int> v;
    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < N; j++)
        {
            std::cin >> height[i][j];
            v.push_back(height[i][j]);
        }
    }
    std::sort(v.begin(), v.end()); // 정렬
    v.erase(std::unique(v.begin(), v.end()), v.end()); // 중복 제거

    int res = INT_MAX;
    int left = 0, right = 0; // 투 포인터
    while(left < (int)v.size())
    {
        int houseleft = house;
        std::vector<std::vector<bool>> visited(N, std::vector<bool>(N));
        std::queue<pii> q;

        if(height[start.first][start.second] >= v[left] && height[start.first][start.second] <= v[right])
        {
            q.push(start);
            visited[start.first][start.second] = true; // 시작 정점 방문 표시
        }

        while(!q.empty())
        {
            int x = q.front().first;
            int y = q.front().second;
            q.pop();

            if(board[x][y] == 'K') // 집이라면
                houseleft--;    // 남은 집 수 감소
            
            for(int i = 0; i < 8; i++)
            {
                int nX = x + dx[i];
                int nY = y + dy[i];
                if(nX < 0|| nY < 0 || nX >= N || nY >= N || visited[nX][nY])    // OOB 및 이미 방문
                    continue;
                if(height[nX][nY] >= v[left] && height[nX][nY] <= v[right])
                {
                    visited[nX][nY] = true;
                    q.push({nX, nY}); // 방문할 수 있는 정점으로 넣어줌
                }
            }
        }

        if(houseleft == 0) // 모든 집을 방문한 경우
        {
            res = std::min(res, v[right] - v[left]); // 결과값 갱신 : 현재 포인터가 가리키는 값의 차를 결과값으로
            left++; // 탐색이 가능하니 왼쪽 포인터를 옮기고
        }
        else if(right + 1 < (int)v.size())
            right++; // 탐색이 불가능하다면 오른쪽 포인터를 옮김
        else
            break;
    }
    std::cout << res << '\n';
    return 0;
}