#include <bits/stdc++.h>
using namespace std;

int N;
int arr[101];
int tmp[1001];

void isPrime(int x)
{
    for (int i = 0; i <= x;i++)
        tmp[i] = i;
    tmp[1] = 0;

    for (int i = 2; i <= x; i++)
    {
        if(tmp[i] == 0)
            continue;
        for (int j = i+i; j <= x; j += i)
            tmp[j] = 0;
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    int cnt = 0;

    cin >> N;
    for (int i = 0; i < N;i++)
        cin >> arr[i];

    isPrime(1000);

    for (int i = 0; i < N;i++){
        if (tmp[arr[i]] != 0)    
            cnt++;
        
    }
    cout << cnt;
    return 0;
}