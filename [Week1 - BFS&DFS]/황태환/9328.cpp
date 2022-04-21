// BFS 문제
#include <bits/stdc++.h>
using pii = std::pair<int, int>;

const int dx[] = {-1, 1, 0, 0};
const int dy[] = {0, 0, -1, 1};

void solution()
{
    int h, w;
    std::cin >> h >> w;

    char map[110][110];
    memset(map, 0, sizeof(map));

    for(int i = 1; i <= h; i++)
        for(int j = 1; j <= w; j++)
            std::cin >> map[i][j];
    
    std::queue<pii> q;
    std::vector<pii> door[26]; //지나갈 수 없는 문을 저장하기 위한 컨테이너
    bool ownKey[26] = {false}; // 소문자의 개수 26개 - 소유 여부
    bool visited[110][110];
    memset(visited, false, sizeof(visited));

    std::string keyStream;
    std::cin >> keyStream;

    if(keyStream != "0")
        for(auto c : keyStream)
            ownKey[c -'a'] = true;

    q.push({0, 0});
    int cnt = 0; // 문서의 개수
    while(!q.empty())
    {
        int y = q.front().first;
        int x = q.front().second;
        q.pop();

        if(map[y][x] == '$')
            cnt++;
        for(int i = 0; i < 4; i++)
        {
            int nY = y + dy[i];
            int nX = x + dx[i];
            if(nY < 0 || nX < 0 || nY > h + 1 || nX > w + 1 || visited[nY][nX] || map[nY][nX] == '*')
                continue;
            if(map[nY][nX] >= 'A' && map[nY][nX] <= 'Z') // 문을 만남
            {
                // 열쇠를 소유하고 있지 않는다면
                if(!ownKey[map[nY][nX] - 'A'])
                {
                    door[map[nY][nX] - 'A'].push_back({nY, nX});
                    continue;
                }
            }
            if(map[nY][nX] >= 'a' && map[nY][nX] <= 'z') // 열쇠를 만남
            {
                ownKey[map[nY][nX]-'a'] = true;
                // 해당하는 문이 있다면 q에 enqueue
                for(auto p : door[map[nY][nX] - 'a']) 
                    q.push(p);
            }
            visited[nY][nX] = 1;
            q.push({nY, nX});
        }
    }
    std::cout << cnt <<'\n';
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int T;
    std::cin >> T;

    while(T--)
        solution();
    return 0;
}