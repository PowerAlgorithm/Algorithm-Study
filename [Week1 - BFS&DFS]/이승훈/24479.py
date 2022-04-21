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

cnt = 1


def dfs(cur, visited):
    global cnt

    if cnt >= n+1:
        return True

    visited[cur] = cnt
    for next in graph[cur]:
        if visited[next] != 0:
            continue
        cnt += 1
        dfs(next, visited)


visited = [0] * (n+1)
dfs(r, visited)
for v in range(1, len(visited)):
    print(visited[v])
