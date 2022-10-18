#include <bits/stdc++.h>
using namespace std;

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);

    int n;
    int ans = 0;
    int target;
    cin >> n;
    bool tmp[2000001] = {};
    int arr[1000001] = {};
    for (int i = 0; i < n; i++)
    {
        int number;
        cin >> arr[i];
    }
    cin >> target;

    for (int i = 0; i < n; i++)
    {
        if (target - arr[i] > 0 and tmp[target - arr[i]])
            ans++;
        tmp[arr[i]] = 1;
    }

    cout << ans << '\n';
    return 0;
}