from re import L
import sys
from collections import *
sys.setrecursionlimit(10**7)


input = sys.stdin.readline

n = int(input())
m = int(input())
graph = [[] for _ in range(n+1)]
for _ in range(m):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)
parent = [i for i in range(n+1)]


def find(a):
    if parent[a] == a:
        return a
    parent[a] = find(parent[a])
    return parent[a]


def union(a, b):
    aRoot = find(a)
    bRoot = find(b)
    if aRoot < bRoot:
        parent[bRoot] = aRoot
    else:
        parent[aRoot] = bRoot


def bfs(start):
    deq = deque([[start, 1]])
    visited = [False for _ in range(n+1)]
    visited[start] = True
    depth = 0

    while deq:
        cur, cnt = deq.popleft()
        depth = max(depth, cnt)
        for next in graph[cur]:
            if not visited[next]:
                visited[next] = True
                deq.append([next, cnt+1])
    return depth


for cur in range(1, n+1):
    for next in graph[cur]:
        union(cur, next)

commDepth = [float('inf') for _ in range(n+1)]
ans = [0 for _ in range(n+1)]
for cur in range(1, n+1):
    curRoot = find(cur)
    depth = bfs(cur)
    if commDepth[curRoot] > depth:
        commDepth[curRoot] = depth
        ans[curRoot] = cur
ans = [a for a in ans if a != 0]
ans.sort()
print(len(ans))
for a in ans:
    print(a)
