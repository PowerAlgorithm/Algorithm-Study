#include <bits/stdc++.h>
using namespace std;

int N, M;
int arr[100001];
vector<int> ans;


int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    cin >> arr[0];
    for (int i = 1; i < N; i++)
    {
        cin >> arr[i];
        arr[i] += arr[i - 1];
    }

    for (int i = 0; i < M;i++){
        int s, e;
        cin >> s >> e;
        if(s == 1)
            ans.push_back(arr[e-1]);
        else
            ans.push_back(arr[e-1] - arr[s - 2]);
    }
    for(auto a: ans)
        cout << a << '\n';

    return 0;
}