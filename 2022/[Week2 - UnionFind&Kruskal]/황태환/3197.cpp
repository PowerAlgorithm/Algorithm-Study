#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 1500;
int R, C;
char map[size][size];
int parents[size * size];
int swan[2];

const int dx[] = {-1, 1, 0, 0};
const int dy[] = {0, 0, -1, 1};

std::queue<pii> Q;

int find(int x)
{
    if(x == parents[x])
        return x;
    return parents[x] = find(parents[x]);
}

void water(int y, int x)
{
    Q.push({y, x});
    int u = find(y * C + x);
    for(int i = 0; i < 4; i++)
    {
        int nX = x + dx[i];
        int nY = y + dy[i];

        if(nX < 0 || nX >= C || nY < 0 || nY >= R)
            continue;
        if(map[nY][nX] == '.')
        {
            int v = find(nY * C + nX); 
            parents[v] = u;
        }
    }
}

void melt()
{
    int sz = Q.size();
    for(int i = 0; i < sz; i++)
    {
        int y = Q.front().first;
        int x = Q.front().second;
        Q.pop();

        for(int i = 0; i < 4; i++)
        {
            int nY = y + dy[i];
            int nX = x + dx[i];

            if(nX < 0 || nX >= C || nY < 0 || nY >= R)
                continue;
            if(map[nY][nX] == 'X') 
            {
                map[nY][nX] = '.';
                water(nY, nX);
            }
        }
    }
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> R >> C;
    int area = R * C;
    std::iota(parents, parents + area, 0);

    int cnt = 0;
    for(int i = 0; i < R; i++)
    {
        for(int j = 0; j < C; j++)
        {
            std::cin >> map[i][j];
            if(map[i][j] == 'L')
            {
                swan[cnt++] = i * C + j;
                map[i][j] = '.'; 
            }
            if(map[i][j] == '.')
                water(i, j);
        }
    }
    int day = 0;
    while(!Q.empty())
    {
        if(find(swan[0]) == find(swan[1]))
            break;
        melt();
        day++;
    }
    std::cout << day << '\n';
    return 0;
}