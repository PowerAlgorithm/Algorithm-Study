#include <bits/stdc++.h>

constexpr int size = 1e5 + 1;
int N, M, R, cnt;
std::vector<int> adj[size];
int visited[size];

void dfs(int curr)
{
    visited[curr] = ++cnt;
    for(auto nxt : adj[curr])
        if(!visited[nxt])
            dfs(nxt);
}

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
    dfs(R);
    for(int i = 1; i <= N; i++)
        std::cout << visited[i] << '\n';
    return 0;
}