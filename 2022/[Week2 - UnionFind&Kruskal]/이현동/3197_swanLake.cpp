#include <bits/stdc++.h>
using namespace std;

int R, C;
string graph[1501];
bool visited[1501][1501] = {false,};
int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};
queue<pair<int, int>> q, wq;
vector<pair<int, int>> sloc;
bool flag;

bool checkRange(int rx, int ry){
    if (0 <= rx && rx < R && ry >= 0 && ry < C)
        return true;
    return false;
}


int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> R >> C;

    for (int i = 0; i < R;i++){
        cin >> graph[i];
        for (int j = 0; j < C;j++){
            if (graph[i][j] == '.' || graph[i][j] == 'L')
                wq.push(make_pair(i, j));
            if (graph[i][j] == 'L'){
                sloc.push_back(make_pair(i, j));
            }
        }
    }
    

    q.push(make_pair(sloc[0].first, sloc[0].second));
    visited[sloc[0].first][sloc[0].second] = true;
    int year = 0;

    while (1)
    {
        queue<pair<int, int>> bq;
        flag = false;
        while (!q.empty())
        {
            int x = q.front().first;
            int y = q.front().second;
            q.pop();
            if (flag)
                break;
            for (int i = 0; i < 4; i++)
            {
                int rx, ry;
                rx = x + dx[i];
                ry = y + dy[i];
                if (checkRange(rx, ry) && !visited[rx][ry]){
                    visited[rx][ry] = true;
                    if (graph[rx][ry] == '.')
                        q.push(make_pair(rx, ry));
                    if (graph[rx][ry] == 'X'){
                        bq.push(make_pair(rx, ry));
                    }
                    if (graph[rx][ry] == 'L'){
                        if (rx == sloc[1].first && sloc[1].second == ry)
                            flag = true;
                        q.push(make_pair(rx, ry));
                        visited[rx][ry] = true;
                    }
                }
            }
        }

        if (flag)
            break;
        q = bq;

        int size = wq.size();
        for (int i = 0; i < size;i++){
            int x = wq.front().first;
            int y = wq.front().second;
            wq.pop();
            for (int d = 0; d < 4;d++){
                int rx = x + dx[d];
                int ry = y + dy[d];
                if (!checkRange(rx, ry))
                    continue;
                if (visited[rx][ry])
                    continue;
                if (graph[rx][ry] == 'X')
                {
                    graph[rx][ry] = '.';
                    wq.push({rx, ry});
                }
            }
        }
        year++;
    }
    cout << year;

    return 0;
}