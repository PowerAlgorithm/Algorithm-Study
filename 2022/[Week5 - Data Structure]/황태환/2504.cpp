#include <bits/stdc++.h>
using ll = long long;

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::stack<char> S;
    std::string str;

    std::cin >> str;

    ll res = 0;
    int tmp = 1; 
    bool chk = false; // 올바른 입력인지 판단

    for(int i = 0; i < str.size(); i++)
    {
        // 여는 괄호가 나올 때는 스택에 push
        if(str[i] == '(') // 소괄호일 때는 *2
        {
            tmp *= 2;
            S.push('(');
        }
        else if(str[i] == '[') // 중괄호일 때는 *3;
        {
            tmp *= 3;
            S.push('[');
        }
        else if(str[i] == ')' && (S.empty() || S.top() != '(')) // 불가능한 경우
        {
            chk = true;
            break;
        }
        else if(str[i] == ']' && (S.empty() || S.top() != '[')) // 불가능한 경우
        {
            chk = true;
            break;
        }
        // 닫는 경우가 나올 땐 스택에서 pop
        else if(str[i] == ')')
        {
            if(str[i - 1] == '(')
                res += tmp;
            S.pop();
            tmp /= 2;
        }
        else if(str[i] == ']')
        {
            if(str[i - 1] == '[')
                res += tmp;
            S.pop();
            tmp /= 3;
        }
    }
    if(chk || !S.empty())
        std::cout << "0\n";
    else
        std::cout << res << '\n';
    return 0;
}