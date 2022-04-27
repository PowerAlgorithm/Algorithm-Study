// 2 * 길 이동 비용 + u 도시 방문 비용 + v 도시 방문 비용
#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 1e4 + 1;
int N, P, parent[size], cost[size];
std::vector<std::pair<int, pii>> edge;

int find(int x)
{
    if(x == parent[x])
        return x;
    return parent[x] = find(parent[x]);
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::iota(parent, parent + size, 0);
    int res = INT_MAX;
    
    std::cin >> N >> P;
    for(int i = 1; i <= N; i++)
    {
        std::cin >> cost[i];
        res = std::min(res, cost[i]);
    }
    
    while(P--)
    {
        int s, e, l;
        std::cin >> s >> e >> l;
        edge.push_back({2 * l + cost[s] + cost[e], {s, e}});
    }

    std::sort(edge.begin(), edge.end());

    for(int i = 0; i < (int)edge.size(); i++)
    {
        int u = find(edge[i].second.first);
        int v = find(edge[i].second.second);
        int d = edge[i].first;

        if(u == v)
            continue;
        parent[std::max(u, v)] = std::min(u, v);
        res += d;
        if(++P == N - 2)
            break;
    }
    std::cout << res << '\n';
    return 0;
}