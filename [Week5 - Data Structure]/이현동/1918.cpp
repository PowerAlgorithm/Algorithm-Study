#include <bits/stdc++.h>
using namespace std;

stack<char> st;
vector<char> ans;
map<char, int> priority;
string expr;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> expr;
    priority.insert({'*', 3});
    priority.insert({'/', 3});
    priority.insert({'+', 2});
    priority.insert({'-', 2});
    priority.insert({'(', 1});
    for (int i = 0; i < expr.length(); i++)
    {
        if (expr[i] == '(')
            st.push('(');
        else if (expr[i] == ')')
        {
            while(st.top() != '('){
                ans.push_back(st.top());
                st.pop();
            }
            st.pop();
        }
        else if (isalpha(expr[i]))
            ans.push_back(expr[i]);
        else
        {
            while(!st.empty() && priority[expr[i]] <= priority[st.top()]){
                ans.push_back(st.top());
                st.pop();
            }
            st.push(expr[i]);
        }
    }
    while(!st.empty()){
        ans.push_back(st.top());
        st.pop();
    }

    for(auto a : ans)
        cout << a;

    return 0;
}