#include <queue>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int graph[20][20];
int resSec = 0;
int size = 2;
int tx, ty;
int N;
int eat = 2;
int direction[4][2] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
vector<pair<int, pair<int, int> > > shark;

void bfs(int x, int y)
{
    shark.clear();
    queue<pair<pair<int, int>, int> > q;
    bool visited[20][20] = {false};
    int minD = 1000;

    q.push({{x, y}, 0});
    visited[x][y] = true;
    while (!q.empty())
    {
        int rx = q.front().first.first, ry = q.front().first.second;
        int d = q.front().second;
        int dist;
        q.pop();
        for (int i = 0; i < 4; i++)
        {
            int dx = rx + direction[i][0];
            int dy = ry + direction[i][1];
            if (dx < 0 || dx >= N || dy < 0 || dy >= N)
                continue;
            if (size < graph[dx][dy] || visited[dx][dy])
                continue;
            // shark.push_back({{dx, dy}, d + 1});
            dist = d + 1;
            q.push({{dx, dy}, dist});
            visited[dx][dy] = true;
            if (graph[dx][dy] > 0 && size > graph[dx][dy])
            {
                if (minD >= dist)
                {
                    minD = dist;
                    shark.push_back({minD, {dx, dy}});
                }
            }
        }
    }
}

int main()
{
    cin >> N;
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            cin >> graph[i][j];
            if (graph[i][j] == 9)
            {
                tx = i;
                ty = j;
                graph[i][j] = 0;
            }
        }
    }
    while (1)
    {
        bfs(tx, ty);
        if (shark.size() == 0)
        {
            break;
        }
        else
        {
            sort(shark.begin(), shark.end());
            resSec += shark[0].first;
            eat -= 1;

            if (eat == 0)
            {
                size += 1;
                eat = size;
            }

            tx = shark[0].second.first;
            ty = shark[0].second.second;
            graph[tx][ty] = 0;
        }
    }
    cout << resSec << '\n';
    return 0;
}