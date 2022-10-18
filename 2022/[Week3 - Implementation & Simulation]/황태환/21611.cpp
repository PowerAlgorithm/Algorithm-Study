#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 50;
int N, M, cnt, board[size][size], destroyed[4]; 
std::vector<pii> marbleOrder;

const int dy[] = {0, 1, 0, -1};
const int dx[] = {-1, 0, 1, 0};
const int ddy[] = {0, -1, 1, 0, 0};
const int ddx[] = {0, 0, 0, -1, 1};

void setGroup()
{
    int tmp[size][size];

    for(int i = 0; i < N; i++)
        for(int j = 0; j < N; j++)
            tmp[i][j] = board[i][j];
    memset(board, 0, sizeof(board));

    int ccnt, idx = 0;

    for(int i = 0; i < (int)marbleOrder.size(); i += ccnt)
    {
        if(idx == (int)marbleOrder.size())
            break;
        int y = marbleOrder[i].first, x = marbleOrder[i].second;

        if(!tmp[y][x])
            continue;
        
        int c = tmp[y][x];
        ccnt = 1;

        for(int j = i + 1; j < (int)marbleOrder.size(); j++)
        {
            int nY = marbleOrder[j].first, nX = marbleOrder[j].second;

            if(tmp[nY][nX] != c)
                break;
            
            ccnt++;
        }

        int cY = marbleOrder[idx].first, cX = marbleOrder[idx].second;
        board[cY][cX] = ccnt;

        if(idx + 1 < (int)marbleOrder.size())
        {
            int nY = marbleOrder[idx + 1].first, nX = marbleOrder[idx + 1].second;
            board[nY][nX] = c;
        }
        else
            break;
        idx += 2;
    }
}

void move()
{
    int tmp[size][size];

    for(int i = 0; i < (int)marbleOrder.size(); i++)
    {
        int y = marbleOrder[i].first, x = marbleOrder[i].second;

        if(board[y][x])
            continue;
        
        for(int p = 0; p < N; p++)
            for(int q = 0; q < N; q++)
                tmp[p][q] = board[p][q];

        int ccnt = 1, idx;

        for(idx = i + 1; idx < (int)marbleOrder.size(); idx++)
        {
            int nY = marbleOrder[idx].first, nX = marbleOrder[idx].second;

            if(tmp[nY][nX])
                break;
            
            ccnt++;
        }

        for(int j = i; j < (int)marbleOrder.size() - ccnt; j++)
        {
            int cY = marbleOrder[j].first, cX = marbleOrder[j].second;
            int nY = marbleOrder[j + ccnt].first, nX = marbleOrder[j + ccnt].second;

            board[cY][cX] = tmp[nY][nX];
            board[nY][nX] = 0;
        }
    }
}

void process()
{
    bool chk = false;

    while(true)
    {
        for(int i = 0; i < (int)marbleOrder.size(); i++)
        {
            int y = marbleOrder[i].first, x = marbleOrder[i].second;

            if(!board[y][x])
                continue;
            
            int c = board[y][x];
            std::vector<pii> tmp;
            tmp.push_back({y, x});

            for(int j = i + 1; j < (int)marbleOrder.size(); j++)
            {
                int nY = marbleOrder[j].first, nX = marbleOrder[j].second;

                if(board[nY][nX] != c)
                    break;
                
                tmp.push_back({nY, nX});
            }

            if((int)tmp.size() >= 4)
            {
                for(auto it : tmp)
                {
                    int cY = it.first, cX = it.second;
                    board[cY][cX] = 0;
                }

                destroyed[c] += (int)tmp.size();
                chk = true;
            }
        }

        if(!chk)
            break;
        
        chk = false;
        move();
    }
}

void destroy(int d, int s)
{
    int y = N / 2, x = N / 2;

    for(int i = 0; i < s; i++)
    {
        y += ddy[d], x += ddx[d];
        board[y][x] = 0;
        cnt--;
    }
}

void init()
{
    int y = N / 2, x = N / 2, ccnt = 1, dir = 0;

    while(true)
    {
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < ccnt; j++)
            {
                y += dy[dir], x += dx[dir];

                marbleOrder.push_back({y, x});
                cnt++;

                if(y == 0 && x == 0)
                    return;
            }
            dir = (dir == 3 ? 0 : dir + 1);
        }
        ccnt++;
    }
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M;
    init();
    for(int i = 0; i < N; i++)
        for(int j = 0; j < N; j++)
            std::cin >> board[i][j];
    
    for(int i = 0, d, s; i < M; i++)
    {
        std::cin >> d >> s;

        destroy(d, s);
        move();
        process();
        setGroup();
    }

    int res = 0;

    for(int i = 1; i <= 3; i++)
        res += destroyed[i] * i;
    std::cout << res << '\n';
    return 0;
}