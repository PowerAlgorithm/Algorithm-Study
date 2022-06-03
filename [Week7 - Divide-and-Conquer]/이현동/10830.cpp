#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int N;
ll B;
vector<vector<ll>> A(6, vector<ll>(6));

vector<vector<ll>> matMul(vector<vector<ll>> A, vector<vector<ll>>B)
{
    vector<vector<ll>> C(6, vector<ll>(6, 0));
    for (int i = 0; i < N;i++){
        for (int j = 0; j < N;j++){
            C[i][j] = 0;
            for (int k = 0; k < N; k++)
            {
                C[i][j] += A[i][k] * B[k][j];
            }
            C[i][j] %= 1000;
        }
    }
    return C;
}

vector<vector<ll>> pow(vector<vector<ll>> M, ll n)
{
    if (n == 1){
        vector<vector<ll>> tmp(6, vector<ll>(6, 0));
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N;j++){
                tmp[i][j] = M[i][j];
                tmp[i][j] %= 1000;
            }
        }
        return tmp;
    }

    vector<vector<ll>> tmp = pow(M, n / 2);
    vector<vector<ll>> res = matMul(tmp, tmp);
    if (n % 2 == 0)
        return res;
    else
        return matMul(M, res);
}

int main()
{


    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> B;

    for (int i = 0; i < N;i++){
        for (int j = 0; j < N;j++){
            cin >> A[i][j];
        }
    }

    vector<vector<ll>> res = pow(A, B);

    for (int i = 0; i < N;i++)
    {
        for (int j = 0; j < N;j++)
        {
            cout << res[i][j] << ' ';
        }
        cout << '\n';
    }
    return 0;
}