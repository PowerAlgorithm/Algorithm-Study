import sys
sys.setrecursionlimit(1000000)


input = sys.stdin.readline

n, m, r = map(int, input().split())
graph = [[] for _ in range(n+1)]
for i in range(m):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)

for g in graph:  # 그래프를 오름차순으로 방문하기 위해 정렬
    g.sort()

cnt = 0
visited = [-1] * (n+1)


def dfs(cnt, cur):
    if cnt >= n:
        return True

    visited[cur] = cnt
    for next in graph[cur]:
        if visited[next] != -1:
            continue
        dfs(cnt+1, next)


dfs(cnt, r)
for v in range(1, len(visited)):
    print(visited[v])
