#include <bits/stdc++.h>
using namespace std;
using pii = std :: pair<int, int>;

int N, M;
vector<int> arr[1001];
priority_queue<pii,vector<pii>,greater<pii>> pq;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M;
    for (int i = 0; i < N;i++){
        for (int j = 0; j < M;j++){
            int tmp;
            cin >> tmp;
            arr[i].push_back(tmp);
        }
    }
    int ans = INT_MAX;
    int maxV = 0;
    vector<int> idx(N,0);

    for (int i = 0; i < N;i++){
        sort(arr[i].begin(), arr[i].end());
        maxV = max(maxV, arr[i][0]);
        pq.push({arr[i][0], i});
    }
    for (int i = 0; i < N * M - N;i++){
        pii curr = pq.top();
        int midx = curr.second;
        ans = min(ans, maxV - curr.first);


        if(idx[midx] == N-1)
            break;
        else{
            pq.pop();
            idx[midx]++;
            maxV = max(maxV, arr[midx][idx[midx]]);
            pq.push({arr[midx][idx[midx]], midx});
        }
    }
    cout << ans;
    return 0;
}