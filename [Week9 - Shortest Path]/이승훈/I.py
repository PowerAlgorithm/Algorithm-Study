import sys
from itertools import combinations
from heapq import *
input = sys.stdin.readline

n, m = map(int, input().split())
dist = [float('inf') for _ in range(n+1)]
graph = []
seq = [-1 for _ in range(n+1)]
dir = [[] for i in range(n+1)]

for i in range(m):
    u, v, w = map(int, input().split())
    graph.append([u, v, -w])


def bellman_ford():
    dist[1] = 0
    for i in range(n):
        for j in range(m):
            cur, next, w = graph[j]
            if dist[cur] != float('inf') and dist[next] > dist[cur] + w:
                dist[next] = dist[cur] + w
                # seq[cur] = next
                dir[cur].append(next)
                seq[next] = cur
                if i == n-1 and dist[next] < 0:
                    if n in dir[next] or n in dir[cur]:
                        return False

    return -1


s = n
if not bellman_ford():
    print(-1)
else:
    ans = []
    ans.append(s)
    cnt = 101
    while seq[s] > -1 and cnt > 0:
        ans.append(seq[s])
        s = seq[s]
        cnt -= 1

    if cnt <= 0:
        print(-1)
    else:
        for a in list(reversed(ans)):
            print(a, end=' ')
