#include <bits/stdc++.h>
using namespace std;

int N;
int post[100001];
int in[100001];
int val[100001];

void preOrder(int inStart, int inEnd, int postStart, int postEnd)
{
    if(inStart > inEnd || postStart > postEnd)
        return;
    int root = val[post[postEnd]];
    int lsize = root - inStart;
    cout << in[root] << ' ';

    preOrder(inStart, root - 1, postStart, postStart + lsize - 1);
    preOrder(root + 1, inEnd, postStart + lsize, postEnd - 1);
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i = 1; i <= N;i++){
        cin >> in[i];
        val[in[i]] = i;
    }
    for (int i = 1; i <= N;i++)
        cin >> post[i];
    preOrder(1, N, 1, N);
    return 0;
}