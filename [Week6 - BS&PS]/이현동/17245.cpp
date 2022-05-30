#include <bits/stdc++.h>
using namespace std;
typedef unsigned long long ull;

int N;
int server[1001][1001];

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	ull left = 0, right = 0, sum = 0;
	cin >> N;
	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
		{
			cin >> server[i][j];
			sum += server[i][j];
			if (server[i][j] > right)
				right = server[i][j];
		}

	while (left + 1 < right) {
		ull mid = (left + right) / 2;
		ull cnt = 0;

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				cnt += mid > server[i][j] ? server[i][j]:mid;
			}
		if (cnt>=0.5*sum)
			right=mid;
		else left = mid;
	}
	cout << right;
	return 0;
}