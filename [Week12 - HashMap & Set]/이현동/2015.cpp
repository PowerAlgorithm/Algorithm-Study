#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

ll N, K;
int ans = 0;
ll arr[200001];

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> K;
    ll tot = 0;
    for (int i = 0; i < N;i++){
        cin >> arr[i];
        tot += arr[i];
    }
    ll l = 0, r = N - 1;
    while (l < r)
    {   
        if (tot > K){
            tot -= arr[r];
            r--;
        }
        else if( tot > K){
            tot -= arr[l];
            l++;
        }
    }

    return 0;
}