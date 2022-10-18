#include <bits/stdc++.h>
using namespace std;

int N, M;
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};
int year = 0;
int graph[301][301];
bool visited[301][301];

// 덩어리 세는 함수 dfs
// 얼음이 녹는것 ice 정보를 저장하여 q에 저장하고 bfs식으로 녹인다.
bool checkZone(int rx, int ry)
{
    if (rx >= N || ry >= M || rx < 0 || ry < 0)
        return false;
    return true;
}

void dfs(int x, int y)
{
    for (int i = 0; i < 4; i++)
    {
        int rx, ry;
        rx = x + dx[i];
        ry = y + dy[i];
        if (checkZone(rx, ry) && (!visited[rx][ry]) && (graph[rx][ry] != 0))
        {
            visited[rx][ry] = true;
            dfs(rx, ry);
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> M;

    deque<pair<int, int> > dq;

    // input graph
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            cin >> graph[i][j];
            if (graph[i][j] > 0)
                dq.push_back(make_pair(i, j));
        }
    }

    while (1)
    {
        int cnt = 0;       // 빙산의 갯수
        int tmp[301][301]; // 빙산 표시
        // 빙산 세기
        fill(&visited[0][0], &visited[N][M], false);
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < M; j++)
            {
                if ((!visited[i][j]) && (graph[i][j] != 0))
                {
                    dfs(i, j);
                    cnt++;
                }
            }
        }

        //빙산이 2개로 분리 되거나 다 녹을때까지 분리 x
        if (cnt >= 2 or cnt == 0)
        {
            if (cnt == 0) // 다 녹을 때 까지 분리x
                year = 0;
            break;
        }
        //빙산 녹이기

        copy(&graph[0][0], &graph[N][M], &tmp[0][0]);
        int size = dq.size();
        for (int i = 0; i < size; i++)
        {
            int x, y;
            x = dq.front().first;
            y = dq.front().second;
            dq.pop_front();
            for (int d = 0; d < 4; d++)
            {
                int tx, ty;
                tx = x + dx[d];
                ty = y + dy[d];
                if ((checkZone(tx, ty)) && (graph[tx][ty] == 0))
                    --tmp[x][y];
            }

            if (tmp[x][y] > 0)
                dq.push_back(make_pair(x, y));
            else
                tmp[x][y] = 0;
        }
        copy(&tmp[0][0], &tmp[N][M], &graph[0][0]);

        year++;
    }
    cout << year << '\n';

    return 0;
}