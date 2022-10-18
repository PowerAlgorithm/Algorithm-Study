#include <bits/stdc++.h>
using namespace std;


int N, M, K;
vector<int> card;
vector<int> opp;
vector<int> ans;
bool visited[4000001];
int tmp;

int binSearch(int l, int r, int tmp)
{
    int mid = 0;
    while (l <= r)
    {
        mid = (l + r) / 2;
        if (card[mid] == tmp)
            return mid;
        if (card[mid] > tmp)
            r = mid - 1;
        else
            l = mid + 1;            
    }
    return mid - 1;
}
int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N >> M >> K;
    
    for (int i = 0; i < M;i++){
        cin >> tmp;
        card.push_back(tmp);
    }
    sort(card.begin(), card.end());
    for (int i = 0; i < K;i++){
        cin >> tmp;
        opp.push_back(tmp);
    }
    fill(&visited[0], &visited[M], false);

    for (int i = 0; i < K; i++)
    {
        int idx = binSearch(0, card.size()-1, opp[i]);
        for (int j = idx + 1; j < card.size(); j++)
        {
            if(!visited[j]){
                ans.push_back(card[j]);
                visited[j] = true;
                break;
            }
        }
    }
    for (auto a : ans)
        cout << a << '\n';
    return 0;
}