// dfs 문젠
#include <bits/stdc++.h>

int n, k, cnt;
std::vector<int> v;

void dfs(int sum)
{
    if(sum > n)
        return;
    
    if(sum == n)
    {
        cnt++;
        if(cnt == k)
        {
            for(int i = 0; i < v.size(); i++)
            {
                if(i == v.size() - 1) 
                    std::cout << v[i];
                else    
                    std::cout << v[i] << "+";
            }
            exit(EXIT_SUCCESS);
        }
        return;
    }
    for(int i = 1; i <= 3; i++)
    {
        v.push_back(i);
        dfs(sum + i);
        v.pop_back();
    }
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> n >> k;
    dfs(0);
    if(cnt == 0 || cnt < k)
        std::cout << -1 << '\n';
    return 0;
}