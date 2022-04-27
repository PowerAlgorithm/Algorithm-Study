#include <bits/stdc++.h>
using pii = std::pair<int, int>;

std::vector<int> parents;

int find(int x)
{
    if(parents[x] < 0)
        return x;
    return find(parents[x]);
}

void _union(int a, int b)
{
    a = find(a);
    b = find(b);

    if(a == b)
        return;
    
    if(parents[a] < parents[b])
    {
        parents[a] += parents[b];
        parents[b] = a;
    }
    else
    {
        parents[b] += parents[a];
        parents[a] = b;
    }
}

bool chk(std::pair<pii, int> a, std::pair<pii, int> b)
{
    int x = std::abs(a.first.first - b.first.first);
    int y = std::abs(a.first.second - b.first.second);
    int r = a.second + b.second;

    int dist = x * x + y * y;

    if(dist <= r * r)
        return true;
    return false;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int T;
    std::cin >> T;

    while(T--)
    {
        int N;
        std::cin >> N;
        parents.resize(N + 1);

        std::vector<std::pair<pii, int>> enemy;

        for(int i = 0, x, y, r; i < N; i++)
        {
            std::cin >> x >> y >> r;
            enemy.push_back({{x, y}, r}); 
            parents[i] = -1;  
        }

        for(int i = 0; i < N; i++)
        {
            for(int j = i + 1; j < N; j++)
            {
                if(chk(enemy[i], enemy[j]))
                    _union(i, j);
            }
        }

        int cnt = 0;
        for(int i = 0; i < N; i++)
            if(parents[i] < 0)
                cnt++;
        std::cout << cnt << '\n';
    }
    return 0;
}