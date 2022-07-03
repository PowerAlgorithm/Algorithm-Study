#include <bits/stdc++.h>
using namespace std;

string start, End;
set<string> visited;

int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};

int bfs()
{
    queue<pair<string, int>> q;
    q.push(make_pair(start, 0));
    visited.insert(start);

    while (!q.empty()){
        string curr = q.front().first;
        int cnt = q.front().second;
        q.pop();

        if(curr == End)
            return cnt;
        int zero = curr.find('0');
        int x = zero / 3;
        int y = zero % 3;

        for (int i = 0; i < 4;i++)
        {
            int rx = x + dx[i];
            int ry = y + dy[i];
            if (0 <= rx && rx < 3&& 0 <= ry && ry < 3){
                string next = curr;
                swap(next[x * 3 + y], next[rx * 3 + ry]);

                if(visited.find(next) == visited.end()){
                    visited.insert(next);
                    q.push(make_pair(next, cnt + 1));
                }
            }
        }
    }
    return -1;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    End = "123456780";

    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3;j++){
            int a;
            cin >> a;
            char tmp = a + '0';
            start = start + tmp;
        }
    }
    cout << bfs();
    return 0;
}