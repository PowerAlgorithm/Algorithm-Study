#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

ll A, B;
ll gcd(ll x, ll y)
{
    return y ? gcd(y, x % y) : x;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> A >> B;

    ll r = B / A;
    pair<ll, ll> res;
    for (int i = 1; i <= sqrt(r); i++)
    {
        if(r % i == 0){
            ll a = i, b = r / i;
            if(gcd(a, b) == 1){
                res = {a, b};
            }
        }
    }
    cout << res.first * A << ' ' << res.second * A;
    return 0;
}
