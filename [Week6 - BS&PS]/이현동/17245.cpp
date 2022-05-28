#include <cstdio>
#include <vector>
#define ull unsigned long long
using namespace std;

int n;
int server[1001][1001];

int main() {
	ull left = 0, right = 0, sum = 0;

	scanf("%d", &n);
	for (int i = 0; i < n; i++)for (int j = 0; j < n; j++) {
		scanf("%d", &server[i][j]);
		sum += server[i][j];
		if (server[i][j] > right)right = server[i][j];
	}

	while (left + 1 < right) {
		ull mid = (left + right) / 2;
		ull cnt = 0;

		for (int i = 0; i < n; i++)for (int j = 0; j < n; j++) {
			cnt += mid > server[i][j] ? server[i][j]:mid;
		}
		if (cnt>=0.5*sum)
			right=mid;
		else left = mid;
	}
	printf("%lld", right);
	return 0;
}