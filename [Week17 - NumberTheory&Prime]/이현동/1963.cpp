#include <bits/stdc++.h>
using namespace std;

int T;
int A, B;
bool prime[10001] = {true};

// 소수를 계산한다.
void getPrime()
{
    fill(&prime[0], &prime[10000], true);

    prime[1] = false;

    for (int i = 2; i < 10000;i++){
        if(prime[i] == 0)
            continue;
        for (int j = i + i; j < 10000; j+=i)
            prime[j] = false;
    }
}

int bfs(string start, int End)
{
    queue<string> q;
    int visited[10001];
    string curr, nxt;

    // init
    q.push(start);
    fill(&visited[0], &visited[10000], 0);
    visited[stoi(start)] = 1;

    while(!q.empty()){
        curr = q.front();
        
        q.pop();

        for (int i = 0; i < 4;i++){
            for (char c = '0'; c <= '9';c++){
                if(c=='0' && i==0) continue;
                nxt = curr;
                nxt[i] = c;
                int inxt = stoi(nxt);
                if(prime[inxt] && visited[inxt] == 0){
                    q.push(nxt);
                    visited[inxt] = visited[stoi(curr)] + 1;
                }
            }
        }
    }
    return visited[End] - 1;
}

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> T;
    getPrime();
    for (int i = 0; i < T;i++){
        cin >> A >> B;
        int res = bfs(to_string(A), B);
        if(res == -1)
            cout << "Impossible\n";
        else
            cout << res << '\n';
    }

        return 0;
}