#include <bits/stdc++.h>
using namespace std;

int N;
int arr[201];
int dp[201];

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;

    for (int i = 0; i < N; i++)
        cin >> arr[i];

    
    for (int i = 0; i < N; i++)
    {
        dp[i] = 1;
        for (int j = 0; j < i; j++)
        {
            if(arr[i] > arr[j])
                dp[i] = max(dp[i], dp[j] + 1);
        }
    }
    int ans = -1;
    for (int i = 0; i < N;i++)
        ans = max(ans, dp[i]);

    cout << N - ans;

    return 0;
}