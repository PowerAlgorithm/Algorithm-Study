import sys
from itertools import combinations
from heapq import *
input = sys.stdin.readline

n, m = map(int, input().split())
dist = [float('inf') for _ in range(n+1)]
graph = []

for i in range(m):
    u, v, w = map(int, input().split())
    graph.append([u, v, w])


def bellman_ford():
    dist[1] = 0
    for i in range(n):
        for j in range(m):
            cur, next, w = graph[j]
            if dist[cur] != float('inf') and dist[next] > dist[cur] + w:
                dist[next] = dist[cur] + w
                if i == n - 1:
                    return False
    return True


if not bellman_ford():
    print(-1)
else:
    for i in range(2, n+1):
        print(dist[i] if dist[i] != float('inf') else -1)
