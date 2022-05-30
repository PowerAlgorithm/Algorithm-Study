#include <bits/stdc++.h>
using ll = long long;

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N, M;
    std::cin >> N >> M;

    ll maxv = 0;
    std::vector<ll> v(N);
    for(int i = 0; i < N; i++)
    {
        std::cin >> v[i];
        maxv = std::max(maxv, v[i]);
    }   

    ll left = 1, right = maxv * M, res = 0;
    while(left <= right)
    {
        ll mid = (left + right) >> 1;
        ll sum = 0;

        for(int i = 0; i < N; i++)
            sum += mid / v[i];

        if(sum < M)
            left = mid + 1;
        else
        {
            res = mid;
            right = mid - 1;
        }
    }
    std::cout << res << '\n';
    return 0;
}