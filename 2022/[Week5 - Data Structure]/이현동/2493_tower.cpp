#include <bits/stdc++.h>
using namespace std;

int N;
vector<pair<int, int>> tower;
stack<pair<int, int>> st;
vector<int> ans;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;
    for (int i = 0; i < N;i++){
        int tmp;
        cin >> tmp;
        tower.push_back({i + 1, tmp});
    }

    st.push(tower[0]);
    ans.push_back(0);
    for (int i = 1; i < N; i++)
    {
        if (!st.empty() && st.top().second > tower[i].second){
            ans.push_back(st.top().first);
            st.push(tower[i]);
        }else{
            while(!st.empty() && st.top().second < tower[i].second)
                st.pop();
            if (st.empty()){
                ans.push_back(0);
                st.push(tower[i]);
            }
            else if (!st.empty()){
                ans.push_back(st.top().first);
                st.push(tower[i]);
            }
        }
    }
    for(auto a : ans)
        cout << a << ' ';
    return 0;
}