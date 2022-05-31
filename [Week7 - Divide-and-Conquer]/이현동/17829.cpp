#include <bits/stdc++.h>
using namespace std;

int N;
int arr[1025][1025];
int ans = 0;

int falling(int x, int y, int size)
{
    if (size == 2)
    {
        vector<int> res;
        for (int i = x; i < x + size; i++)
        {
            for (int j = y; j < y + size;j++){
                res.push_back(arr[i][j]);
            }
        }
        sort(res.begin(), res.end());
        return res[2];
    }
    vector<int> res;
    res.push_back(falling(x, y, size / 2));
    res.push_back(falling(x + size / 2, y, size / 2));
    res.push_back(falling(x , y + size / 2, size / 2));
    res.push_back(falling(x + size / 2, y + size / 2, size / 2));
    sort(res.begin(), res.end());
    return res[2];
}

int main()
{


    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;
    for (int i = 0; i < N;i++){
        for (int j = 0; j < N;j++){
            cin >> arr[i][j];
        }
    }
    
    cout << falling(0, 0, N);
    return 0;
}