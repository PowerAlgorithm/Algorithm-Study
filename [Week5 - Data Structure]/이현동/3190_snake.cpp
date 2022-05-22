#include <bits/stdc++.h>
using namespace std;
using pic = std::pair<int, char>;
using pii = std::pair<int, int>;

int N, K, L;
int graph[101][101];
deque<pic> turns;
queue<pair<pii, pii> > q;
deque<pii> snake;

bool checkRange(int rx, int ry)
{
    if(0 <= rx && rx < N && 0 <= ry && ry < N)
        return true;
    return false;
}

bool snakeTouch(int rx, int ry){
    for (int i = 0; i < snake.size();i++){
        if(snake[i].first == rx && snake[i].second == ry)
            return true;
    }
    return false;
}
int bfs()
{
    int dx[] = {0, -1, 0, 1};
    int dy[] = {1, 0, -1, 0};
    int toward = 0;
    q.push(make_pair(make_pair(0, 0), make_pair(0, 0)));
    snake.push_back(make_pair(0, 0));

    while(!q.empty()){
        int x, y, time, tow;
        x = q.front().first.first;
        y = q.front().first.second;
        time = q.front().second.first;
        tow = q.front().second.second;
        q.pop();
        if (!turns.empty())
        {
            int t, d;
            t = turns.front().first;
            d = turns.front().second;
            turns.pop_front();
            if (time == t){
                if(d == 'D'){
                    toward--;
                    if (toward < 0)
                        toward = 3;
                    toward %= 4;
                }
                else if (d == 'L')
                {
                    toward++;
                    toward %= 4;
                }
            }else{
                turns.push_front(make_pair(t, d));
            }
        }
        int rx, ry;
        rx = x + dx[toward];
        ry = y + dy[toward];
        if(!checkRange(rx, ry) || snakeTouch(rx, ry))
            return (time + 1);
        if(checkRange(rx, ry)){
            if(graph[rx][ry] == 1){
                graph[rx][ry] = 0;
                snake.push_back(make_pair(rx, ry));
                q.push(make_pair(make_pair(rx, ry), make_pair(time + 1, tow)));
            }else if(graph[rx][ry] == 0){
                q.push(make_pair(make_pair(rx, ry), make_pair(time + 1, tow)));
                snake.push_back(make_pair(rx, ry));
                snake.pop_front();
            }
        }
    }
    return 0;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;
    cin >> K;

    fill(&graph[0][0], &graph[100][100], 0);
    for (int i = 0; i < K;i++){
        int a, b;
        cin >> a >> b;
        graph[a - 1][b - 1] = 1;
    }
    cin >> L;
    for (int i = 0; i < L;i++){
        int s;
        char d;
        cin >> s >> d;
        turns.push_back(make_pair(s, d));
    }
    cout << bfs();
    return 0;
}