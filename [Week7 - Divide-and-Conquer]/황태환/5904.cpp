#include <bits/stdc++.h>

int dp[32];

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int n, res = 0;
    std::cin >> n;
    dp[0] = 3;
    for(int i = 1; i < 28; i++)
        dp[i] = dp[i - 1] * 2 + i + 3;
    
    for(int i = 27; i >= 0; i--)
    {
        if(n > dp[i - 1])
        {
            n -= dp[i - 1];
            if(n == 1)
            {
                res = 2;
                break;
            }
            else if(n > 1 && n <= i + 3)
            {
                res = 1;
                break;
            }
            n -= i + 3;
        }
    }
    if(res == 1)
        std::cout << "o\n";
    else
        std::cout << "m\n";
    return 0;
}