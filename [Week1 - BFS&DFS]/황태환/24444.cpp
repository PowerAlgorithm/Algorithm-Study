#include <bits/stdc++.h>

constexpr int size = 1e5 + 1; 
int N, M, R;
std::vector<int> adj[size];
int visited[size];

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M >> R;
    
    for(int i = 0, u, v; i < M; i++)
    {
        std::cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    for(int i = 1; i <= N; i++)
        std::sort(adj[i].begin(), adj[i].end());

    std::queue<int> q;
    q.push(R); // 시작 정점
    visited[R] = 1;
    int order = 2; // 시작 정점은 1로 이미 방문해서 그 다음 방문순서는 2부터 시작함

    while(!q.empty())
    {
        auto curr = q.front();
        q.pop();
        
        for(auto nxt : adj[curr])
        {
            if(!visited[nxt])
            {
                visited[nxt] = order++;
                // visited[nxt] = order;
                // order++
                // 랑 같은 의미
                q.push(nxt);
            }
        }
    }
    for(int i = 1; i <= N; i++)
        std::cout << visited[i] << '\n';
    return 0;
}