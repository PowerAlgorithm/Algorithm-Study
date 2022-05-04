#include <bits/stdc++.h>
# define SIZE 101
using namespace std;

/*
* 0 - x 증가하는 방향(우), 1 - y감소하는 방향(위), 
* 2 - x감소하는 방향(왼), 3 - y증가(아래)
* 
*/
// 3
// 3 3 0 1
// 4 2 1 3
// 4 2 2 1

int N; // 커브 갯수
int x, y, d, g;
int ans = 0;
int graph[SIZE][SIZE] = {0};
vector<int> curves;
int dx[] = {1, 0, -1, 0}; // 우, 상, 좌, 하
int dy[] = {0, -1, 0, 1};



void dragonCurve()
{
    int l = curves.size();
    for (int i = l - 1; i >= 0; i--)
    {
        int dd = (curves[i]+1)%4;
        x += dx[dd];
        y += dy[dd];
        graph[y][x] = 1;
        curves.push_back(dd);
    }
}

void countSquare()
{
    for (int i = 0; i < SIZE; i++)
    {
        for (int j = 0; j < SIZE; j++)
        {
            if ((graph[i][j] == 1) && (graph[i][j + 1] == 1) && (graph[i + 1][j] == 1) && (graph[i + 1][j + 1] == 1))
                ans++;
        }
    }
}

int main()
{

    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;
    /*input
    * x, y, d, g
    * x, y : 좌표, d : 방향, g : 커브 세대
    */
    while(N--){
        curves.clear();
        cin >> x >> y >> d >> g;
        graph[y][x] = 1;
        x += dx[d];
        y += dy[d];
        graph[y][x] = 1;
        curves.push_back(d);
        while(g--){
            dragonCurve();
        }
    }
    countSquare();
    cout << ans;
    return 0;
}