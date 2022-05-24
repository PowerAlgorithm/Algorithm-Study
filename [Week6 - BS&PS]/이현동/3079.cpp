#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

ll N, M;
vector<ll> T(100001);

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> M;

    for (int i = 0; i < N; i++)
        cin >> T[i];
    
    ll l = 1, r = (*max_element(T.begin(), T.end())*M);
    while(l < r){
        ll mid = (l + r) / 2;
        ll cnt = 0;
        for (int i = 0; i < N;i++){
            cnt += mid / T[i];
        }
        if(cnt < M)
            l = mid + 1;
        else
            r = mid;
    }
    cout << r;
    return 0;
}

