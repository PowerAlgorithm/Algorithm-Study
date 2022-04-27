#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 1e3 + 1;
int N, M, parent[size];
std::vector<std::pair<int, pii>> edge;

int find(int x)
{
    if(x == parent[x])
        return x;
    return parent[x] = find(parent[x]);
}

bool _merge(int a, int b)
{
    a = find(a);
    b = find(b);

    if(a == b)
        return false;
    parent[a] = b;
    return true;
}

int kruskal()
{
    std::iota(parent, parent + size, 0); // 편의를 위해 kruskal 내에서 parent 배열 초기화
    int cnt = 0, k = 0; // 간선의 개수, 오르막길의 개수
    for(int i = 0; i < (int)edge.size(); i++)
    {
        int x = edge[i].second.first;
        int y = edge[i].second.second;
        int d = edge[i].first;

        if(_merge(x, y))
        {
            if(!d) // 오르막길일 때
                ++k;
            if(++cnt == N)
                break;
        }
    }
    return k * k; // 오르막길 = k * k;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M;
    for(int i = 0, A, B, C; i <= M; i++) // M + 1 개의 입력
    {
        std::cin >> A >> B >> C;
        edge.push_back({C, {A, B}});
    }

    std::sort(edge.begin(), edge.end()); // 오르막길이 우선되게.. -> 최악의 경우
    int worst = kruskal();

    std::sort(edge.rbegin(), edge.rend()); // 내리막길이 우선되게... -> 최선의 경우
    int best = kruskal();

    std::cout << worst - best << '\n';
    return 0;
}