// 최대 비용 = 모든 거리에 불을 다 켰을 때 드는 비용
// 최소 배용 = 최소 신장 트리를 구현하는데 드는 비용 
// 절약할 수 있는 최대 비용 = 
// 모든 거리에 불을 다 켰을 때 드는 비용 - 최소 신장 트리를 구현하는데 드는 비용
// 크루스칼 알고리즘 연습 문제
#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 2e5 + 1;
int m, n, parent[size];
std::vector<std::pair<int, pii>> edge;

int find(int x)
{
    if(x == parent[x])
        return x;
    return parent[x] = find(parent[x]);
}

void _merge(int a, int b)
{
    a = find(a), b = find(b);
    if(a < b) parent[b] = a;
    else parent[a] = b;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    while(true)
    {   
        // Init
        edge.clear();
        std::iota(parent, parent + size, 0);

        std::cin >> m >> n;
        if(!m && !n)
            break;

        int total = 0;
        for(int i = 0, x, y, z; i < n; i++)
        {
            std::cin >> x >> y >> z;
            edge.push_back({z, {x, y}});
            total += z;
        }

        std::sort(edge.begin(), edge.end());
        int cost = 0;
        for(int i = 0; i < n; i++)
        {
            int x = edge[i].second.first;
            int y = edge[i].second.second;
            int d = edge[i].first;
            if(find(x) == find(y))
                continue;
            _merge(x, y);
            cost += d;
        }
        
        std::cout << total - cost << '\n';
    }
    return 0;
}