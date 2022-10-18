#include <bits/stdc++.h>

constexpr int size = 101;
int N, x, y, d, g;
int arr[size][size];
std::vector<int> dir;

const int dy[] = {0, -1, 0, 1};
const int dx[] = {1, 0, -1, 0};

void func()
{
    int sz = (int)dir.size();
    for(int i = sz - 1; i >= 0; i--)
    {
        int nD = (dir[i] + 1) % 4;
        y += dy[nD];
        x += dx[nD];
        arr[y][x] = 1;
        dir.push_back(nD);
    }
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N;

    while(N--)
    {
        dir.clear();
        std::cin >> x >> y >> d >> g;

        arr[y][x] = 1;
        y += dy[d];
        x += dx[d];
        arr[y][x] = 1;
        
        dir.push_back(d);

        while(g--)  
            func();
    }

    int cnt = 0;
    for(int i = 0; i < size; i++)
        for(int j = 0; j < size; j++)
            if(arr[i][j] == 1 && arr[i][j + 1] == 1 && arr[i + 1][j] == 1 && arr[i + 1][j + 1] == 1)
                cnt++;
    std::cout << cnt << '\n';
    return 0;
}