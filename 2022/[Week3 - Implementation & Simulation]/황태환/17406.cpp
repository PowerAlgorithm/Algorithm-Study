#include <bits/stdc++.h>

typedef struct s{
    int r, c, s;
};

std::vector<std::vector<int>> board;
std::vector<s> comm;
int N, M, K;

std::vector<std::vector<int>> dfs(std::vector<int>& sequence, std::vector<std::vector<int>>& sequences, std::vector<bool>& visited)
{
    if(sequence.size() == comm.size())
    {
        sequences.push_back(sequence);
        return sequences;
    }
    for(int i = 0; i < comm.size(); i++)
    {
        if(!visited[i])
        {
            visited[i] = true;
            sequence.push_back(i);
            dfs(sequence, sequences, visited);
            visited[i] = false;
            sequence.pop_back();
        }
    }
    return sequences;
}

void rotate(int x, int y, int d, std::vector<std::vector<int>>& arr)
{
    std::vector<std::vector<int>> tmp = arr;

    for(int i=y-d; i<y+d; i++)
        arr[x-d][i+1] = tmp[x-d][i];
    for(int i=x-d; i<x+d; i++)
        arr[i+1][y+d] = tmp[i][y+d];
    for(int i=y+d; i>y-d; i--)
        arr[x+d][i-1] = tmp[x+d][i];
    for(int i=x+d; i>x-d; i--)
        arr[i-1][y-d] = tmp[i][y-d];
}

int minFind(std::vector<std::vector<int>> arr)
{
    int mini = INT_MAX;
    for(int i = 0; i<N; i++)
    {
        int sum = 0;
        for(int j = 0; j<M; j++)
            sum += arr[i][j];
        mini = std::min(sum, mini);
    }
    return mini;
}

int go(std::vector<int> sequence)
{
    std::vector<std::vector<int>> newBoard = board;
    for(int i = 0; i<sequence.size(); i++)
    {
        int r = comm[sequence[i]].r;
        int c = comm[sequence[i]].c;
        int s = comm[sequence[i]].s;

        for(int j = 1; j <= s; j++)
            rotate(r, c, j, newBoard);
    }
    return minFind(newBoard);
}

int solve()
{
    std::vector<std::vector<int>> sequences;
    std::vector<int> sequence;
    std::vector<bool> visited(6, false);
    int mini = INT_MAX;

    sequences = dfs(sequence, sequences, visited);

    for(int i = 0; i<sequences.size(); i++)
        mini = std::min(mini, go(sequences[i]));
    return mini;
}

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::cin >> N >> M >> K;

    for(int i = 0; i<N; i++)
    {
        std::vector<int> v;
        for(int j = 0; j<M; j++)
        {
            int num;
            std::cin >> num;
            v.push_back(num);
        }
        board.push_back(v);
    }
    for(int i = 0; i < K; i++)
    {
        int r, c, s;
        std::cin >> r >> c >> s;
        comm.push_back({r - 1, c - 1, s});
    }

    std::cout << solve() << '\n';
    return 0;
}