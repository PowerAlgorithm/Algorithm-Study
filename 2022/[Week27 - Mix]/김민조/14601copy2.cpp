#include <bits/stdc++.h>
using namespace std;
int a[1 << 7][1 << 7];
int num;
bool check(int n, int x, int y) {
	for (int i = x; i < x + n; i++) {
		for (int j = y; j < y + n; j++) {
			if (a[i][j])return false;
		}
	}
	return true;
}
void solve(int n, int x, int y) {
	num++;
	int n2 = n / 2;
	if (check(n2, x, y))a[x + n2 - 1][y + n2 - 1] = num;
	if (check(n2, x, y + n2))a[x + n2 - 1][y + n2] = num;
	if (check(n2, x + n2, y))a[x + n2][y + n2 - 1] = num;
	if (check(n2, x + n2, y + n2))a[x + n2][y + n2] = num;
	if (n == 2)return;
	solve(n2, x, y);
	solve(n2, x + n2, y);
	solve(n2, x, y + n2);
	solve(n2, x + n2, y + n2);
}
int main() {
	ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	int n;
	cin >> n;
	n = (1 << n);
	int x, y;
	cin >> x >> y;
	a[n - y][x - 1] = -1;
	solve(n, 0, 0);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << a[i][j] << ' ';
		}
		cout << '\n';
	}

}