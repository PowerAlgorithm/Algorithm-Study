#include <bits/stdc++.h>
using ll = long long;
using pii = std::pair<int, int>;

constexpr int size = 1e5 + 1;
int N, M, Q;
int parents[size], seq[size];

bool chk[size];
pii graph[size];

int find(int x)
{
    if(parents[x] < 0)
        return x;
    return parents[x] = find(parents[x]);
}

void _union(int a, int b)
{
    a = find(a);
    b = find(b);

    if(a == b)
        return;

    parents[a] += parents[b];
    parents[b] = a;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M >> Q;
    memset(parents, -1, sizeof(parents));

    for(int i = 1, u, v; i <= M; i++)
    {
        std::cin >> u >> v;
        graph[i] = {u, v};
        chk[i] = true;
    }

    for(int i = 1; i <= Q; i++)
    {
        std::cin >> seq[i];
        chk[seq[i]] = false;
    }

    for(int i = 1; i <= M; i++)
        if(chk[i])
            _union(graph[i].first , graph[i].second);

    ll res = 0;
    for(int i = Q; i >= 1; i--)
    {
        int tmp = seq[i];

        int a = find(graph[tmp].first);
        int b = find(graph[tmp].second);

        if(a != b)
            res += (parents[a] * parents[b]);
        
        _union(a, b);
    }
    std::cout << res << '\n';
    return 0;
}