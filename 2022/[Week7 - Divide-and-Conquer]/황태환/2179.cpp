#include <bits/stdc++.h>
using psi = std::pair<std::string, int>;
using pii = std::pair<int, int>;

constexpr int size = 2e4 + 1;
std::string s[size];
psi p[size];
pii a, b;

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N;
    std::cin >> N;

    for(int i = 0; i < N; i++)
    {
        std::cin >> s[i];
        p[i] = {s[i], i};
    }

    std::sort(p, p + N);
    std::unique(p, p + N);

    int x, prev = -1, idx = -1;
    for(int i = 1; i < N; i++)
    {
        for(x = 0; x < (int)p[i - 1].first.size() && x < (int)p[i].first.size() && p[i - 1].first[x] == p[i].first[x]; x++);
        if(x != prev)
        {
            prev = x;
            b = {p[i - 1].second, p[i].second};
        }
        else if(b.second > p[i].second)
            b.second = p[i].second;
        if(b.first > b.second)
            std::swap(b.first, b.second);
        if(idx < x || idx == x && a > b)
        {
            idx = x;
            a = b;
        }
    }
    std::cout << s[a.first] << '\n' << s[a.second] << '\n';
    return 0;
}