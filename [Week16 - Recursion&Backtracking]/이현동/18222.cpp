#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

const ll MAX_LONG = 62;
/*
1. X를 0 -> 1, 1 -> 0으로 바꿈
2. X뒤에 X를 붙인다
1~2반복
K번째 문자열 구하라
문자열 길이 1, 2, 4, 8, 16으로 증가함

*/
ll K;

char ans;

ll dfs(ll N)
{
    if(N == 0)
        return 0;
    if (N == 1)
        return 1;
    if (N%2)
        return 1-dfs(N/2);
    else
        return dfs(N / 2);
}
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> K;
    
    cout << dfs(K-1);

    return 0;
}