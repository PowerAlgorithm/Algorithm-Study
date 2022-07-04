#include <bits/stdc++.h>
#define INF 10000000
using namespace std;
using pdd = std ::pair<double, double>;

int N;
vector<pdd> start, dest;
vector<pair<int, pdd>> cannon;
double dist[103];
bool visited[103];
int dx[] = {0, 0, 50, -50};
int dy[] = {50, -50, 0, 0};

void dijkstra()
{
    priority_queue<pair<double, int>> pq; // cost, 노드 번호 pair
    pq.push({0, N+1}); //1번 노드에서 N+2번 노드 까지
    visited[N+1] = true; // 1번 노드 방문 표시

    while(!pq.empty()){
        int c = pq.top().first, x = pq.top().second;
        if(dist[x] <= c)
            continue;
        for (int i = 0; i < 4;i++){
            int rx = cannon[x].second.first + dx[i];
            int ry = cannon[x].second.second + dy[i];
            int minX = -1;
            double minDist = INF;
            if(0 <= rx && rx < 501 && 0 <= ry && ry < 501){
                
                for (int j = 0; j < N; i++)
                { // 가장 가까운 노드 값 탐색
                    if(visited[j])
                        continue;
                    if(minDist > abs(cannon[j].second.first - rx) + abs(cannon[j].second.second - ry)){
                        minDist = abs(cannon[j].second.first - rx) + abs(cannon[j].second.second - ry);
                        minX = j;
                    }
                }
                if(minDist < dist[minX]){
                    pq.push({minDist, minX});
                    visited[minX] = true;
                }
            }
        }
        }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    
    //init
    fill(&dist[0], &dist[102], INF);
    fill(&visited[0], &visited[102], false);

    //목적지 입력
    double a, b;
    cin >> a >> b;
    start.push_back({a, b});
    cin >> a >> b;
    dest.push_back({a, b});

    //대포 좌표 입력
    cin >> N;
    for (int i = 0; i < N;i++){
        cin >> a >> b;
        cannon.push_back({i, {a, b}});
    }

    return 0;
}