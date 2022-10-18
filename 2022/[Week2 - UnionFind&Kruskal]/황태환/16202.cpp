#include <bits/stdc++.h>
using pii = std::pair<int, int>;

int N, M, K;
std::vector<std::pair<int, pii>> edge;
std::vector<int> parents, rank, res;

int find(int x)
{
    if(x == parents[x])
        return x;
    return parents[x] = find(parents[x]);
}

void _union(int u, int v)
{
    u = find(u);
    v = find(v);

    if(u == v)
        return;
    
    if(rank[u] > rank[v])
        std::swap(u, v);

    parents[u] = v;
    
    if(rank[u] == rank[v])
        rank[v]++;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M >> K;
    parents.resize(N + 1);  
    rank.resize(N + 1, 1);
    res.resize(K, 0);

    for(int i = 1; i <= N; i++)
        parents[i] = i;

    for(int i = 1, x, y; i <= M; i++)
    {
        std::cin >> x >> y;
        edge.push_back({i, {x, y}});
    }

    for(int i = 0; i < K; i++)
    {
        int cnt = 0, sum = 0;
        for(int j = 1; j <= N; j++)
            parents[j] = j;
        std::fill(rank.begin(), rank.end(), 1);

        for(int j = 0; j < edge.size(); j++)
        {
            int u = edge[j].second.first;
            int v = edge[j].second.second;
            int d = edge[j].first;

            if(find(u) == find(v))
                continue;
            
            cnt++;
            sum += d;
            _union(u, v);
            
            if(cnt == N - 1)
                break;
        }
        if(cnt != N - 1)
            break;
        
        edge.erase(edge.begin());
        res[i] = sum;
    }
    for(auto i : res)
        std::cout << i << ' ';
    std::cout << '\n';
    return 0;
}