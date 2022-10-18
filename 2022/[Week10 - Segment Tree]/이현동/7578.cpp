#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int first[500001];
int second[1000001];
ll *tree;

ll sum(int node, int start, int end, int left, int right){
    if(left>end || right<start) return 0;
    if(left<=start && end<=right) return tree[node];
    int mid = start + (end-start)/2;
    return sum(node*2,start,mid,left,right) + sum(node*2+1,mid+1,end,left,right);
}

void update(int node, int start, int end, int idx){
    if(start<=idx && idx<=end){
        tree[node]+=1;
        if(start!=end){
            int mid = start + (end-start)/2;
            update(node*2,start,mid,idx);
            update(node*2+1,mid+1,end,idx);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false); 
    cin.tie(NULL); 
    int num,height,val;
    ll res=0;

    cin>>num;
    height = ceil(log2(num));
    tree = new ll[1<<(height+1)];

    for(int i=1;i<=num;i++)
        cin>>first[i];
    for(int i=1;i<=num;i++){
        cin>>val;
        second[val] = i;
    }

    for(int i=1;i<=num;i++){
        int temp = second[first[i]];
        ll tmp = sum(1,1,num,temp+1,num);
        res+=tmp;
        update(1, 1, num, temp);
    }
    cout<<res;
    return 0;
}