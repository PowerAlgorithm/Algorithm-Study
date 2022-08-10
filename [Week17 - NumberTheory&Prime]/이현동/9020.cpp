#include <bits/stdc++.h>
using namespace std;

int N;
int arr[10001];
int tmp[10001];
/*
8 = 1 7, 2 6, 3 5, 4 4,
*/

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
    cin >> N;
    for (int i = 0; i < N;i++)
        cin >> arr[i];
    
    //make prime Number set
    isPrime(10000);

    for (int i = 0; i < N; i++)
    {
        int a, b;
        for (int j = 2; j <= arr[i] / 2; j++)
        {
            if(tmp[arr[i]-j] != 0 && tmp[j] != 0){
                b = arr[i] - j;
                a = j;
            }
        }
        cout << a << ' ' << b << '\n';
    }
        return 0;
}