#include <iostream>
#include <string>

using namespace std;

bool visited[51] = {
    false,
};

int dfs(string &s, int idx)
{
    int cnt = 0;
    for (int i = idx; i < s.size(); i++)
    {
        if (s[i] == '(' && !visited[i])
        {
            visited[i] = true;
            int num = s[i - 1] - '0';
            cnt--;
            cnt += num * dfs(s, i + 1);
        }
        else if (s[i] == ')' && !visited[i])
        {
            visited[i] = true;
            return cnt;
        }
        else if (!visited[i])
        {
            visited[i] = true;
            cnt++;
        }
    }

    return cnt;
}
int main()
{
    string s;
    cin >> s;
    int ans = dfs(s, 0);
    cout << ans << endl;

    return 0;
}