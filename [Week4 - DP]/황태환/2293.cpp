#include <bits/stdc++.h>

int coin[101];
int dp[10001];

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int n, k;
    std::cin >> n >> k;

    dp[0] = 1;
    
    for(int i = 1; i<=n; i++)
        std::cin >> coin[i]; // 동전의 가치 저장

    for(int i = 1; i <= n; i++)
    {
        for(int j = coin[i]; j<=k; j++)
            dp[j] += dp[j - coin[i]];
    }
    std::cout << dp[k] << '\n';
    return 0;
}