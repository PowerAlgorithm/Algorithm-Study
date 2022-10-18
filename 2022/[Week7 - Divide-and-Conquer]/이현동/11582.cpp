#include <bits/stdc++.h>
using namespace std;
typedef long long ll;

int N, dep;
int arr[2000001];
int tmp[2000001];

void merge(int l, int r, int mid)
{
    if ((r - l) > (N/dep))
        return;

    int i = l, j = mid+1;
    // int k = 0;
    int k = l;
    while (i <= mid && j <= r)
    {
        if(arr[i] < arr[j])
            tmp[k++] = arr[i++];
        else
            tmp[k++] = arr[j++];
    }
    while(i <= mid)
        tmp[k++] = arr[i++];
    while (j <= r)
        tmp[k++] = arr[j++];
    
    
    for (int i = l; i <= r;i++)
        arr[i] = tmp[i-l];
    // arr[i] = tmp[i]; 이건 왜 안될까?..
}

void mergeSort(int l, int r)
{
    if (l < r)
    {
        int mid = (l+r) / 2;
        mergeSort(l, mid);
        mergeSort(mid+1, r);
        merge(l, r, mid);
    }
}


int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;
    for (int i = 0; i < N;i++){
        cin >> arr[i];
    }
    cin >> dep;
    mergeSort(0, N - 1);
    for (int i = 0; i < N;i++){
        cout << arr[i] << ' ';
    }
    cout << '\n';
    return 0;
}
