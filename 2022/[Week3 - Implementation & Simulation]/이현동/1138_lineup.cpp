#include <bits/stdc++.h>
using namespace std;

int N;
int line[11];
int loc[11];

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;
    
    for (int i = 0; i < N;i++)
        cin >> line[i];
    fill(&loc[0], &loc[11], -1);

    for (int i = 0; i < N; i++)
    {
        int cnt = 0;
        for (int j = 0; j < N; j++)
        {
            if (cnt == line[i] && loc[j] == -1)
            {
                loc[j] = i + 1;
                break;
            }
            else if (loc[j] == -1)
                cnt++;
        }
    }
    for (int i = 0; i < N;i++)
        cout << loc[i] << ' ';

    return 0;
}