// DFS + std::set(식 중복, 정렬) + 괄호제거(stack) + backtracking
#include <bits/stdc++.h>

std::vector<int> oIndex, cIndex;
std::set<std::string> S;
std::string str;
bool chk[200];

void dfs(int idx, int cnt)
{
    if(idx == oIndex.size())
    {
        if(cnt > 0)
        {
            std::string tmp = "";
            for(int i = 0; i < str.size(); i++)
                if(chk[i])
                    tmp += str[i];
            S.insert(tmp);
        }
        return;
    }
    dfs(idx + 1, cnt);
    chk[oIndex[idx]] = false;
    chk[cIndex[idx]] = false;
    dfs(idx + 1, cnt + 1);
    chk[oIndex[idx]] = true;
    chk[cIndex[idx]] = true;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> str;
    std::stack<int> stack;

    for(int i = 0; i < str.size(); i++)
    {
        chk[i] = true;

        if(str[i] == '(')
            stack.push(i);
        else if(str[i] == ')')
        {
            int o = stack.top();
            stack.pop();
            oIndex.push_back(o);
            cIndex.push_back(i);
        }
    }

    dfs(0, 0);
    for(auto i : S)
        std::cout << i << '\n';
    return 0;
}  