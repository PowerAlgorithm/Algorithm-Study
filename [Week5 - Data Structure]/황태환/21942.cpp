#include <bits/stdc++.h>
using ll = long long;

struct {
    int month[13] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    ll operator() (std::string& s)
    {
        ll d = std::stoll(s.substr(0, 3));
        ll h = std::stoll(s.substr(4, 2));
        ll m = std::stoll(s.substr(7, 2));
        return d * 24 * 60 + h * 60 + m;
    }
    ll operator() (std::string& a, std::string& b)
    {
        ll M = std::stoll(a.substr(5, 2));
        ll D = std::stoll(a.substr(8, 2));
        ll h = std::stoll(b.substr(0, 2));
        ll m = std::stoll(b.substr(3, 2));
        return (std::accumulate(month, month + M, 0) + D) * 24 * 60 + h * 60 + m;
    }
} parsing;

struct node{
    ll val;
    std::map<std::string, ll> Mp;
};

std::map<std::string, node> I;

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    ll N, F;
    std::string L;

    std::cin >> N >> L >> F;
    ll conv = parsing(L);

    while(N--)
    {
        std::string s1, s2, a, b;
        std::cin >> s1 >> s2 >> a >> b;

        ll tmp = parsing(s1, s2);
        auto& curr = I[b];

        if(curr.Mp.count(a))
        {
            curr.val += std::max<ll>((tmp - curr.Mp[a] - conv) * F, 0);
            curr.Mp.erase(a);
        }
        else
            curr.Mp[a] = tmp;
    }

    bool chk = false;
    for(auto it : I)
        if(it.second.val)
            chk = true;
    
    if(!chk)
        std::cout << -1 << '\n';
    else
    {
        for(auto it : I)
            if(it.second.val)
                std::cout << it.first << " " << it.second.val << '\n';
    }
    return 0;
}