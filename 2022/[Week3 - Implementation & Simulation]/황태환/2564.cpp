#include <bits/stdc++.h>
using pii = std::pair<int, int>;

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N, M, Q;
    std::cin >> N >> M >> Q, Q++;

    std::vector<pii> v;
    while(Q--)
    {
        int x, y;
        std::cin >> x >> y;
        if(x == 2)
            y = 2 * N + M - y;
        else if(x == 3)
            y = 2 * (N + M) - y;
        else if(x == 4)
            y = N + y;
        v.push_back({x, y});
    }
    auto p = v.back(); // 동근
    int res = 0;
    for(int i = 0; i < (int)v.size(); i++)
    {
        int tmp = std::abs(p.second - v[i].second);
        res += std::min(2 * (N + M) - tmp, tmp);
    }
    std::cout << res << '\n';
    return 0;
}