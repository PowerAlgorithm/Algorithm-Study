#include <bits/stdc++.h>
using namespace std;

int M, N, K;
int board[101][101];
bool visited[101][101] = {false};
int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};
int area = 0;
vector<int> ans;
int cnt = 0;

bool checkZone(int rx, int ry)
{
    if (rx >= N || ry >= M || rx < 0 || ry < 0)
        return false;
    return true;
}

//보드 판 만들기 from ~ to
void makeBoard(int fx, int fy, int tx, int ty)
{
    for (int i = fx; i <= tx; i++)
        for (int j = fy; j <= ty; j++)
            board[i][j] = 1;
}

int dfs(int x, int y)
{
    for (int i = 0; i < 4; i++)
    {
        int rx = x + dx[i];
        int ry = y + dy[i];
        if (checkZone(rx, ry) && (!visited[rx][ry]) && (board[rx][ry] == 0))
        {
            visited[rx][ry] = true;
            area += dfs(rx, ry);
        }
    }
    return 1;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> M >> N >> K;

    for (int i = 0; i < K; i++)
    {
        int fx, fy, tx, ty;
        cin >> fx >> fy >> tx >> ty;
        tx--;
        ty--;
        makeBoard(fx, fy, tx, ty);
    }
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < M; j++)
        {
            if ((!visited[i][j]) && (board[i][j] == 0))
            {
                visited[i][j] = 1;
                area = 1;
                dfs(i, j);
                ans.push_back(area);
                area = 0;
                cnt++;
            }
        }
    }
    sort(ans.begin(), ans.end());
    cout << cnt << '\n';

    for (auto a : ans)
    {
        cout << a << ' ';
    }
    cout << '\n';
    return 0;
}