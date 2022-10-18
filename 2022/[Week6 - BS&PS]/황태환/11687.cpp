#include <bits/stdc++.h>

int func(int M)
{
    int l = 1, r = 1e9, mid, cnt, res;
    bool chk = false;
    while(l <= r)
    {
        cnt = 0;
        mid = (l + r) >> 1;
        for(int i = 5; i <= mid; i *= 5)
            cnt += mid / i;
        if(M <= cnt)
        {
            if(M == cnt)
            {
                chk = true;
                res = mid;
            }
            r = mid - 1;
        }
        else
        {
            l = mid + 1;
        }
    }
    if(!chk)
        return -1;
    else
        return res;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int M;
    std::cin >> M;

    std::cout << func(M) << '\n';
    return 0;
}