#include <bits/stdc++.h>
using namespace std;

int ans = 0;
int N, r, c;

void searchZ(int x, int y, int size)
{
    if (x == r && y == c)
    {
        cout << ans;
        return;
    }
    if (x <= r && r < x + size && y <= c && c < y + size)
    {
        
        searchZ(x, y, size / 2);           //제 1사분면
        searchZ(x, y + size / 2, size / 2);      //제 2사분면
        searchZ(x + size / 2, y, size / 2);      //제 3사분면
        searchZ(x + size / 2, y + size / 2, size / 2); //제 4사분면
    }
    else
    {
        ans += size * size;
    }
}
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> r >> c;

    searchZ(0, 0, (1 << N));

    return 0;
}