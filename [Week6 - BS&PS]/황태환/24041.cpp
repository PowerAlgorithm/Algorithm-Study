#include <bits/stdc++.h>
using ll = long long;

struct info{
    ll s, l;
    int o;
};

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N, K;
    ll G;
    std::cin >> N >> G >> K;

    std::vector<info> v(N);
    for(int i = 0; i < N; i++)
        std::cin >> v[i].s >> v[i].l >> v[i].o;


    ll start = 1, end = 2e9;
    while(start < end)
    {
        ll mid = (start + end + 1) >> 1;
        ll sum = 0;

        std::priority_queue<ll> pq;

        for(int i = 0; i < N; i++)
        {
            sum += v[i].s * std::max(1LL, mid - v[i].l);
            if(v[i].o)
                pq.push(v[i].s * std::max(1LL, mid - v[i].l));
        }

        for(int i = 0; i < K && (int)pq.size(); i++)
        {
            sum -= pq.top();
            pq.pop();
        }

        if(sum > G)
            end = mid - 1;
        else
            start = mid;
    }
    std::cout << start << '\n';
    return 0;
}