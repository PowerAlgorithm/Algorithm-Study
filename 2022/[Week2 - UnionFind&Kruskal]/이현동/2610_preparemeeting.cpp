#include <bits/stdc++.h>
#define INF INT_MAX
using namespace std;


int N, M;
bool arr[101][101]; // 그래프 간선 표시
bool visit[101]; // 방문 표시
vector<int> ans; // 답

void floydWarshall(vector<int> group){
    int size = group.size(); // 그룹 내에 속한 인원 수
    if(size == 1){
        ans.push_back(group[0]); 
        return;
    }
    int dist[101][101]; // 의사전달시간 계산
    
    for(int i=0; i<size; i++){
        for(int j=0; j<size; j++){
            if (i == j) 
                dist[i][j] = 0;
            else if(arr[group[i]][group[j]] == true) 
                dist[i][j] = 1;
            else 
                dist[i][j] = size+1;
        }
    }
    
    for(int k=0; k<size; k++){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(dist[i][k] + dist[k][j] < dist[i][j]){
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }
    // 그룹 장을 뽑는 부분
    int rep;
    int tmp = size+1;
    
    for(int i=0; i<size; i++){
        // 정점으로부터 다른 정점까지의 거리의 최대값이 제일 작은 정점 (그룹 장)
        int dis = 0;
        for(int j=0; j<size; j++){
            dis = max(dis, dist[i][j]);
        }
        if(dis < tmp){
            tmp = dis;
            rep = group[i];
        }
    }
    
    ans.push_back(rep);
}

void bfs(int V){
    // 위원회 회원들을 담을 벡터 group
    // 뻗어나갈 수 있는 그룹을 만들기
    vector<int> group;
    queue<int> q;
    q.push(V);
    visit[V] = true;
    
    while(!q.empty()) {
        V = q.front();
        group.push_back(V);
        q.pop();
        for(int i=1; i<=N; i++) {
            if(visit[i] == true || arr[i][V] == false) continue;
            q.push(i);
            visit[i] = true;
        }
    }
    floydWarshall(group);
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
    
    cin>>N>>M;
    
    for(int i=0; i<M; i++){
        int a, b;
        cin>>a>>b;
        arr[a][b] = arr[b][a] = true;
    }
    
    for(int i=1; i<=N; i++){
        // 위원회 구성, 그룹을 짓는 부분
        if(!visit[i])
            bfs(i);
    }
    
    cout<<ans.size()<<endl;
    
    sort(ans.begin(), ans.end());
    
    for(int i=0; i<ans.size(); i++){
        cout<<ans[i]<<endl;
    }
    
    return 0;
}