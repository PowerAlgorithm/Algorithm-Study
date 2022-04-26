#include <bits/stdc++.h>

constexpr int INF = 0x3f3f3f3f;
constexpr int size = 1e2 + 1;
int N, M, cnt, parent[size], dist[size][size], res[size], sz[size];

int find(int x)
{
    if(parent[x] < 0)
        return x;
    return parent[x] = find(parent[x]);
}

void _merge(int a, int b)
{
    a = find(a), b = find(b);
    if(a == b) 
        return;
    if(parent[a] < parent[b])
        parent[a] += parent[b], parent[b] = a;
    else
        parent[b] += parent[a], parent[a] = b;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    memset(parent, -1, sizeof(parent));
    std::cin >> N >> M;
    for(int i = 0; i < size; i++)
        for(int j = 0; j < size; j++)
            dist[i][j] = (i == j ? 0 : INF);
    
    for(int i = 0, a, b; i < M; i++)
    {
        std::cin >> a >> b;
        --a, --b;
        dist[a][b] = dist[b][a] = 1;
        _merge(a, b);
    }

    for(int i = 0; i < N; i++)
        if(parent[i] < 0)
            parent[i] = -(++cnt);
    
    for(int k = 0; k < N; k++)
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                dist[i][j] = std::min(dist[i][j], dist[i][k] + dist[k][j]);
    
    memset(sz, INF, sizeof(sz));

    for(int i = 0; i < N; i++)
    {
        int tmp = 0;
        for(int j = 0; j < N; j++)
            if(dist[i][j] != INF)
                tmp = std::max(tmp, dist[i][j]);
        int gp = -parent[find(i)] - 1;
        if(sz[gp] > tmp)
        {
            sz[gp] = tmp;
            res[gp] = i + 1;
        }
    }

    std::sort(res, res + cnt);
    std::cout << cnt << '\n';
    for(int i = 0; i < cnt; i++)
        std::cout << res[i] << '\n';
    return 0;
}