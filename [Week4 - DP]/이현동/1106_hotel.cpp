#include <bits/stdc++.h>
using namespace std;
using pii = std::pair<int, int>;


int C, N;
int dp[100001];
vector<pii> cost;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> C >> N;


    int c, p;
    for (int i = 0; i < N; i++)
    {
        cin >> c >> p;
        cost.push_back(make_pair(c, p));
    }

    dp[0] = 0;
    
    for (int i = 1; i <= 100001; i++)
    {
        for (int j = 0; j < N; j++)
        {
            if (i%cost[j].first == 0)
                dp[i] = max(dp[i], (i / cost[j].first) * cost[j].second);
            if (i - cost[j].first >= 0)
                dp[i] = max(dp[i - cost[j].first] + cost[j].second, dp[i]);
        }
        if (C <= dp[i]){
            cout << i;
            break;
        }
        }

    
    return 0;
}