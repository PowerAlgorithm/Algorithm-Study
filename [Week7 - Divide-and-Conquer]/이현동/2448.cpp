#include <bits/stdc++.h>
using namespace std;

int N; // 3 x 2^k
/*
  *   //공백 2 별1
 * * //공백 1 별 공백 1 별 1
***** //공백 없이 별
*/
// 3으로 나누면 
// 1, 2, 4, 8
// 3, 6, 12, 24
// 2, 5, 11, 23
// 앞 공백 2시작, 
// 1 * 8, 3 * 8, 9 * 8, 27 * 8
// 1, 5, 11, 23
//  4   6   12
using namespace std;

vector<vector<char>>star;
void printStar(int N, int y, int x) {
    if (N == 3) {
        star[y][x] = '*';
        star[y + 1][x - 1] = star[y + 1][x + 1] = '*';
        for (int idx = 0; idx < 5; ++idx)
            star[y + 2][x - 2 + idx] = '*';
    }
    else {
        printStar(N / 2, y, x);
        printStar(N / 2, y + N / 2, x - N / 2);
        printStar(N / 2, y + N / 2, x + N / 2);
    }
}


int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int N = 0;
    int y = 0, x = 0;
    cin >> N;

    star = vector<vector<char>>(N, vector<char>(N * 2 - 1, ' '));

    printStar(N, 0, N - 1);

    for (y = 0; y < N; ++y) {
        for (x = 0; x < N * 2 - 1; ++x) {
            cout << star[y][x];
        }
        cout << '\n';
    }
    return 0;
}