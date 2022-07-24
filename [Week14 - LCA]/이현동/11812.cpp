#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

ll N;
int K, Q;
ll u, v;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> K >> Q;

    for (int i = 0; i < Q;i++){
        cin >> u >> v;
        ll depth = 0;
        if(K == 1)
            depth = abs(u - v);
        else{
            while(u != v){
                // cout << "u, v :"  << u << ' ' <<  v << '\n';
                if (u > v){
                    u = (u - 2) / K + 1;
                }else{
                    v = (v - 2) / K + 1;
                }
                ++depth;
            }
        }
        // cout << u << ' ' <<  v << '\n';
        cout << depth << '\n';
        // cout << "----------\n";
    }

        return 0;
}