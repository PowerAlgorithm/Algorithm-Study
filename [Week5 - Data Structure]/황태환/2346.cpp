// 문제에 나온 설명대로 구현해주면 되는 문제
#include <bits/stdc++.h>
using pii = std::pair<int, int>;

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N;
    std::cin >> N;

    std::vector<pii> v;

    for(int i = 1, x; i <= N; i++)
    {
        std::cin >> x;
        v.push_back({i, x});
    }

    while(!v.empty())
    {
        std::cout << v.front().first << " ";
        int curr = v.front().second;
        v.erase(v.begin());

        if(curr < 0)
        {
            for(int j = 0; j < std::abs(curr); j++)
            {
                v.insert(v.begin(), v.back());
                v.erase(v.end());
            }
        }
        else
        {
            for(int j = 0; j < curr - 1; j++)
            {
                v.push_back(v.front());
                v.erase(v.begin());
            }
        }
    }
    return 0;
}