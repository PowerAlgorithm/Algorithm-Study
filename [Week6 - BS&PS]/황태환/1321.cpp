#include <bits/stdc++.h>

constexpr int size = 5e5 + 1;
int N, M, arr[size];

void update(int i, int v)
{
    while(i <= N)
    {
        arr[i] += v;
        i += i & -i;
    }
}

int query(int i)
{
    int ret = 0;
    while(i)
    {
        ret += arr[i];
        i -= i & -i;
    }
    return ret;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N;
    for(int i = 1, x; i <= N; i++)
    {
        std::cin >> x;
        update(i, x);
    }

    std::cin >> M;
    while(M--)
    {
        int op;
        std::cin >> op;
        if(op == 1)
        {
            int i, a;
            std::cin >> i >> a;
            update(i, a);
        }
        else if(op == 2)
        {
            int i;
            std::cin >> i;

            int low = 1, high = N, res;
            while(low <= high)
            {
                int mid = low + high >> 1;
                if(query(mid) >= i)
                {
                    res = mid;
                    high = mid - 1;
                }
                else
                    low = mid + 1;
            }
            std::cout << res << '\n';
        }
    }
    return 0;
}