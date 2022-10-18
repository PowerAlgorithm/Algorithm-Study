// 옮길 수 있는 물건의 중량 = 이어진 다리의 가중치 중 최솟값
// 가중치가 큰 간선을 이어주면 됨
#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 1e4 + 1;
int N, M;
int parent[size];

int find(int x)
{
    if(x == parent[x])
        return x;
    return parent[x] = find(parent[x]);
}

void _merge(int a, int b)
{
    a = find(a), b = find(b);
    if(a ^ b) 
        parent[a] = b;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::iota(parent, parent + size, 0);
    std::cin >> N >> M;
    std::vector<std::pair<int, pii>> v(M);

    for(int i = 0, A, B, C; i < M; i++)
    {
        std::cin >> A >> B >> C;
        v[i] = {C, {A, B}};
    }
    std::sort(v.rbegin(), v.rend()); // 중량 기준으로 내림차순

    int x, y;
    std::cin >> x >> y;
    for(auto p : v)
    {
        _merge(p.second.first, p.second.second);
        if(find(x) == find(y))
        {
            std::cout << p.first << '\n';
            return 0;
        }
    }
    return 0;
}