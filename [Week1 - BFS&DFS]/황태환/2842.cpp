#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 51;
int N, low, high;
pii start;
int height[size][size];
bool visited[size][size];
std::vector<pii> house;

const int dx[] = {0, 0, 1, -1, 1, 1, -1, -1};
const int dy[] = {1, -1, 0, 0, 1, -1, 1, -1};

void dfs(int x, int y)
{
    if(x < 0 || x >= N || y < 0 || y >= N || visited[x][y] || height[x][y] < low || height[x][y] > high)
        return;
    visited[x][y] = true;
    for(int i = 0; i < 8; i++)
        dfs(x + dx[i], y + dy[i]);
}

bool chk()
{
    int cnt = 0;
    for(int i = 0; i < (int)house.size(); i++)
        if(visited[house[i].first][house[i].second]) // 방문된 집의 개수를 세어줌
            cnt++;
    return cnt == (int)house.size();
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N;
    char ch;
    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < N; j++)
        {
            std::cin >> ch;
            if(ch == 'P') // 우체국
                start = {i, j};
            else if(ch == 'K') // 집
                house.push_back({i, j}); // 집의 좌표를 저장해줌
        }
    }
    std::set<int> h;
    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < N; j++)
        {
            std::cin >> height[i][j];
            h.insert(height[i][j]);
        }
    }

    int res = INT_MAX;
    auto left = h.begin(), right = h.begin(); // two pointer
    while(right != h.end())
    {
        while(left != h.end())
        {
            memset(visited, false, sizeof(visited));
            low = *left, high = *right;
            dfs(start.first, start.second);
            if(!chk())
                break;
            
            res = std::min(res, *right - *left);
            ++left;
        }
        ++right;
    }
    std::cout << res << '\n';
    return 0;
}