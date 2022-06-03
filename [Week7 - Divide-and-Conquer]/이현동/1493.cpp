#include <bits/stdc++.h>
using namespace std;
using pii = std::pair<int, int>;
typedef long long ll;

int L, W, H;
int N;
vector<pii> cube;
int ans = 0;
bool flag = true;

void cubeCount(int l, int w, int h, int idx)
{
    if(l == 0 || w == 0 || h == 0)
        return;
    
    for (int i = idx; i > -1;i--){
        if (cube[i].second > 0 && cube[i].first <= l && cube[i].first <= h&& cube[i].first <= w){
            cube[i].second--;
            ans++;
            cubeCount(l - cube[i].first, w, h, i);
            cubeCount(cube[i].first, w- cube[i].first, h,i);
            cubeCount(cube[i].first, cube[i].first, h - cube[i].first,i);
            return;
        }
    }
    flag = false;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> L >> W >> H;
    cin >> N;
    for (int i = 0; i < N; i++)
    {
        int a, b;
        cin >> a >> b;
        ll tmp = pow(1 << a, 3);
        cube.push_back({(1 << a), b}); // 한 변의 길이 저장
    }
    
    cubeCount(L, W, H, N-1);
    if(!flag)
        cout << -1;
    else{
        cout << ans;
    }

    return 0;
}