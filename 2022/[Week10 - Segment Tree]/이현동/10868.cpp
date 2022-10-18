#include <bits/stdc++.h>
#define INF 1000000001
using namespace std;
typedef long long ll;

int N, M;
ll arr[100001];
ll *tree;

ll init(int node, int start, int end){
    if(start == end)
        return tree[node] = arr[start];
    int mid = (start + end) / 2;
    ll l = init(2 * node, start, mid);
    ll r = init(2 * node + 1, mid + 1, end);
    tree[node] = min(l, r);
    return tree[node];
}

ll minval(int node, int start, int end, int left, int right)
{
    if(left > end || right < start)
        return INF;
    if(left<=start && end<=right) return tree[node];
    int mid = (start + end) / 2;    
    return min(minval(2*node,start,mid,left,right), minval(2*node+1,mid+1,end,left,right));
}


int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> M;
        int height = ceil(log2(N));
    tree = new ll[1<<(height+1)];
    for (int i = 0; i < N;i++){
        cin >> arr[i];
    }
    init(1, 0, N-1);
    ll a, b;
    for (int i = 0; i < M;i++){
        cin >> a >> b;
        cout << minval(1, 0, N - 1, a - 1, b - 1) << '\n';
    }

        return 0;
}