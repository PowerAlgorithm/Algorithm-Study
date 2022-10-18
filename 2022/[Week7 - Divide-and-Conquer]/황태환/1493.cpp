#include <bits/stdc++.h>
using ll = long long;
using pii = std::pair<int, int>;

pii arr[21];

int L, W, H, N, res;
bool chk = true;

void func(int l, int w, int h, int idx)
{
    if(idx == -1)
    {
        if((ll)l * w * h > 0)
            chk = false;
        return;
    }
    auto& it = arr[idx];
    if(it.first > l || it.first > w || it.first > h || !it.second)
    {
        func(l, w, h, idx - 1);
        return;
    }

    --it.second;
    ++res;

    func(l - it.first, w, h, idx);
    func(it.first, it.first, h - it.first, idx);
    func(it.first, w - it.first, h, idx);
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> L >> W >> H >> N;

    for(int i = 0, a, b; i < N; i++)
    {
        std::cin >> a >> b;
        arr[i].first = std::pow(2, a);
        arr[i].second = b;
    }

    func(L, W, H, N - 1);

    if(chk)
        std::cout << res << '\n';
    else
        std::cout << -1 << '\n';
    return 0;
}