#include <bits/stdc++.h>

constexpr int size = 1e3 + 1;
int M, N;
int map[size][size];
bool chk; // 침투 여부

void func(int y, int x)
{
    map[y][x] = 1; // 지나간 자리이기 때문에 1로 변경
    if(y == M) // 가장 아래까지 도달하면
    {
        chk = true; // 침투 가능
        return;
    }

    if(y >= 2 && map[y - 1][x] == 0)
        func(y - 1, x);
    if(map[y + 1][x] == 0)
        func(y + 1, x);
    if(x >= 2 && map[y][x - 1] == 0)
        func(y, x - 1);
    if(x <= N - 1 && map[y][x + 1] == 0)
        func(y, x + 1);
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> M >> N;

    char c;
    for(int i = 1; i <= M; i++)
    {
        for(int j = 1; j <= N; j++)
        {
            std::cin >> c;
            map[i][j] = c - '0';
        }
    }

    for(int i = 1; i <= N; i++)
        if(map[1][i] == 0) // 안쪽으로 들어갈 수 있다면
            func(1, i); // 플러드 필 수행
    
    std::cout << (chk ? "YES" : "NO") << '\n';
    return 0;
}