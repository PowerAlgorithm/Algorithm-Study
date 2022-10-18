#include <bits/stdc++.h>
using namespace std;

int N, M;
int graph[1001][1001];
int dp[1001][1001][3];
int ans = INT_MAX;
int d[] = {-1, 0, 1}; //좌, 중, 우

/*
초기에 모든 방향으로 탐색이 가능
이후 이전에 선택한 방향 제외 탐색
깊이 우선 탐색으로 구현해야한다.
깊이가 graph의 행 넘으면 return
*/

/*
params :방향, depth, 총합
*/

bool OOB(int rx, int ry){
    if(0 <= rx && rx < N && 0<= ry && ry < M)
        return true;
    return false;
}

int dfs(int x, int y,  int direction, int depth)
{
    if(depth == N)
        return graph[x][y];
    if(dp[x][y][direction] != INT_MAX)
        return dp[x][y][direction];
    
    for (int i = 0; i < 3; i++)
    {
        if (direction != d[i] && OOB(x+1, y+d[i]))
        {
            dp[x][y][direction] = min(graph[x][y] + dfs(x + 1, y + d[i], d[i], depth + 1), dp[x][y][direction]);
        }
    }
    return dp[x][y][direction];
}
int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> M;
    fill(&dp[0][0][0], &dp[1000][1000][2], INT_MAX);

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M;j++){
            cin >> graph[i][j];
        }
    }
    
    
    for (int i = 0; i < M;i++){
        for (int k = 0; k < 3;k++){
            ans = min(dfs(0, i, k, 1), ans);
        }
    }
    
        cout << ans;
    return 0;
}
