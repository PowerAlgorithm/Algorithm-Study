#include <bits/stdc++.h>

constexpr int size = 1e5 + 1;
int G, P, parent[size];

int find(int x)
{
    if(x == parent[x])
        return x;
    return parent[x] = find(parent[x]);
}

void _merge(int a, int b)
{
    a = find(a), b = find(b);
    parent[a] = b;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::iota(parent, parent + size, 0);
    std::cin >> G >> P;

    int cnt = 0;
    for(int i = 0, x; i < P; i++)
    {
        std::cin >> x;
        int root = find(x);

        if(root != 0) // 도킹이 가능하다면
        {
            _merge(root, root - 1); // 도킹한 게이트 - 1과 merge
            cnt++;
        }
        else // 도킹할 게이트 없음
            break;
    }
    std::cout << cnt << '\n';
    return 0;
}