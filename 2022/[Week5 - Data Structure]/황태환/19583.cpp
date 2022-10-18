#include <bits/stdc++.h>

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::string S, E, Q;
    std::cin >> S >> E >> Q;
    std::set<std::string> in, acc;

    while(true)
    {
        std::string a, b;
        std::cin >> a >> b;
        if(a == "" && b == "")
            break;
        if(a <= S)
        {
            in.insert(b);
            continue;
        }
        if(E <= a && a <= Q)
        {
            if(in.find(b) != in.end())
            {
                acc.insert(b);
                continue;
            }
        }
    }
    std::cout << acc.size() << '\n';
    return 0;
}