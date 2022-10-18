// dp 문제
#include <bits/stdc++.h>


constexpr int size = 21;
constexpr int cityNum = 1e3 + 1;
int C, N;
int cost[size], income[size], dp[size][cityNum];

int func(int pos, int dest)
{
    int& ret = dp[pos][dest];
    if(ret != -1)
        return ret;
    if(pos == N)
        return ret = dest ? 1e9 : 0;

    ret = func(pos + 1, dest);
    if(dest > 0)
        ret = std::min(ret, func(pos, std::max(dest - income[pos], 0)) + cost[pos]);
    return ret;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> C >> N;
    for(int i = 0; i < N; i++)
        std::cin >> cost[i] >> income[i];

    memset(dp, -1, sizeof(dp));

    std::cout << func(0, C) << '\n';
    return 0;
}