#include <bits/stdc++.h>
using namespace std;

int N, K;
string Start, End;
set<string> visited;


int bfs(){
    queue<pair<string, int>> q;
    q.push({Start, 0});
    visited.insert(Start);

    while(!q.empty()){
        string tmp;
        string curr = q.front().first;
        int cnt = q.front().second;
        q.pop();
        if (curr == End)
            return cnt;
        for (int i = 0; i < N - K + 1; i++)
        {
            tmp = curr;
            reverse(tmp.begin() + i, tmp.begin() + i + K);
            if (visited.find(tmp) != visited.end())
                continue;
            visited.insert(tmp);
            q.push({tmp, cnt + 1});
        }
    }
    return -1;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> K;
    char tmp;
    for (int i = 0; i < N;i++){
        cin >> tmp;
        Start += tmp;
        End += (char)((i+1) + '0');
    }

    cout << bfs();

    return 0;
}