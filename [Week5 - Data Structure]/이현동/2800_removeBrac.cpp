#include <bits/stdc++.h>
using namespace std;
using pii = std :: pair<int, int>;

using namespace std;

vector<pii> ans;
vector<string> res;
string s;
bool visit[201];

void dfs(int idx) {
	if (idx == ans.size()) {
		string str;
		for (int j = 0; j < s.length(); j++) {
			if (visit[j])
				continue;
			str += s[j];
		}
		if (str != s)
			res.push_back(str);
		return;
	}
	dfs(idx + 1);
	visit[ans[idx].first] = true;
	visit[ans[idx].second] = true;
	dfs(idx + 1);
	visit[ans[idx].first] = false;
	visit[ans[idx].second] = false;
}
int main() {
	ios::sync_with_stdio(false);
    cin.tie(0);

	cin >> s;

	vector<pair<char, int>> st;
    
	for (int i = 0; i < s.length(); i++) {
		if (s[i] == '(' || s[i] == ')') {
			st.push_back({ s[i], i });
		}
		if (st.size() >= 2 && st[st.size()-2].first=='(' 
			&& st[st.size()-1].first==')') {
			ans.push_back({ st[st.size() - 2].second, st[st.size() - 1].second });
			st.pop_back();
			st.pop_back();
		}
	}
	
    dfs(0);

	sort(res.begin(), res.end());
	res.erase(unique(res.begin(), res.end()), res.end());
	for (auto i : res) {
		cout << i << "\n";
	}
}