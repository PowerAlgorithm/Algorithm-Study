#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
using pll = std::pair<ll, ll>;


int N;
vector<pll> rooms;

bool compare(const pll &a, const pll &b)
{
    return (a.second < b.second);
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for(int i = 0;i < N;i++){
        int a, b;
        cin >> a >> b;
        rooms.push_back(make_pair(a, b));
    }
    sort(rooms.begin(), rooms.end());
    sort(rooms.begin(), rooms.end(), compare);
    

    ll val = 0;
    int ans = 0;

    for(int i = 0;i < N;i++){
        if(val <= rooms[i].first){
            val = rooms[i].end;
            ans++;
        }
    }
    cout << ans;

    return 0;
}