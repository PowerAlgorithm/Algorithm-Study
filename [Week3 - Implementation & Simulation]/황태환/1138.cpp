#include <bits/stdc++.h>

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N;
    std::cin >> N;

    std::vector<int> v(N);
    for(int i = 0, x; i < N; i++)
    {
        std::cin >> x;
        for(int j = 0; j < N; j++)
        {
            if(!x && v[j] == 0)
            {
                v[j] = i + 1;
                break;
            }
            else if(v[j] == 0)
                x--;
        }
    }
    for(auto it : v)
        std::cout << it << ' ';
    std::cout << '\n';
    return 0;
}