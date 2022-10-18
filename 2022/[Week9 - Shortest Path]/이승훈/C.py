import sys
from heapq import *


input = sys.stdin.readline

w, h = map(int, input().split())

dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]

board = [list(input().strip()) for _ in range(h)]
visited = [[False for _ in range(w)] for _ in range(h)]
dist = [[float('inf') for _ in range(w)] for _ in range(h)]

cPos = []
for i in range(h):
    for j in range(w):
        if board[i][j] == 'C':
            cPos.append([i, j])
            board[i][j] == '.'

heap = []
heappush(heap, [0, [cPos[0][0], cPos[0][1], -1]])
# heappush(heap, [0, [cPos[0][0], cPos[0][1], 1]])
# heappush(heap, [0, [cPos[0][0], cPos[0][1], 2]])
# heappush(heap, [0, [cPos[0][0], cPos[0][1], 3]])
visited[cPos[0][0]][cPos[0][1]] = True
dist[cPos[0][0]][cPos[0][1]] = 0

while heap:
    curW, cur = heappop(heap)
    curY, curX, curD = cur

    if dist[curY][curX] < curW:
        continue

    for i in range(4):
        ny, nx = curY+dy[i], curX+dx[i]
        nextW = 0
        if not(0 <= ny < h and 0 <= nx < w) or board[ny][nx] == '*':
            #  or visited[ny][nx] or
            continue

        if i != curD and curD != -1:
            nextW = 1

        if dist[ny][nx] >= dist[curY][curX] + nextW:
            dist[ny][nx] = dist[curY][curX] + nextW
            # visited[ny][nx] = True
            heappush(heap, [dist[ny][nx], [ny, nx, i]])

# for d in dist:
#     print(d)
print(dist[cPos[1][0]][cPos[1][1]])
