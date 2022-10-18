#include <bits/stdc++.h>
using namespace std;

int N, M;
int a, b;
queue<int> q;
vector<int> node[50001];
bool check[50001];
int parent[50001];
int depth[50001];

int LCA(int u, int v)
{
	// v가 u보다 더 깊게 설정
	if (depth[u] > depth[v]) swap(u, v);
	while (depth[u] != depth[v]) v = parent[v];
	
	// 두 노드가 같아질때 까지 위로 거슬러 올라감
	while (u != v)
	{
		u = parent[u];
		v = parent[v];
	}
	return u;
}


int main()
{
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	
	for (int i = 0; i < N-1; i++)
	{
		cin >> a >> b;
		node[a].push_back(b);
		node[b].push_back(a);
	}

    //root node
	check[1] = true;
	q.push(1);

	while (!q.empty())
	{
		int x = q.front(); 
		q.pop();

		for (int i = 0; i < node[x].size(); i++)
		{
			if (!check[node[x][i]]) 
			{
				depth[node[x][i]] = depth[x] + 1; // 깊이 +1
				check[node[x][i]] = true; // 방문표시
				parent[node[x][i]] = x; // 부모노드 입력
				q.push(node[x][i]); // 큐에 추가
			}
		}
	}

	cin >> M;

	for (int i = 0; i < M; i++)
	{
		cin >> a >> b;
		cout << LCA(a, b) << '\n';
	}

}