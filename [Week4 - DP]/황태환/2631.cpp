// 자리를 최소 횟수로 바꾼다 = 가장 길게 오름차순으로 정렬된 부분을 기준으로
// 나머지 값들을 옮기면 된다. LIS 문제라고 할 수 있음
// N의 범위가 작기 때문에 O(N^2)에 해결이 가능할 것 같음 
#include <bits/stdc++.h>

constexpr int size = 201;
int arr[size];
int dp[size];

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N;
    std::cin >> N;

    for(int i = 0; i < N; i++)
        std::cin >> arr[i];

    int res = 0;
    for(int i = 0; i < N; i++)
    {
        dp[i] = 1;
        for(int j = i - 1; j >= 0; j--)
        {
            if(arr[i] > arr[j])
                dp[i] = std::max(dp[i], dp[j] + 1);
            res = std::max(res, dp[i]);            
        }
    }
    std::cout << N - res << '\n';
    return 0;
}