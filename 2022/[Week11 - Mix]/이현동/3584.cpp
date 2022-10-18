#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int N, T;
bool visited[10001];
int parent[100001];
int a, b; // first and second node
int f, s;



void init()
{
    fill(&visited[0], &visited[10000], false);
    for(int i =0;i <= N;i++){
        parent[i] = i;
    }
    
    return;
}


int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> T;
    while(T--){
        init();
        cin >> N;
        for(int i = 0;i < N-1;i++){
            cin >> a >> b;
            parent[b] = a;
        }
        cin >> f >> s;
        visited[f] = true;
        while(f != parent[f]){
            f = parent[f];
            visited[f] = true;
        }
        while(1){
            if(visited[s]){
                cout << s << '\n';
                break;
            }
            s = parent[s];
        }
    }

    return 0;
}