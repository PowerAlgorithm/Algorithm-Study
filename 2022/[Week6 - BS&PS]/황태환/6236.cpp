#include <bits/stdc++.h>

constexpr int size = 1e5 + 1;
int arr[size];

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N, M, low = 0, high = 0;
    std::cin >> N >> M;

    for(int i = 0; i < N; i++)
    {
        std::cin >> arr[i];
        low = std::max(low, arr[i]);
        high += arr[i];
    }

    int mid = 0;
    while(low <= high)
    {
        mid = low + high >> 1;
        int cnt = 1, tmp = mid;

        for(int i = 0; i < N; i++)
        {
            tmp -= arr[i];
            if(tmp <= 0)
            {
                tmp = mid - arr[i];
                cnt++;
            }
        }

        if(cnt > M)
            low = mid + 1;
        else
            high = mid - 1;
    }
    std::cout << mid << '\n';
    return 0;
}