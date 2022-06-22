import sys
from heapq import *


input = sys.stdin.readline

h, w = map(int, input().split())

dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]

board = [list(input().strip()) for _ in range(h)]
visited = [[False for _ in range(w)] for _ in range(h)]
dist = [[[float('inf'), float('inf')] for _ in range(w)] for _ in range(h)]

start = []
end = []
for i in range(h):
    for j in range(w):
        if board[i][j] == 'S':
            start = [i, j]
        if board[i][j] == 'F':
            end = [i, j]

heap = []
heappush(heap, [[0, 0], [start[0], start[1]]])
visited[start[0]][start[1]] = True
dist[start[0]][start[1]] = [0, 0]


def checkGarbage(curY, curX):
    nextW0, nextW1 = 0, 0
    if board[curY][curX] == 'g':
        nextW0 = 1
        return 1, 0

    for i in range(4):
        ny, nx = curY+dy[i], curX+dx[i]
        if not(0 <= ny < h and 0 <= nx < w):
            continue
        if board[ny][nx] == 'g':
            nextW1 = 1

    return nextW0, nextW1


while heap:
    curW, cur = heappop(heap)
    curW0, curW1 = curW
    curY, curX = cur

    for i in range(4):
        ny, nx = curY+dy[i], curX+dx[i]
        if not(0 <= ny < h and 0 <= nx < w) or visited[ny][nx]:
            continue

        cd = dist[curY][curX]
        nd = dist[ny][nx]

        nextW0, nextW1 = checkGarbage(ny, nx)

        if nd[0] > cd[0] + nextW0 or (nd[0] == cd[0] + nextW0 and nd[1] > cd[1] + nextW1):
            dist[ny][nx] = [cd[0] + nextW0, cd[1] + nextW1]
            visited[ny][nx] = True
            heappush(heap, [[dist[ny][nx][0], dist[ny][nx][1]], [ny, nx]])

# for d in dist:
#     print(d)
# print(dist[end[0]][end[1]])
ans = dist[end[0]][end[1]]
a, b = checkGarbage(end[0], end[1])
if b == 1:
    print(ans[0], ans[1]-1)
else:
    print(ans[0], ans[1])
