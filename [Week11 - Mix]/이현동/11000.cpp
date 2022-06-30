#include <bits/stdc++.h>
using namespace std;
using pii = std::pair<int, int>;

int N;
vector<pii> sch;
priority_queue<int> pq;

bool comp(const pii &a, const pii &b)
{
    return (a.first < b.first);
}



int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;

    for(int i = 0;i < N;i++){
        int s, t;
        cin >> s >> t;
        sch.push_back(make_pair(s, t));
    }
    
    sort(sch.begin(), sch.end(), comp);
    
    int ans;
    pq.push(-sch[0].second);

    for(int i = 1;i < N;i++){
        if (-pq.top() > sch[i].first)
            pq.push(-sch[i].second);
        else{
            pq.pop();
            pq.push(-sch[i].second);
        }
    }

    cout << pq.size();
    return 0;
}

