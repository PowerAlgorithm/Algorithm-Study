#include <bits/stdc++.h>
using namespace std;

int N, K;
vector<int> tmp;
int cnt = 0;
bool flag = false;

void dfs(int tot)
{
    if (tot > N)
        return;
    if (tot == N)
    {
        cnt++;
        if (cnt == K)
        {
            for (int i = 0; i < tmp.size();i++)
            {
                cout << tmp[i];
                if (i != tmp.size()-1)
                    cout << '+';
            }
            flag = true;
        }
        return;
    }
    for (int i = 1; i < 4;i++){
        tmp.push_back(i);
        dfs(tot + i);
        tmp.pop_back();
    }
}

int main()
{


    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> K;
    dfs(0);
    if (!flag)
        cout << "-1";
    return 0;
}