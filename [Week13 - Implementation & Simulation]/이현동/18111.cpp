#include <bits/stdc++.h>
using namespace std;

/*
땅의 높이를 고르게 만드는 작업
1. (i, j)에 들어 있는 블록을 제거하여 인벤토리에 넣는다.- 2sec
2. 인벤토리에서 꺼내 (i, j)에 맨 위에 쌓는다. - 1sec

*/

int N, M, B;
int arr[501][501];

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> M >> B;

    for (int i = 0; i < N;i++){
        for (int j = 0; j < M;j++){
            cin >> arr[i][j];
        }
    }
    int minT = 100000000;
    int maxH;
    for (int h = 0; h <= 256; h++)
    {
        int build = 0;
        int r = 0;
        for (int i = 0; i < N;i++){
            for (int j = 0; j < M;j++){
                int height = arr[i][j] - h;
                if(height > 0)
                    r += height;
                else if(height < 0)
                    build -= height;
            }
        }
        if(r + B >= build){
            int t = r * 2 + build;
            if(minT >= t){
                minT = t;
                maxH = h;
            }
        }
    }
    cout << minT << ' ' << maxH;
    return 0;
}