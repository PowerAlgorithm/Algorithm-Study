#include <bits/stdc++.h>
using pii = std::pair<int, int>;

constexpr int size = 201;
int N, M;
pii start;
char board[size][size];
bool visited[size][size];
std::vector<pii> locks[26];
std::vector<pii> path, key;

// 4 way
const int dx[] = {0, 0, 1, -1};
const int dy[] = {1, -1, 0, 0};

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M;

    for(int i = 1; i <= N; i++)
    {
        for(int j = 1; j <= M; j++)
        {
            std::cin >> board[i][j];
            if(board[i][j] == '@')
            {
                start = {i, j}; // 시작점
                board[i][j] = '*'; // 시작점을 기록했으니 갈 수 있는 길로 바꿔줌
                visited[i][j] = true; // 시작점 방문
            }
            if(board[i][j] >= 'A' && board[i][j] <= 'Z') // 자물쇠가 있는 지역
                locks[board[i][j] - 'A'].push_back({i, j}); // 좌표를 저장해줌
        }
    }

    std::queue<pii> q;
    q.push(start);

    while(!q.empty())
    {
        auto curr = q.front();
        q.pop();

        if(std::find(path.begin(), path.end(), curr) == path.end()) // 경로는 중복 좌표 없이 저장해야 함
            path.push_back(curr); // 경로에 좌표 추가
        if(board[curr.first][curr.second] == '!') // 최종 목표 지점 도착
            break;
        else if(board[curr.first][curr.second] >= 'a' && board[curr.first][curr.second] <= 'z') // 열쇠 아이템 지역이라면
        {
            int c = board[curr.first][curr.second] - 'a';
            for(auto l : locks[c]) // 해당하는 자물쇠가 있는 곳을
                board[l.first][l.second] = '*'; // 갈 수 있는 곳으로 변경
        }

        for(int i = 0; i < 4; i++) // 4way
        {
            pii coord = curr;
            coord.first += dx[i];
            coord.second += dy[i];

            if(coord.first <= 0 || coord.second <= 0 || coord.first > N || coord.second > M) // OOB
                continue;
            if(!visited[coord.first][coord.second] && board[coord.first][coord.second] != '#' && !(board[coord.first][coord.second] >= 'A' && board[coord.first][coord.second] <= 'Z'))
            {
                q.push(coord);
                visited[coord.first][coord.second] = true;
            }
            if(board[coord.first][coord.second] >= 'A' && board[coord.first][coord.second] <= 'Z' && std::find(key.begin(), key.end(), curr) == key.end())
                key.push_back(curr);
        }

        if(q.empty()) // 큐 비워주기
        {
            for(auto x : key)
                q.push(x);
            key.clear();
        }
    }
    // 경로 출력
    std::cout << (int)path.size() << '\n';
    for(auto p : path)
        std::cout << p.first << " " << p.second << '\n';
    return 0;
}