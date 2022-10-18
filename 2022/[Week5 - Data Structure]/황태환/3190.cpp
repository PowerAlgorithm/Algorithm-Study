#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 1e2 + 1;
bool map[size][size];
bool visited[size][size];
// E S W N
const int dx[] = {0, 1, 0, -1};
const int dy[] = {1, 0, -1, 0};

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N, K;
    std::cin >> N >> K;
    
    for(int _ = 0, x, y; _ < K; _++)
    {
        std::cin >> x >> y;
        map[x - 1][y - 1] = true; // apple location
    }

    int L;
    std::cin >> L;
    std::deque<std::pair<int, char>> query;
    for(int _ = 0, x; _ < L; _++)
    {
        char ch;
        std::cin >> x >> ch;

        query.push_back({x, ch});
    }

    int time = 0;
    int dir = 0;
    std::deque<pii> snake;
    snake.push_back({0, 0});
    visited[0][0] = true;

    while(true)
    {
        time++;
        pii prev = snake.back();
        pii curr = {prev.first + dx[dir], prev.second + dy[dir]};

        if(visited[curr.first][curr.second] || !(0 <= curr.first && curr.first < N && 0 <= curr.second && curr.second < N))
        {
            std::cout << time << '\n';
            break;
        }

        visited[curr.first][curr.second] = true;
        snake.push_back(curr);

        if(map[curr.first][curr.second]) // 사과의 위치
            map[curr.first][curr.second] = false;
        else
        {
            pii tail = snake.front();
            visited[tail.first][tail.second]= false;
            snake.pop_front();
        }

        if(query.size() && query.front().first == time)
        {
            if(query.front().second == 'L')
                dir = (dir + 3) % 4;
            else
                dir = (dir + 1) % 4;
            query.pop_front();
        }
    }
    return 0;
}