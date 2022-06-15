#include <bits/stdc++.h>
#define INF 10000000
using namespace std;
using pii = std::pair<int, int>;

int K, V, E; //vertex, edges
int u, v, w; // 정점 u, v 가중치 w
vector<pii> graph[20001];
priority_queue<pii, vector<pii>, greater<pii>> q;
int dist[20001];

void dijkstra(int start)
{
    fill(&dist[0], &dist[20001], INF);
    q.push({0, start});
    dist[start] = 0;

    while (!q.empty())
    {
        int cost = q.top().first;
        int x = q.top().second;
        
        q.pop();
        if(dist[x] < cost)
            continue;
        for(auto i : graph[x]){
            int d = dist[x] + i.second;
            if (d <= dist[i.first]){
                q.push({d, i.first});
                dist[i.first] = d;
            }
        }
    }
}
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> V >> E;
    cin >> K;
    for (int i = 0; i < E;i++)
    {
        cin >> u >> v >> w;
        graph[u].push_back({v, w});
    }
    dijkstra(K);
    for (int i = 1; i <= V;i++){
        if(dist[i] == INF)
            cout << "INF\n";
        else
            cout << dist[i] << '\n';
    }
    return 0;
}