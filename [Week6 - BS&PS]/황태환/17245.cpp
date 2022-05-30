#include <bits/stdc++.h>
using ll = long long;

constexpr int size = 1e3 + 1;
ll arr[size][size];

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N;
    ll l = 0, r = 0, sum = 0;

    std::cin >> N;
    for(int i = 0; i < N; i++)
    {
        for(int j = 0; j < N; j++)
        {
            std::cin >> arr[i][j];
            sum += arr[i][j];
            r = std::max(r, arr[i][j]);
        }
    }

    while(l + 1 < r)
    {
        ll m = (l + r) >> 1, cnt = 0;

        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                cnt += (m > arr[i][j] ? arr[i][j] : m);
        
        if(((double)cnt / sum) >= 0.5)
            r = m;
        else
            l = m;
    }
    std::cout << r << '\n';
    return 0;
}