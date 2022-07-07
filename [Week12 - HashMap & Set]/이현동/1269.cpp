#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int N, M;
set<ll> A;
set<ll> B;
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    int tmp;
    cin >> N >> M;

    for (int i = 0; i < N; i++)
    {
        cin >> tmp;
        A.insert(tmp);
    }
    for (int i = 0; i < M; i++)
    {
        cin >> tmp;
        B.insert(tmp);
    }
    vector<ll> res(N + M+1);
    auto result = set_intersection(A.begin(), A.end(), B.begin(), B.end(), res.begin());
    res.resize(result - res.begin());
    cout << N + M - 2 * res.size();

    return 0;
}