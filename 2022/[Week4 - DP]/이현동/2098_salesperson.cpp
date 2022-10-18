#include <bits/stdc++.h>
using namespace std;

const int IMPOSSIBLE = 1987654321;
int graph[17][17];
int N;
int dp[16][1<<16];


int traverse(int current, int visited)
{
    int &ret = dp[current][visited];
    if(ret != -1)
        return ret;
    if (visited == ((1 << N)-1))
    {
        if(graph[current][0] != 0)
            return graph[current][0];
        return IMPOSSIBLE;
    }
    ret = IMPOSSIBLE;
    for (int i = 0; i < N;++i){
        // 방문했거나, 그래프에 가는 길이 없다면
        if(visited & (1<<i) || graph[current][i] == 0)
            continue;
        ret = min(ret, traverse(i, visited | (1 << i)) + graph[current][i]);
    }
    return ret;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i = 0; i < N;++i){
        for (int j = 0; j < N;++j){
            cin >> graph[i][j];
        }
    }

    memset(dp, -1, sizeof(dp));
    cout << traverse(0, 1);

    return 0;
}