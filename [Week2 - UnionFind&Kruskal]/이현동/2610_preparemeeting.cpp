#include <bits/stdc++.h>
#define INF INT_MAX
using namespace std;

//https://velog.io/@binary_j/2610-%ED%9A%8C%EC%9D%98%EC%A4%80%EB%B9%84

int N, M;
bool arr[101][101];
bool visit[101];
vector<int> ans;

void floydWarshall(vector<int> grp){
    int size = grp.size();
    if(size == 1){ans.push_back(grp[0]); return;}
    int d[101][101];
    
    for(int i=0; i<size; i++){
        for(int j=0; j<size; j++){
            if (i == j) d[i][j] = 0;
            else if(arr[grp[i]][grp[j]] == true) d[i][j] = 1;
            else d[i][j] = size+1;
        }
    }
    
    for(int k=0; k<size; k++){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(d[i][k] + d[k][j] < d[i][j]){
                    d[i][j] = d[i][k] + d[k][j];
                }
            }
        }
    }
    
    // 위원장
    int rep;
    int tmp = size+1;
    
    for(int i=0; i<size; i++){
        // 정점으로부터 다른 정점까지의 거리의 최대값이 제일 작은 정점 -> 위원장
        int dis = 0;
        for(int j=0; j<size; j++){
            dis = max(dis, d[i][j]);
        }
        if(dis < tmp){
            tmp = dis;
            rep = grp[i];
        }
    }
    
    ans.push_back(rep);
}

void bfs(int V){
    // 위원회 회원들을 담을 벡터 grp
    vector<int> grp;
    queue<int> q;
    q.push(V);
    visit[V] = true;
    
    while(!q.empty()) {
        V = q.front();
        grp.push_back(V);
        q.pop();
        for(int i=1; i<=N; i++) {
            if(visit[i] == true || arr[i][V] == false) continue;
            q.push(i);
            visit[i] = true;
        }
    }
    floydWarshall(grp);
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    cin>>N>>M;
    
    for(int i=0; i<M; i++){
        int a, b;
        cin>>a>>b;
        arr[a][b] = arr[b][a] = true;
    }
    
    for(int i=1; i<=N; i++){
        // 위원회 구성
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