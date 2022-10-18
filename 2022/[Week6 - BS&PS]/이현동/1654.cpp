#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

ll N, K;
vector<ll> line(10001);

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> K;
    for (int i = 0; i < N; i++)
        cin >> line[i];

    
    ll l = 1, r = *max_element(line.begin(), line.end());

    while(l <= r){
        ll cnt = 0;
        ll mid = (l + r)/2;
        for (int i = 0; i < N;i++)
            cnt += line[i] / mid;
        if (cnt >= K)
            l = mid + 1;
        else
            r = mid - 1;
    }
    cout << r;

    return 0;
}