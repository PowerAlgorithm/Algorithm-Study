#include <bits/stdc++.h>
using namespace std;

int M, N;
vector<int> cookies;

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> M >> N;

    for (int i = 0; i < N; i++){
        int tmp;
        cin >> tmp;
        cookies.push_back(tmp);
    }

    sort(cookies.begin(), cookies.end());

    int l = 1, r = cookies[N-1], mid;
    while (l <= r)
    {
        int cnt = 0;
        mid = (l + r)/2;
        for (int i = 0; i < N;i++)
            cnt += (cookies[i] / mid);

        if(cnt >= M)
            l = mid + 1;
        else
            r = mid - 1;
        
    }

    cout << l-1;

    return 0;
}