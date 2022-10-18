#include <bits/stdc++.h>
#define MOD 1000000007
using namespace std;
typedef long long ll;

int N, M, K;
int a, b;
ll c;
ll arr[1000001];
ll *tree;

ll init(int node, int start, int end){
    if(start==end) return tree[node] = arr[start];
    int mid = start + (end-start)/2;
    return tree[node] = (init(2*node,start,mid) * init(2*node+1,mid+1,end))%MOD;
}

ll mul(int node, int start, int end, int left, int right){
    if(left>end || right<start) return 1;
    if(left<=start && end<=right) return tree[node];
    int mid = start + (end-start)/2;
    return (mul(node*2, start, mid, left, right)*mul(node*2+1, mid+1,end,left,right))%MOD;
}


ll update(int node, int start, int end, int idx, ll val){
    if(idx>end || idx<start) return tree[node];
    if(start==end) return tree[node]=val;
    int mid = start + (end-start)/2;
    return tree[node] = (update(2*node,start,mid,idx,val)*update(2*node+1,mid+1,end,idx,val))%MOD;
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
            arr[b - 1] = c;
            update(1, 0, N - 1, b - 1, c);
        }
        else if(a==2){
            cout << mul(1, 0, N - 1, b - 1, c - 1)%MOD << '\n';
        }
    }

        return 0;
}