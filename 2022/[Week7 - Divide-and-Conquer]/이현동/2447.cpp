#include <bits/stdc++.h>
using namespace std;

int N;

vector<string> printingStarBammHaneulUiPearl(int n)
{
    if(n == 1){
        return {"*"};
    }
    
    vector<string> stars = printingStarBammHaneulUiPearl(n / 3);
    
    vector<string> tmp;
    for (int i = 0; i < stars.size();i++){
        string t = "";
        for (int j = 0; j < 3;j++)
            t += stars[i];
        tmp.push_back(t);
    }
    
    for (auto a : tmp)
        cout << a << '\n';
    for (int i = 0; i < stars.size(); i++)
    {
        string t = "";
        t += stars[i];
        for (int j = 0; j < n / 3;j++)
            t += ' ';
        t += stars[i];
        tmp.push_back(t);
    }
    for (int i = 0; i < stars.size();i++){
        string t = "";
        for (int j = 0; j < 3;j++)
            t += stars[i];
        tmp.push_back(t);
    }
    
    return tmp;
}
int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;
    vector<string> ans = printingStarBammHaneulUiPearl(N);

    for(auto a : ans)
        cout << a << '\n';
    return 0;
}