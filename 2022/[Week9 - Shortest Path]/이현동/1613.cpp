#include <bits/stdc++.h>
#define INF 1000000
#define MAX 401
using namespace std;

int n, k, s;
int graph[MAX][MAX];

void floyd() {
    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] > graph[i][k] + graph[k][j]) {
                    graph[i][j] = graph[i][k] + graph[k][j];
                }
            }
        }
    }
}

int main() {
    cin >> n >> k;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            graph[i][j] = INF;
        }
    }

    for (int i = 0; i < k; i++) {
        int pre, nxt;
        cin >> pre >> nxt;
        graph[pre][nxt] = 1;
    }

    floyd();

    int ans[50001];
    cin >> s;
    for (int i = 0; i < s; i++) {
        int event1, event2;
        cin >> event1 >> event2;

        if (graph[event1][event2] == INF && graph[event2][event1] == INF) {
            ans[i] = 0;
        }
        else if (graph[event1][event2] != INF) {
            ans[i] = -1;
        }
        else if (graph[event2][event1] != INF) {
            ans[i] = 1;
        }
    }
    for (int i = 0; i < s; i++) {
        cout << ans[i] << "\n";
    }

    return 0;
}
