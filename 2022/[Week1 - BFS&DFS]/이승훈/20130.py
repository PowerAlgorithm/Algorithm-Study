import sys
from collections import deque


input = sys.stdin.readline

n, m = map(int, input().split())
board = [list(input().strip()) for _ in range(n)]
startY, startX = 0, 0
dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]
for i in range(n):
    for j in range(m):
        if board[i][j] == '@':
            board[i][j] = '*'
            startY, startX = i, j
        elif board[i][j] == '!':
            targetY, targetX = i, j
key = set()
visited = []
road = []
ans = []


def bfs(deq):
    while deq:
        curY, curX = deq.popleft()
        if board[curY][curX] == '!':
            return True

        for i in range(4):
            ny, nx = curY+dy[i], curX+dx[i]
            if visited[ny][nx] or board[ny][nx] == '#':
                continue
            if board[ny][nx].islower():
                key.add(board[ny][nx])
                road.append((ny, nx))
                deq.append([ny, nx])
                visited[ny][nx] = True
            elif board[ny][nx].lower() in key or board[ny][nx] in ['*', '!']:
                road.append((ny, nx))
                deq.append([ny, nx])
                visited[ny][nx] = True
    return False


seq = []
seqSet = set()
while True:
    visited = [[False]*m for _ in range(n)]
    visited[startY][startX] = True
    road = [(startY, startX)]
    success = bfs(deque([[startY, startX]]))
    for r in road:
        if r not in seqSet:
            seq.append(r)
            seqSet.add(r)
    if success:
        for val in seq:
            if val[0] == targetY and val[1] == targetX:
                ans.append([val[0]+1, val[1]+1])
                break
            ans.append([val[0]+1, val[1]+1])
        break
print(len(ans))
for s in ans:
    print(s[0], s[1])
