#include <bits/stdc++.h>
using namespace std;


int main() {
    int n, k;
    int coins[101];
    int d[10001] = {0};

    cin >> n >> k;
    for (int i = 1; i <= n; i++)
        cin >> coins[i];
    

    d[0] = 1;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= k; j++) {
            if (j >= coins[i]) {
                d[j] += d[j-coins[i]];
            }
        }
    }

    cout << d[k];

    return 0;
}