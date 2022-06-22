import sys
from collections import deque
from heapq import *
input = sys.stdin.readline

n, k = map(int, input().split())
board = [[float('inf') for _ in range(n)] for _ in range(n)]

for i in range(n):
    board[i][i] = 0

for i in range(k):
    u, v = map(int, input().split())
    board[u-1][v-1] = 1


s = int(input())
li = [list(map(int, input().split())) for _ in range(s)]


for k in range(n):
    for i in range(n):
        for j in range(n):
            board[i][j] = min(board[i][j], board[i][k] + board[k][j])


for l, r in li:
    l, r = l-1, r-1
    a = board[l][r]
    b = board[r][l]

    if a != float('inf') or b != float('inf'):
        print(-1 if a < b else 1)
    else:
        print(0)
