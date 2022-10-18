#include <bits/stdc++.h>
using namespace std;

int N, D, K, C; //접시의 수, 초밥의 가짓 수, 연속해서 먹는 접시의  수, 쿠폰
int arr[300001];
int dup, coupon, ans = 0;
int check[3001] = {0};

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> D >> K >> C;
    for (int i = 0; i < N;i++)
        cin >> arr[i];
    
    for (int i = 0; i < N; i++)
    {
        dup = 0;
        coupon = 1;
        memset(check, 0, sizeof(check));
        for (int j = i; j < i + K; j++)
        {
            if(check[arr[j % N]] == 1)
                dup++;
            else
                check[arr[j % N]] = 1;
            if(arr[j%N] == C)
                coupon = 0;
        }
        ans = max(ans, K - dup + coupon);
    }
    cout << ans;

    return 0;
}

