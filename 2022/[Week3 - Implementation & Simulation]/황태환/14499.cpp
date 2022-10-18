#include <bits/stdc++.h>

constexpr int size = 21;
int N, M, y, x, k;
int board[size][size];
int order[1001];
std::vector<int> dice(7);

const int dx[] = {1, -1, 0, 0};
const int dy[] = {0, 0, -1, 1};

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M >> y >> x >> k;

    for(int i = 0; i < N; i++)
        for(int j = 0; j < M; j++)
            std::cin >> board[i][j];
    for(int i = 0; i < k; i++)
        std::cin >> order[i];

    for(int i = 0; i < k; i++)
    {
        int p = dy[order[i] - 1], q = dx[order[i] - 1];
        if(y + p < 0 || y + p >= N || x + q < 0 || x + q >= M)
            continue;
        
        y += p, x += q;
        
        if(order[i] == 1)
            dice = {0, dice[4], dice[2], dice[1], dice[6], dice[5], dice[3]};
        else if(order[i] == 2)
            dice = {0, dice[3], dice[2], dice[6], dice[1], dice[5], dice[4]};
        else if(order[i] == 3)
            dice = {0, dice[5], dice[1], dice[3], dice[4], dice[6], dice[2]};
        else if(order[i] == 4)
            dice = {0, dice[2], dice[6], dice[3], dice[4], dice[1], dice[5]};
        
        std::cout << dice[1] << '\n';

        if(board[y][x] == 0)
            board[y][x] = dice[6];
        else
        {
            dice[6] = board[y][x];
            board[y][x] = 0;
        }
    }
    return 0;
}