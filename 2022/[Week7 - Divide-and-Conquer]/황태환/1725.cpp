#include <bits/stdc++.h>

constexpr int size = 1e5 + 1;
int arr[size];

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N;
    std::cin >> N;

    for(int i = 1; i <= N; i++)
        std::cin >> arr[i];

    std::stack<int> S;

    S.push(0);
    int res = 0;
    for(int i = 1; i <= N + 1; i++)
    {
        while(!S.empty() && arr[S.top()] > arr[i])
        {
            int tmp = S.top();
            S.pop();
            res = std::max(res, arr[tmp] * (i - S.top() - 1));
        }
        S.push(i);
    }
    std::cout << res << '\n';
    return 0;
}