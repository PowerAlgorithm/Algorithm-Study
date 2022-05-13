#include <bits/stdc++.h>
using namespace std;
using pii = std::pair<int, int>;

int S;
queue<pii> q;
int ans = INT_MAX;

void bfs()
{
    int visited[1001][1001];
    fill(&visited[0][0], &visited[1000][1000], -1);
    visited[1][0] = 1;
    q.push(make_pair(1, 0));

    while(!q.empty()){
        int e = q.front().first;
        int c = q.front().second;
        q.pop();
        if (e == S){
            ans = min(visited[e][c]-1, ans);
        }
        if (visited[e][e] == -1){
            q.push({e, e});
            visited[e][e] = visited[e][c] + 1;

        }
        if (e + c <= S && visited[e+c][c] == -1){
            q.push({e + c, c});
            visited[e + c][c] = visited[e][c] + 1;
        }
        if (e - 1 >= 0 && visited[e-1][c] == -1){
            q.push({e - 1, c});
            visited[e - 1][c] = visited[e][c] + 1;
        }
    }
    
    
}

int main()
{


    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> S;
    bfs();
    cout << ans;

    return 0;
}