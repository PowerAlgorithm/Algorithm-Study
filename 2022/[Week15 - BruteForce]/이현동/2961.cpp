#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

using pii = std ::pair<int, int>;

int N;
vector<pii> ingred;
vector<pii> res(11);
bool visited[11];
int ans = 2e9;

void dfs(int idx, int depth)
{
    if(depth > 0){
        int S, B;
        S = res[0].first;
        B = res[0].second;
        for (int i = 1; i < depth;i++){
            S *= res[i].first;
            B += res[i].second;
        }
        ans = min(ans, abs(B - S));
    }

    if(depth == N)
        return;

    for (int i = idx; i < N;i++){    
        res[depth] = {ingred[i].first, ingred[i].second};
        dfs(i + 1, depth + 1);
        
    }
}


int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    
    int a, b;
    for (int i = 0;i < N;i++){
        cin >> a >> b;
        ingred.push_back({a, b});
    }

    dfs(0, 0);
    cout << ans;

    return 0;
}