import sys
from collections import deque

input = sys.stdin.readline
s = int(input())
board = [[-1] * (s+1) for _ in range(s+1)]      # 시간


def bfs():
    board[1][0] = 0
    deq = deque([[1, 0]])
    while deq:
        x, y = deq.popleft()

        if board[x][x] == -1:
            board[x][x] = board[x][y] + 1
            deq.append([x, x])

        if x+y <= s and board[x+y][y] == -1:
            board[x+y][y] = board[x][y] + 1
            deq.append([x+y, y])

        if x-1 >= 0 and board[x-1][y] == -1:
            board[x-1][y] = board[x][y] + 1
            deq.append([x-1, y])


bfs()
ans = float('inf')
for i in range(s+1):
    if board[s][i] == -1:
        continue
    ans = min(ans, board[s][i])
print(ans)