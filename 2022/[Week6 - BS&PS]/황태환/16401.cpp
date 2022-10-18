#include <bits/stdc++.h>

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int M, N;
    std::cin >> M >> N;
    std::vector<int> v(N);

    int l = 1, h = 0;
    for(int i = 0; i < N; i++)
    {
        std::cin >> v[i];
        h = std::max(h, v[i]);
    }

    int res = 0;
    while(l <= h)
    {
        int m = (l + h) / 2;
        int cnt = 0;

        for(int i = 0; i < N; i++)
            cnt += v[i] / m;
        
        if(cnt >= M)
        {
            res = m;
            l = m + 1;
        }
        else
            h = m - 1;
    }
    std::cout << res << '\n';
    return 0;
}