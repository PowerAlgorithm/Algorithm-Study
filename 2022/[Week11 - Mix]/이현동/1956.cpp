#include <bits/stdc++.h>
#define INF (400*10000+ 1)
using namespace std;

int V, E;
int a, b, c;
int graph[401][401];
int ans = INF;

void floyd()
{
    for(int i = 1;i <= V;i++){
        for(int j = 1;j <= V;j++){
            for(int k = 1;k <= V;k++){
                if(graph[i][j] > graph[i][k] + graph[k][j]){
                    graph[i][j] = graph[i][k] + graph[k][j];
                    
                }  
                
            }
        }
    }

    return;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> V >> E;
    fill(&graph[0][0], &graph[400][400], INF);

    //init
    for(int i = 0;i <= V;i++){
        for(int j =0;j <= V;j++){
            if(i == j)
                graph[i][j] = 0;
        }
    }

    for(int i = 0;i < E;i++){
        cin >> a >> b >> c;
        graph[a][b] = c;
    }
    floyd();

    for(int i = 1;i <= V;i++){
        for(int j = 1;j <= V;j++){
            if (i == j){
                if(graph[i][j] != 0)
                    ans = min(graph[i][j], ans);
                for(int k = 1;k <= V;k++){
                    if(i == k || j == k)
                        continue;
                    if(graph[i][k] != INF && graph[k][j] != INF)
                        ans = min(ans, graph[i][k] + graph[k][j]);
                }
            }
        }
    }
    if(ans != INF)
        cout << ans;
    else
        cout << -1;

    return 0;
}

