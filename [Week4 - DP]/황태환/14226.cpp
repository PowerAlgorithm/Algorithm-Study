#include <bits/stdc++.h>

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    int N;
    std::cin >> N;

    std::queue<std::tuple<int, int, int>> q;
    std::map<std::pair<int, int>, bool> visited;

    q.push({1, 0, 0});
    while(!q.empty())
    {
        auto curr = q.front();
        int emo = std::get<0>(curr);
        int clip = std::get<1>(curr);
        int time = std::get<2>(curr);
        q.pop();

        if(emo == N)
        {
            std::cout << time << '\n';
            break;
        }

        if(!visited[{emo, clip}])
        {
            visited[{emo,clip}] = true;
            if(std::get<1>(curr) > 0)
                q.push({emo + clip, clip, time + 1});
            if(std::get<0>(curr) > 1)
                q.push({emo - 1, clip, time + 1});
            q.push({emo, emo, time + 1});
        }
    }
    return 0;
}