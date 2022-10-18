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

visited = [-1 for _ in range(N+1)]


def dfs(start, depth):
    visited[start] = depth
    for i in graph[start]:
        if visited[i] == -1:
            dfs(i, depth+1)


dfs(R, 0)

for i in visited[1:]:
    print(i)
