#include <bits/stdc++.h>
using namespace std;
using pii = std::pair<int, int>;

int W, H;
string graph[101];
int visited[101][101];
vector<pii> coords;
queue<pair<pii, pii>> q;
int dx[] = {0, 0, -1, 1};
int dy[] = {-1, 1, 0, 0};

bool OOB(int rx, int ry)
{
    if(0 <= rx && rx < H && 0 <= ry && ry < W)
        return true;
    return false;

}
void bfs(int x, int y)
{
    fill(&visited[0][0], &visited[100][100], 1000000000);
    for (int i = 0; i < 4;i++)
        q.push({{i, 0}, {x, y}});
    visited[x][y] = 0;
    while(!q.empty()){
        int toward = q.front().first.first, cnt = q.front().first.second;
        int x = q.front().second.first, y = q.front().second.second;
        q.pop();
        for (int i = 0; i < 4; i++)
        {
            int rx = x + dx[i], ry = y + dy[i];
            int tcnt = cnt;
            if (!OOB(rx, ry))
                continue;
            if(graph[rx][ry] == '*')
                continue;
            if (toward != i)
                tcnt++;
            if(visited[rx][ry] >= tcnt){
                visited[rx][ry] = tcnt;
                q.push({{i, tcnt}, {rx, ry}});                    
            }
        }
        
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> W >> H;
    for (int i = 0; i < H;i++){
        cin >> graph[i];
        for (int j = 0; j < W;j++){
            if(graph[i][j] == 'C')
                coords.push_back({i, j});
        }

    }
    bfs(coords[0].first, coords[0].second);
    cout << visited[coords[1].first][coords[1].second];

    return 0;
}