#include <bits/stdc++.h>

constexpr int size = 3e3 + 1;
int V, E, cnt, parent[size], arr[size];

int find(int x)
{
    if(x == parent[x])
        return x;
    return parent[x] = find(parent[x]);
}

bool _merge(int a, int b)
{
    a = find(a), b = find(b);
    if(a == b)
        return false;
    parent[a] = b;
    return true;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::iota(parent, parent + size, 0);
    std::cin >> V >> E;
    cnt = V;

    int root = -1;
    for(int i = 1, a, b; i <= E; i++)
    {
        std::cin >> a >> b;
        arr[a]++, arr[b]++;
        root = a;
        if(_merge(a, b))
            cnt--;
    }

    int tmp = 0;
    for(int i = 1; i <= V; i++)
    {
        if(!arr[i])
            if(_merge(root, i))
                cnt--;
        if(arr[i] & 1)
            tmp++;
    }
    if(cnt == 1 && (tmp == 0 || tmp == 2))
        std::cout << "YES\n";
    else
        std::cout << "NO\n";
    return 0;
}