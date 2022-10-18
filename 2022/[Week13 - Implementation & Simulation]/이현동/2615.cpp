#include <bits/stdc++.h>
using namespace std;

int board[19][19];
bool visited[19][19];
bool flag = false;
int dep;
int dx[] = {0, 0, -1, 1, 1, 1, -1, -1};
int dy[] = {-1, 1, 0, 0, 1, -1, 1, -1};

bool OOB(int rx, int ry){
    return (0 <= rx && rx < 19 && 0 <= ry && ry < 19);
}

void dfs(int x, int y, int color, int dir, int depth)
{
    if(depth >= 5){
        flag = true;
        return;
    }
    for (int i = 0; i < 8;i++){
        int rx = x + dx[i], ry = y + dy[i];
        if (!OOB(rx, ry) || visited[rx][ry] || color != board[rx][ry] || dir != i)
            continue;
        visited[rx][ry] = true;
        dep++;
        dfs(rx, ry, color, dir, depth + 1);
    }
    return;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    fill(&visited[0][0], &visited[18][18], false);
    for (int i = 0; i < 19;i++){
        for (int j = 0; j < 19;j++){
            cin >> board[i][j];
        }
    }

        for (int i = 0; i < 19; i++)
        {
            for (int j = 0; j < 19; j++)
            {
                if (board[i][j] != 0 && !visited[i][j])
                {
                    dep = 1;
                    int color = board[i][j];
                    visited[i][j] = true;
                    for (int d = 0; d < 8; d++)
                    {
                        int nx = i + dx[d], ny = j + dy[d];
                        if (OOB(nx, ny) && color == board[nx][ny] && !visited[nx][ny])
                        {
                            visited[nx][ny] = true;
                            dep++;
                            dfs(nx, ny, color, d, 2);
                        }
                        if (flag && dep == 5)
                        {
                            cout << color << '\n';
                            cout << i+1 << ' ' << j+1 << '\n';
                            exit(0);
                        }else{
                            flag = false;
                        }
                    }
                }
            }
        }
        if(!flag)
            cout << 0;

        return 0;
}