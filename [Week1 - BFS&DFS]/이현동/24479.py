import sys
sys.setrecursionlimit(10**8)
input = sys.stdin.readline

N, M, R = map(int, input().split())

graph = [[] for _ in range(N+1)]

for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

for i in range(1, N+1):
    graph[i].sort()

visited = [0 for _ in range(N+1)]
cnt = 0


def dfs(start):
    global cnt
    cnt += 1
    visited[start] = cnt
    for i in graph[start]:
        if not visited[i]:
            dfs(i)


dfs(R)

for i in visited[1:]:
    print(i)
