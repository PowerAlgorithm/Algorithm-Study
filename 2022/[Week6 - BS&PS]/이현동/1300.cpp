#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

ll K;
int N;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;
    cin >> K;

    ll l = 1, r = K;
    int ans = 0;

    while (l <= r)
    {
        ll s = 0;
        ll mid = (l + r) / 2;
        for (int i = 1; i < N + 1; i++){
            int tmp = (mid / i);
            s += min(tmp, N);
        }
        if(s < K){
            l = mid + 1;
        }if(s >= K){
            r = mid - 1;
            ans = mid;
        }
    }
    cout << ans;

    return 0;
}
