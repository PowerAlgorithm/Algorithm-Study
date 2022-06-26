#include <bits/stdc++.h>
#define INF 1000000001
using namespace std;
typedef long long ll;
using pll = std::pair<ll, ll>;

int N, M;
ll arr[100001];
ll *tree1;
ll *tree2;

void init(int node, int start, int end){
    if(start == end){
        tree1[node] = tree2[node] = arr[start];
        return;
    }
    int mid = (start + end) / 2;
    init(2 * node, start, mid);
    init(2 * node + 1, mid + 1, end);
    tree1[node] = min(tree1[node * 2], tree1[node * 2 + 1]);
    tree2[node] = max(tree2[2 * node], tree2[2 * node + 1]);
    return;
}

pll query(int node, int start, int end, int left, int right)
{
    if(left > end || right < start)
        return {INF, 0};
    if(left<=start && end<=right) return {tree1[node], tree2[node]};
    int mid = (start + end) / 2;
    pll l = query(2*node, start, mid, left, right);
    pll r = query(2*node+1, mid + 1, end, left, right);
    return {min(l.first, r.first), max(l.second, r.second)};
}

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> M;
    int height = ceil(log2(N));
    tree1 = new ll[1<<(height+1)];
    tree2 = new ll[1<<(height+1)];
    for (int i = 0; i < N;i++){
        cin >> arr[i];
    }
    init(1, 0, N-1);
    
    ll a, b;
    for (int i = 0; i < M;i++){
        cin >> a >> b;
        pll tmp = query(1, 0, N - 1, a - 1, b - 1);
        cout << tmp.first << ' ' << tmp.second << '\n';
    }

        return 0;
}