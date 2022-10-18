import sys
from collections import deque

input = sys.stdin.readline
n = int(input())
board = [list(map(int, input().split())) for _ in range(n)]
dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
visited = []

startPos = []
for i in range(n):
    for j in range(n):
        if board[i][j] == 9:
            startPos = [i, j]
            board[i][j] = 0
            break

shark_size = 2
shark_exp = 0


def find_food(deq):
    can_eat = []
    while deq:
        cy, cx, dist = deq.popleft()
        for i in range(4):
            ny, nx = cy+dy[i], cx+dx[i]
            if 0 <= ny < n and 0 <= nx < n and not visited[ny][nx]:
                if board[ny][nx] > shark_size:
                    continue

                if board[ny][nx] != 0 and board[ny][nx] < shark_size:
                    can_eat.append([ny, nx, dist])
                visited[ny][nx] = True
                deq.append([ny, nx, dist+1])
    return can_eat


cy, cx = startPos
ans = 0
while True:
    visited = [[False] * n for _ in range(n)]
    visited[cy][cx] = True
    deq = deque([[cy, cx, 1]])
    can_eat = find_food(deq)
    if not can_eat:
        break
    can_eat.sort(key=lambda x: x[1])
    can_eat.sort(key=lambda x: x[0])
    can_eat.sort(key=lambda x: x[2])
    y, x, dist = can_eat[0]
    ans += dist
    board[y][x] = 0
    cy, cx = y, x
    shark_exp += 1
    if shark_size <= shark_exp:
        shark_size += 1
        shark_exp = 0
print(ans)
