#include <bits/stdc++.h>
using namespace std;

string arr;
bool flag = true;
int T;

void fold(int l, int r)
{
    int mid, left, right;
    if(!flag || l > r)
        return;

    mid = (l + r) / 2;
    for (left = mid - 1, right = mid + 1; left >= l && right <= r; left--, right++){
        if(arr[left] == arr[right]){
            flag = false;
            return;
        }
    }
    fold(l, mid - 1);
    fold(mid + 1, r);
}
int main()
{


    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> T;
    for (int i = 0; i < T;i++){
        flag = true;
        cin >> arr;
        fold(0, arr.length()-1);
        
        if (flag)
            cout << "YES\n";
        else
            cout << "NO\n";
    }

        return 0;
}