#include <bits/stdc++.h>
using namespace std;
#define MAXN 1000000000
typedef long long ll;

ll M;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> M;
    ll l = 1, r = MAXN;
    ll cnt;
    bool flag = false;
    while (l < r)
    {
        cnt = 0;
        ll mid = (l + r) / 2;

        for (int i = 5; i < mid + 1;i *= 5){
            cnt += mid / i;
        }
        if(M > cnt)
            l = mid + 1;
        else{
            if (M == cnt)
                flag = true;
            r = mid;
        }
    }
    if (!flag)
        cout << -1;
    else
        cout << r;
    
    return 0;
}