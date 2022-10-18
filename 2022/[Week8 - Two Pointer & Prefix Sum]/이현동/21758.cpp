#include <bits/stdc++.h>
using namespace std;

int N;
int arr[100001];
int s[100001];
int ans = 0;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;
    
    
    for (int i = 1; i <= N; i++)
    {
        cin >> arr[i];
        s[i] += s[i-1] + arr[i];
    }
    // 벌-꿀-벌
    for (int i = 2; i < N;i++){
        int now = (s[i] - s[1]) + (s[N - 1] - s[i - 1]);
        ans = max(ans, now);
    }
    // 벌-벌-꿀
    //arr 벌위치 제외 이전 부터 꿀위치까지의 합
    for (int i = 2; i < N;i++){
        int now = (s[N] - s[1] - arr[i]) + (s[N] - s[i]);
        ans = max(ans, now);
    }
    //꿀-벌-벌
    for (int i = 2; i < N;i++){
        int now = s[N-1] - arr[i] + s[i - 1]; //arr 벌위치 제외 이전 부터 꿀위치까지의 합,
        // 한녀석은 맨끝에 위치하는 것이 항상 최대
        ans = max(ans, now);
    }
    cout << ans;

    return 0;
}