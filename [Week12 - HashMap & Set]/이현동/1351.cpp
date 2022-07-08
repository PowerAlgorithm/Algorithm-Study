#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

ll N, P, Q;
map<ll, ll> A;

ll calMap(ll n, ll p, ll q)
{
    if (A.find(n) != A.end() && A[n] != 0)
        return A[n];
    ll tmpp, tmpq;
    tmpp = n / p;
    tmpq = n / q;
    A[tmpp] = calMap(tmpp, p, q);
    A[tmpq] = calMap(tmpq, p, q);
    return A[n] = A[tmpp] + A[tmpq];
}
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> P >> Q;
    A.insert({0, 1});
    calMap(N, P, Q);
    cout << A[N];

    return 0;
}
