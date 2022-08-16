#include <bits/stdc++.h>
using namespace std;

int N, k;
string arr[2];

bool OOB(int rx, int ry, int t)
{
    if (0 <= rx && rx < 2 && t < ry && ry < N)
        return true;
    return false;
}
int bfs()
{
    queue<pair<pair<int, int>, int>> q;
    int dx[] = {0, 0, 1, -1};
    int dy[] = {1, -1, k, k};


    bool visited[2][200001];
    fill(&visited[0][0], &visited[1][200000], false);

    q.push({{0, 0}, 0});
    visited[0][0] = true;

    while(!q.empty()){
        int x = q.front().first.first, y = q.front().first.second;
        int time = q.front().second;
        q.pop();
        if(time >= N)
            break;

        for (int i = 0; i < 4;i++){
            int rx = x + dx[i];
            int ry = y + dy[i];
            if(ry >= N)
                return 1;
            if(OOB(rx, ry, time)){
                if (arr[rx][ry] == '1' && !visited[rx][ry] )
                {
                    visited[rx][ry] = true;
                    q.push({{rx, ry}, time + 1});
                }
            }
        }
    }
    return 0;
}

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> k;
    cin >> arr[0];
    cin >> arr[1];
    cout << bfs();

    return 0;
}
