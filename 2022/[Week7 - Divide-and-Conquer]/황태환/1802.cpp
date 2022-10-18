#include <bits/stdc++.h>

bool chk(std::string str, int s, int e)
{
    if(s >= e)
        return true;
    int l = s, r = e;
    while(l < r)
    {
        if(str[l++] == str[r--])
            return false;
    }
    return chk(str, s, r - 1);
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int T;
    std::cin >> T;

    while(T--)
    {
        std::string str;
        std::cin >> str;

        if((int)str.size() % 2 == 0)
        {
            std::cout << "NO\n";
            continue;
        }
        std::cout << (chk(str, 0, (int)str.size() - 1) ? "YES\n" : "NO\n");
    }
    return 0;
}