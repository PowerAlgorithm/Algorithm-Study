#include <bits/stdc++.h>
using namespace std;
using pii = std::pair<int, int>;

vector<pii> mat;
int dp[501][501];
int N;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;
    int r, c;
    for (int i = 0; i < N; i++){
        cin >> r >> c;
        mat.push_back(make_pair(r, c));
    }
    
    fill(&dp[0][0], &dp[500][500], 0);
    
    for (int i = 1; i < N; i++)
    {
        
        for (int j = 0; j < N-i; j++)
        {
            int x = i + j;
            dp[j][x] = 2147483647;
            for (int k = j; k <= x; k++)
            {
                dp[j][x] = min(dp[j][x], dp[j][k] + dp[k + 1][x] + mat[j].first * mat[k].second * mat[x].second);
            }
        }
    }

    cout << dp[0][N - 1];
    
    return 0;
}