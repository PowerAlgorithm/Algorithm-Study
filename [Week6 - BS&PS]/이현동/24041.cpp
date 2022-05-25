#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
using pll = std::pair<ll, ll>;

ll N, G, K;
vector<pair<int, pll>> milkit;

ll binarySearch(ll l, ll r, int k){
    ll mid;
    ll res = 0;

    while (l <= r)
    {
        ll cnt = 0;
        mid = (l + r) / 2;
        for (int i = 0; i < N - k; i++){
            if (mid-milkit[i].second.first < 1){
                cnt += milkit[i].second.first;
            }
            else{
                cnt += (milkit[i].second.first * (mid - milkit[i].second.second));
            }
        }

        if(cnt <= G){
            res = mid;
            l = mid + 1;
            
        }else{
            r = mid - 1;
        }
    }

    return res;
}

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> G >> K;
    
    for (int i = 0; i < N;i++){
        ll s, l;
        int o;
        cin >> s >> l >> o;
        milkit.push_back(make_pair(o, make_pair(s, l)));
    }
    // 람다로 풀기

    sort(milkit.begin(), milkit.end());
    ll left = 1, right = 2000000000;

    ll ans = 0;
    if (K > 0)
    {
        while(K >= 0){
            ans = max(ans, binarySearch(left, right, K));
            K--;
        }
        cout << ans;
    }
    else{
        cout << binarySearch(left, right, K);
    }
    return 0;
}