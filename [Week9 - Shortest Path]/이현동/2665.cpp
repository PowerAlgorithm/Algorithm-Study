#include <bits/stdc++.h>
#define INTMAX 1000000
using namespace std;
using pii = std::pair<int, int>;

int N;
int dx[] = {0, 0, -1, 1};
int dy[] = {-1, 1, 0, 0};
string graph[51];
int dist[51][51];
queue<pii> q;

bool OOB(int rx, int ry)
{
    if(0 <= rx && rx < N && 0 <= ry && ry <N)
        return true;
    return false;
}

void dijkstra(int x, int y)
{
    fill(&dist[0][0], &dist[50][50], INTMAX);
    q.push({x, y});
    dist[x][y] = 0;

    while(!q.empty()){
        int x = q.front().first, y = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++){
            int rx = x + dx[i], ry = y + dy[i];
            if (OOB(rx, ry)){
                if(graph[rx][ry] == '0'){
                    if (dist[x][y]+1 < dist[rx][ry]){
                        dist[rx][ry] = dist[x][y]+1;
                        q.push({rx, ry});
                    }
                }
                else{
                    if(dist[x][y] < dist[rx][ry]){
                        dist[rx][ry] = dist[x][y];
                        q.push({rx, ry});
                    }
                }
                

            }       
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;

    for (int i = 0; i < N;i++){
        cin >> graph[i];
    }

    dijkstra(0, 0);
    cout << dist[N - 1][N - 1];

    return 0;
}