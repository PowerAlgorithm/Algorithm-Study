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
    ll ans;
    ans = gcd(A, B);

    for (int i = 0; i < ans;i++)
        cout << 1;

        return 0;
}
