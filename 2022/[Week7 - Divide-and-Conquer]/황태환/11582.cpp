// merge sort의 중간 과정 출력 문제
#include <bits/stdc++.h>

int N, K;
std::vector<int> v, u;

void merge(int s, int e)
{
    if(e - s > N / K)
        return;
    int mid = s + e >> 1;

    int i = s, j = mid + 1, k = 0;
    while(i <= mid && j <= e)
    {
        if(v[i] <= v[j])    
            u[k++] = v[i++];
        else
            u[k++] = v[j++];
    }
    while(i <= mid)
        u[k++] = v[i++];
    while(j <= e)
        u[k++] = v[j++];
    for(int i = s; i <= e; i++)
        v[i] = u[i - s];
}

void mergeSort(int s, int e)
{
    if(s == e)
        return;
    int m = s + e >> 1;
    mergeSort(s, m);
    mergeSort(m + 1, e);
    merge(s, e);
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N;

    v.resize(N);
    u.resize(N);

    for(int i = 0; i < N; i++)
        std::cin >> v[i];

    std::cin >> K; 
    mergeSort(0, N - 1);

    for(auto i : v)
        std::cout << i << " ";
    std::cout << '\n';
    return 0;
}