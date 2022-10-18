#include <bits/stdc++.h>
#include <cmath>
using namespace std;
typedef long long ll;

int N, M, K;
int a, b;
ll c;
ll arr[1000001];
ll *tree;

ll init(int node, int start, int end){
    if(start == end)
        return tree[node] = arr[start];
    int mid = (start + end) / 2;
    return tree[node] = init(2*node, start, mid) + init(2*node + 1, mid + 1, end);
}

ll sum(int node, int start, int end, int left, int right)
{
    if(left > end || right < start)
        return 0;
    if(left<=start && end<=right) return tree[node];
    int mid = (start + end) / 2;    
    return sum(2*node,start,mid,left,right) + sum(2*node+1,mid+1,end,left,right);
}

void update(int node, int start, int end, int idx, ll diff)
{
    if(idx < start || idx > end)
        return;
    tree[node] += diff;
    if(start== end)
        return;
    int mid = (start + end) / 2;
    update(node*2, start, mid, idx, diff);
    update(node*2+1, mid + 1, end, idx, diff);
}

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M >> K;
    int height = ceil(log2(N));
    tree = new ll[1<<(height+1)];
    for (int i = 0; i < N;i++){
        cin >> arr[i];
    }
    init(1, 0, N-1);
    for (int i = 0; i < M + K; i++)
    {
        cin >> a >> b >> c;
        if(a==1){
            ll diff = c - arr[b - 1];
            arr[b - 1] = c;
            update(1, 0, N - 1, b - 1, diff);
        }
        else if(a==2){
            cout << sum(1, 0, N - 1, b - 1, c - 1) << '\n';
        }
    }

        return 0;
}