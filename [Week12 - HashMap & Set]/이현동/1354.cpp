#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

ll N, P, Q, X, Y;
map<ll, ll> A;

ll calMap(ll n)
{
    if (n <= 0)
        return 1;
    if (A.find(n) != A.end())
        return A[n];
    
    return A[n] = calMap(n/P - X) + calMap(n / Q - Y);
}
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> P >> Q >> X >> Y;
    cout << calMap(N);

    return 0;
}
