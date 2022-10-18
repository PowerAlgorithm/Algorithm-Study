#include <bits/stdc++.h>

constexpr int size = 1024;
int arr[size][size];

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N;
    std::cin >> N;
    
    for(int i = 0; i < N; i++)
        for(int j = 0; j < N; j++)
            std::cin >> arr[i][j];
    
    while(N != 1)
    {
        std::vector<std::vector<int>> tmp(N / 2, std::vector<int>(N / 2, 0));
        for(int j = 0; j < N; j += 2)
        {
            for(int i = 0; i < N; i += 2)
            {
                int tmpY = j;
                int tmpX = i;

                std::vector<int> v(4);
                v[0] = arr[tmpY][tmpX];
                v[1] = arr[tmpY + 1][tmpX];
                v[2] = arr[tmpY][tmpX + 1];
                v[3] = arr[tmpY + 1][tmpX + 1];

                std::sort(v.begin(), v.end());
                tmp[j >> 1][i >> 1] = v[2];
            }
        }

        N >>= 1;

        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                arr[i][j] = tmp[i][j];
    }
    std::cout << arr[0][0] << '\n';
    return 0;
}