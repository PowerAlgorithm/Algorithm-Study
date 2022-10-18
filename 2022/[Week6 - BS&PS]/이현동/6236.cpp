#include <bits/stdc++.h>
using namespace std;
int N, M;
vector<int> money;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> M;
    for (int i = 0; i < N; i++){
        int tmp;
        cin >> tmp;
        money.push_back(tmp);
    }
    
    int l = *max_element(money.begin(), money.end()), r = accumulate(money.begin(), money.end(), 0);
    
    int mid = 0;
    while (l <= r)
    {
        mid = (l + r) / 2;
        int cnt = 1;
        int tmp = mid;
        for (int i = 0; i < N; i++)
        {
            tmp -= money[i];
            if (tmp <= 0)
            {
                tmp = mid - money[i];
                cnt++;
            }
            
        }
        if (cnt > M)
            l = mid + 1;
        else
            r = mid - 1;
        
            
    }
    cout << mid;
    return 0;
}