#include <bits/stdc++.h>
using namespace std;
using pii = std::pair<int, int>;

int N, K;
vector<pii> stuff;
int dp[101][100001];

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> K;
    int w, v;
    stuff.push_back({0, 0});
    for (int i = 0; i < N; i++)
    {
        cin >> w >> v;
        stuff.push_back({w, v});
    }
    fill(&dp[0][0], &dp[100][100000], 0);

    for (int i = 1; i <= N; i++)
    {
        for (int j = 1; j <= K;j++){
            if (j >= stuff[i].first)
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - stuff[i].first] + stuff[i].second);
            else
                dp[i][j] = dp[i - 1][j];
        }
    }
    cout << dp[N][K];

    return 0;
}
