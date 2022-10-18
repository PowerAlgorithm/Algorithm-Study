#include <bits/stdc++.h>
using namespace std;

int N;
char ans;

void moo(int n, int k, int l)
{
    int length = l * 2 + k + 3;
    if(n <= 3){
        if(n == 1){
            cout << "m\n";
            exit(0);
        }
        else{
            cout << "o\n";
            exit(0);
        }

    }
    if (n <= (k + 3) + 2 * l)
    {
        if(n > l && n <= l+k+3){
            if(n-l != 1){
                cout << "o\n";
                exit(0);
            }
            else{
                cout << "m\n";
                exit(0);
            }
        }
        else{
            moo(n - (l + k + 3), 1, 3);
        }
    }
    moo(n, k + 1, length);
}

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;
    moo(N, 1, 3);
    return 0;
}
