#include <bits/stdc++.h>
using namespace std;

vector<int> graph[100001]; //인접리스트
int visited[100001] = {0};
int N, M, R; // 노드, 간선, 시작정점
int cnt = 0;

void bfs(int start)
{
    deque<int> dq;
    dq.push_back(start);
    visited[start] = ++cnt;
    while (!dq.empty())
    {
        int x = dq.front();
        dq.pop_front();
        for (int i = 0; i < graph[x].size(); i++)
        {
            if (!visited[graph[x][i]])
            {
                dq.push_back(graph[x][i]);
                visited[graph[x][i]] = ++cnt;
            }
        }
    }
}
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> M >> R;

    for (int i = 0; i < M; i++)
    {
        int a, b;
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    for (int i = 1; i < N + 1; i++)
        sort(graph[i].begin(), graph[i].end());

    bfs(R);

    for (int i = 1; i < N + 1; i++)
        cout << visited[i] << '\n';
    return 0;
}