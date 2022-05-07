#include <bits/stdc++.h>
using namespace std;
/*
* 주사위 바닥면에 쓰여진 수 -> 지도로 복사됨
* 지도의 각 칸에는 정수가 하나씩 쓰여져 있다. 
* 주사위 윗면 1 오른쪽 면 1이 초기 상태, 바닥 - 6, 북쪽면 -5
* 주사위 6면을 기억하고 있는 것이 관건
* 동서남북으로 주사위를 굴릴 때 무조건 윗면이 가는 방향의 면으로 
* 바뀌는 것이 포인트. 주사위에 복사된 후 칸에 쓰여진 수는 0이 된다.
*/
int N, M, x, y, K;
int dx[] = {0, 0, -1, 1}; // 동-1 서-2 북-3 남-4
int dy[] = {1, -1, 0, 0};
// 주사위 위, 동, 서, 남, 북, 아래
int dice[6] = {0, 0, 0, 0, 0, 0}; // 주사위 4면 
int graph[21][21];
int order[1001];
vector<int> ans;

bool checkRange(int rx, int ry){
    if (0 <= rx && rx < N && 0 <= ry && ry < M)
        return true;
    return false;
}

int rollDice(int direction)
{
    int rx = x + dx[direction - 1];
    int ry = y + dy[direction - 1];
    int bottom = graph[rx][ry];
    if (!checkRange(rx, ry))
        return -1;
    x = rx;
    y = ry;

    if (direction == 1) // 동
    {
        //주사위 윗면->동쪽, 동쪽->바닥, 바닥->서쪽, 서쪽->윗면
        int tmp = dice[0];
        dice[0] = dice[2];
        dice[2] = dice[5];
        dice[5] = dice[1];
        dice[1] = tmp;
    }
    else if (direction == 2) // 서
    {
        // 윗면 -> 서, 서 -> 바닥, 바닥 -> 동, 동 -> 윗면
        int tmp = dice[0];
        dice[0] = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[2];
        dice[2] = tmp;
    }
    // 주사위 위0, 동1, 서2, 남3, 북4, 아래5
    else if (direction == 3) // 북
    {
        // 윗면 -> 북, 북 -> 바닥, 바닥 -> 남, 남 -> 윗면
        int tmp = dice[0]; // 윗면
        dice[0] = dice[3];
        dice[3] = dice[5];
        dice[5] = dice[4];
        dice[4] = tmp;
    }
    
    else if (direction == 4) // 남
    {
        int tmp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[5];
        dice[5] = dice[3];
        dice[3] = tmp;
    }
    if (graph[rx][ry] == 0)
        graph[rx][ry] = dice[5];
    else{
        dice[5] = graph[rx][ry];
        graph[rx][ry] = 0;
    }

    
    return dice[0];
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N >> M >> x >> y >> K;
    for (int i = 0; i < N;i++){
        for (int j = 0; j < M;j++){
            cin >> graph[i][j];
        }
    }
    for (int i = 0; i < K;i++)
        cin >> order[i];


    for (int i = 0; i < K;i++)
    {
        int tmp = rollDice(order[i]);
        if (tmp != -1)
            ans.push_back(tmp);
    }
    for(auto a : ans)
        cout << a << '\n';
    return 0;
}
