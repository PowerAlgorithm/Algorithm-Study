#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int N, T;
int arr[100001];
ll *tree;
ll a, b, c;

int minidx(int x, int y) // 더 작은 값의 인덱스를 반환하는 함수
{
	// 유효하지 않은 경우
	if (x == -1) return y;
	if (y == -1) return x;
	// 같은 경우 더 작은 인덱스 리턴
	if (arr[x] == arr[y]) return x < y ? x : y;
	else return arr[x] <= arr[y] ? x : y;
}

ll init(int node, int start, int end){
    if(start == end){
        tree[node] = start;
        return tree[node];
    }
    int mid = (start + end) / 2;
    return tree[node] = minidx(init(2*node, start, mid), init(2*node + 1, mid + 1, end));
}

ll query(int node, int start, int end, int left, int right)
{
    if(left > end || right < start)
        return -1;
    if(left<=start && end<=right)
        return tree[node];
    int mid = (start + end) / 2;
    return minidx(query(2*node,start,mid,left,right), query(2*node+1,mid+1,end,left,right));
}

ll update(int node, int start, int end, int idx)
{
    if(idx < start || idx > end)
        return tree[node];
    if(start== end)
        return tree[node];
    int mid = (start + end) / 2;
    return tree[node] = minidx(update(node*2, start, mid, idx), update(node*2+1, mid + 1, end, idx));
}

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    int height = ceil(log2(N));
    tree = new ll[1<<(height+1)];
    for (int i = 0; i < N;i++){
        cin >> arr[i];
    }
    init(1, 0, N-1);
    cin >> T;
    for (int i = 0; i <T; i++)
    {
        cin >> a >> b >> c;
        if(a==1){
            arr[b - 1] = c;
            update(1, 0, N - 1, b - 1);
        }
        else if(a==2){
            cout << query(1, 0, N - 1, b - 1, c - 1)+1 << '\n';
        }
    }

        return 0;
}