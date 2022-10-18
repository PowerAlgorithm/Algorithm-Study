#include <bits/stdc++.h>
using namespace std;

int N, M;
string board[1001]; //격자판
bool visited[1001][1001] = {false};
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};
bool flag = false;

bool checkZone(int rx, int ry)
{
    if (rx >= M || ry >= N || rx < 0 || ry < 0)
        return false;
    return true;
}

void dfs(int x, int y)
{
    if (x == M - 1)
    {
        flag = true;
        return;
    }
    for (int i = 0; i < 4; i++)
    {
        int rx = x + dx[i];
        int ry = y + dy[i];
        if (checkZone(rx, ry) && (!visited[rx][ry]) && (board[rx][ry] == '0'))
        {
            visited[rx][ry] = true;
            dfs(rx, ry);
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> M >> N;
    for (int i = 0; i < M; i++)
        cin >> board[i];

    for (int i = 0; i < N; i++)
    {
        if (board[0][i] == '0')
        {
            visited[0][i] = true;
            dfs(0, i);
        }

        if (flag)
            break;
    }
    if (flag)
        cout << "YES\n";
    else
        cout << "NO\n";
    return 0;
}