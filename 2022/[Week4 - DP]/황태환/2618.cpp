#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 1e3 + 1;
int N, W;
pii incident[size];
int dp[size][size], res[size][size];

int getDist(pii A, pii B) // 맨해튼 거리
{
    return std::abs(A.first - B.first) + std::abs(A.second - B.second);
}

int func(int a, int b)
{
    int currIncident = std::max(a, b) + 1;
    if(currIncident == W + 2)
        return 0;
    
    int& ret = dp[a][b];
    if(ret != -1)
        return ret;
    
    int d1 = func(currIncident, b) + getDist(incident[a], incident[currIncident]);
    int d2 = func(a, currIncident) + getDist(incident[b], incident[currIncident]);
    res[a][b] = (d1 < d2 ? 1 : 2);

    return ret = std::min(d1, d2);
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    memset(dp, -1, sizeof(dp));
    std::cin >> N >> W;

    incident[0] = {1, 1}, incident[1] = {N, N}; // 경찰차의 초기 위치 (사건으로 생각)
    for(int i = 2; i <= W + 1; i++)
        std::cin >> incident[i].first >> incident[i].second; // 사건 위치

    std::cout << func(0, 1) << '\n';

    for(int i = 0, j = 1; std::max(i, j) + 1 <= W + 1;)
    {
        std::cout << res[i][j] << '\n';
        if(res[i][j] == 1)
            i = std::max(i, j) + 1;
        else
            j = std::max(i, j) + 1;
    }
    return 0;
}