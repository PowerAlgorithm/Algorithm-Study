#include <bits/stdc++.h>
using pii = std::pair<int, int>;
using tiii = std::tuple<int, int, int>;

constexpr int size = 21;
int R, C, K, W;
int board[size][size][5];
bool visited[size][size];
std::vector<pii> inspect; // 온도를 조사해야 하는 칸
std::vector<tiii> blower;
std::queue<pii> move;
std::queue<tiii> plus, minus;

const int rot[] = {3, 1}; // 회전
const int dx[] = {0, 1, 0, -1};
const int dy[] = {-1, 0, 1, 0};
const int conv[] = {1, 3, 0, 2}; // 북, 동, 남, 서

bool chkOOB(int y, int x)
{
    return (y < 0 || y >= R || x < 0 || x >= C);
}

bool chkTmp()
{
    for(auto it : inspect)
        if(board[it.first][it.second][4] < K)
            return false;
    return true;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> R >> C >> K;

    for(int i = 0; i < R; i++)
    {
        for(int j = 0; j < C; j++)
        {
            int x;
            std::cin >> x;

            if(x == 0)  // 빈칸
                continue;
            if(x == 5)  // 온도를 조사해야 하는 좌표
                inspect.push_back({i, j}); 
            else
                blower.push_back({i, j, conv[x - 1]}); // 온풍기의 좌표
        }
    }

    std::cin >> W;

    for(int i = 0, y, x, t; i < W; i++)
    {
        std::cin >> y >> x >> t;
        y--, x--;

        if(t == 0) // (x, y)와 (x - 1, y) 사이에 벽
        {
            board[y][x][0] = 1; // 북
            if(y >= 1)
                board[y - 1][x][2] = 1; // 남
        }
        else
        {
            board[y][x][1] = 1; // 동
            if(x < C - 1)
                board[y][x + 1][3] = 1; // 서
        }
    }

    for(int t = 1; t <= 100; t++) // 100번
    {
        for(auto it : blower)
        {
            int y, x, dir;
            std::tie(y, x, dir) = it;

            y += dy[dir], x += dx[dir];

            if(chkOOB(y, x))
                continue;
            
            board[y][x][4] += 5;
            visited[y][x] = true;
            move.push({y, x});

            for(int i = 4; i >= 1; i--)
            {
                int sz = (int)move.size();

                while(sz--)
                {
                    int mY = move.front().first;
                    int mX = move.front().second;
                    move.pop();

                    for(auto r : rot)
                    {
                        int nDir = (dir + r) % 4;
                        int sY = mY + dy[nDir], sX = mX + dx[nDir], eY = sY + dy[dir], eX = sX + dx[dir];

                        // 경계 내에 있으면서 방문한 적 없고, 벽으로 막히지 않았다면
                        if(!chkOOB(sY, sX) && !chkOOB(eY, eX) && !visited[eY][eX] && !board[mY][mX][nDir] && !board[sY][sX][dir])
                        {
                            board[eY][eX][4] += i;
                            visited[eY][eX] = true;
                            if(i > 1)
                                move.push({eY, eX});
                        }
                    }

                    int ey = mY + dy[dir], ex = mX + dx[dir];

                    if(!chkOOB(ey, ex) && !visited[ey][ex] && !board[mY][mX][dir])
                    {
                        board[ey][ex][4] += i;
                        visited[ey][ex] = true;
                        if(i > 1)
                            move.push({ey, ex});
                    }
                }
            }
            memset(visited, false, sizeof(visited));
        }

        // 온도 조절
        for(int i = 0; i < R; i++)
        {
            for(int j = 0; j < C; j++)
            {
                if(board[i][j][4] == 0)
                    continue;
                
                int sub = 0;

                for(int d = 0; d < 4; d++)
                {
                    int nY = i + dy[d], nX = j + dx[d];
                    
                    if(chkOOB(nY, nX) || board[i][j][d] || board[i][j][4] <= board[nY][nX][4])
                        continue;

                    int mv = (board[i][j][4] - board[nY][nX][4]) / 4;
                    sub += mv;
                    plus.push({nY, nX, mv});
                }

                if(sub)
                    minus.push({i, j, sub});
            }
        }

        while(!minus.empty())
        {
            int y, x, m;
            std::tie(y, x, m) = minus.front();
            minus.pop();

            board[y][x][4] -= m;
        }

        while(!plus.empty())
        {
            int y, x, p;
            std::tie(y, x, p) = plus.front();
            plus.pop();

            board[y][x][4] += p;
        }

        for(int i = 0; i < C - 1; i++)
            board[0][i][4] = std::max(0, board[0][i][4] - 1);
        for(int i = 0; i < R - 1; i++)
            board[i][C - 1][4] = std::max(0, board[i][C - 1][4] - 1);
        for(int i = C - 1; i > 0; i--)
            board[R - 1][i][4] = std::max(0, board[R - 1][i][4] - 1);
        for(int i = R - 1; i > 0; i--)
            board[i][0][4] = std::max(0, board[i][0][4] - 1);

        if(chkTmp())
        {
            std::cout << t << '\n';
            return 0;
        }
    }
    std::cout << 101 << '\n';
    return 0;
}