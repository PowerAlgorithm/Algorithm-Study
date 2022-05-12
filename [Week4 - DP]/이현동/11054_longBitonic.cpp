#include <bits/stdc++.h>
using namespace std;

int N;
int arr[1001];
int dp1[1001];
int dp2[1001];

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;

    for (int i = 0; i < N;i++)
        cin >> arr[i];
    
    fill(&dp1[0], &dp1[N], 1);
    fill(&dp2[0], &dp2[N], 1);

    for (int i = 1; i < N;i++){
        int m = 0;
        for (int j = 0; j < i; j++)
        {
            if (arr[j] < arr[i])
                m = max(dp1[j], m);
        }
        dp1[i] += m;
    }

    for (int i = N - 2; i > -1;i--){
        int m = 0;
        for (int j = N - 1; j >= i;j--){
            if (arr[i] > arr[j])
                m = max(dp2[j], m);
        }
        dp2[i] += m;
    }

    int res = -1;
    for (int i = 0; i < N;i++){
        if (res < (dp1[i] + dp2[i]))
            res = dp1[i] + dp2[i];
        
    }

    cout << res - 1;
    return 0;
}