import sys
from heapq import *


input = sys.stdin.readline

n = int(input())
board = [list(input().strip()) for _ in range(n)]

visited = [[False for _ in range(n)] for _ in range(n)]
visited[0][0] = True
dist = [[float('inf') for _ in range(n)] for _ in range(n)]
dist[0][0] = 0
dy = [0, 1, 0, -1]
dx = [1, 0, -1, 0]

heap = []
heappush(heap, [0, [0, 0]])

while heap:
    curW, cur = heappop(heap)
    curY, curX = cur

    if curW > dist[curY][curX]:
        continue

    for i in range(4):
        ny, nx = curY+dy[i], curX+dx[i]
        nextW = 0
        if not(0 <= ny < n and 0 <= nx < n) or visited[ny][nx]:
            continue
        if board[ny][nx] == '0':
            nextW = 1
        if dist[ny][nx] > dist[curY][curX] + nextW:
            dist[ny][nx] = dist[curY][curX] + nextW
            visited[ny][nx] = True
            heappush(heap, [dist[ny][nx], [ny, nx]])

print(dist[n-1][n-1])
