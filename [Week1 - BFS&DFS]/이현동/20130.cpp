#include <bits/stdc++.h>
using namespace std;

int N, M;
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};
int startX, startY;
string graph[201];
bool visited[201][201];
bool alphaVisited[26] = {false};
vector<pair<int, int> > ans;

bool checkRange(int rx, int ry)
{
    if ((rx >= 0 && rx < N) && (ry >= 0 && ry < M))
        return true;
    return false;
}

void bfs(int x, int y)
{
    deque<pair<int, int> > q; //탐색하는 위치 정보
    map<char, queue<pair<int, int> > > doorq;
    q.push_back(make_pair(x, y));
    fill(&visited[0][0], &visited[N][M], false);
    visited[x][y] = true;
    ans.push_back(make_pair(x, y));

    while (!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        q.pop_front();
        for (int i = 0; i < 4; i++)
        {
            int rx = x + dx[i];
            int ry = y + dy[i];

            if (checkRange(rx, ry))
            {
                if (graph[rx][ry] == '!')
                {
                    ans.push_back(make_pair(rx, ry));
                    return;
                }
                if (graph[rx][ry] == '#' || visited[rx][ry])
                    continue;

                // 자물쇠가 있는 경우, 자물쇠 큐에 넣어둔다. 즉, 열쇠를 획득하면 바로 문을 doorq에 넣어둔다.
                //대문자
                if (('A' <= graph[rx][ry]) && ('Z' >= graph[rx][ry]))
                {
                    int tmp = graph[rx][ry] - 'A';
                    if (!alphaVisited[tmp])
                    {
                        doorq[tmp].push(make_pair(rx, ry));
                        continue;
                    }
                }
                visited[rx][ry] = true;
                if (find(ans.begin(), ans.end(), make_pair(rx, ry)) == ans.end())
                    ans.push_back(make_pair(rx, ry));
                q.push_back(make_pair(rx, ry));
                //열쇠 아이템이 있는 경우, 열쇠 획득
                if (('a' <= graph[rx][ry]) && ('z' >= graph[rx][ry]))
                {
                    int tmp = graph[rx][ry] - 'a';
                    alphaVisited[tmp] = true;
                    while (!doorq[tmp].empty())
                    {
                        int nx, ny;
                        nx = doorq[tmp].front().first;
                        ny = doorq[tmp].front().second;
                        doorq[tmp].pop();
                        if (!visited[nx][ny])
                        {
                            visited[nx][ny] = true;
                            ans.push_back({nx, ny});
                            q.push_back(make_pair(nx, ny));
                        }
                    }
                }
            }
        }
    }
}

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> M;
    for (int i = 0; i < N; i++)
    {
        cin >> graph[i];
        for (int j = 0; j < M; j++)
        {
            if (graph[i][j] == '@')
            {
                startX = i;
                startY = j;
            }
        }
    }
    bfs(startX, startY);

    cout << ans.size() << '\n';
    for (auto a : ans)
        cout << a.first + 1 << ' ' << a.second + 1 << '\n';

    return 0;
}

// // 7 7
// // #######
// // #a***!#
// // #*E*FG#
// // #*D***#
// // #*C***#
// // #1B***#
// // #######

// // 6, 2
// // 5, 2
// // 4, 2
// // 3, 2
// // 2, 2 a
// // 2, 3
// // 2, 4
// // 2, 5
