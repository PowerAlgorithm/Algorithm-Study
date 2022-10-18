#include <bits/stdc++.h>
using namespace std;
int arr[100001] = { 0 };
int arr2[100001] = { 0 };
int N, H;
int result = 200001;
int cnt = 0;

int main() {
	cin >> N >> H;

	for (int i = 0; i <N/2; i++) 
		cin >> arr[i] >> arr2[i];
		
	sort(arr, arr + (N / 2));
	sort(arr2, arr2 + (N / 2));


	for (int i = 1; i <= H; i++) {

		int val = lower_bound(arr, arr + (N / 2), i ) - arr ;
		val += upper_bound(arr2, arr2 + (N / 2), H-i ) - arr2 ;
		val = N - val;

		if (result == val)
			cnt++;
		else if (result > val) { 
			result = val;
			cnt = 1;
		}
	}

    cout << result << ' ' << cnt << '\n';
    return 0;
}