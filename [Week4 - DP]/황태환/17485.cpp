#include <bits/stdc++.h>

constexpr int size = 1e3 + 1;
constexpr int INF = 0x3f3f3f3f;
int N, M, arr[size][size], dp[size][size][4];

const int delta[] = {0, -1, 0, 1};

int func(int r, int c, int d)
{
    if(c < 1 || c > M)
        return INF;
    if(r == N)
        return arr[r][c];
    
    int& ret = dp[r][c][d];
    if(ret != INF)
        return ret;
    
    for(int i = 1; i <= 3; i++)
    {
        if(i == d)
            continue;
        ret = std::min(ret, arr[r][c] + func(r + 1, c + delta[i], i));
    }
    return ret;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M;
    for(int i = 1; i <= N; i++)
        for(int j = 1; j <= M; j++)
            std::cin >> arr[i][j];
    
    memset(dp, INF, sizeof(dp));

    int res = INF;
    for(int i = 1; i <= M; i++)
        res = std::min(res, func(0, i, 0));
    std::cout << res << '\n';
    return 0;
}