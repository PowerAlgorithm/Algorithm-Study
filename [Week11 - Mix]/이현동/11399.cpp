#include <bits/stdc++.h>
using namespace std;

int N;
vector<int> arr;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;

    for(int i = 0;i < N;i++){
        int tmp;
        cin >> tmp;
        arr.push_back(tmp);
    }
        
    
    sort(arr.begin(), arr.end());

    int tot = 0;
    int ans = 0;

    for(int i = 0;i < N;i++){
        tot += arr[i];
        ans += tot;
    }

    cout << ans;



    return 0;
}