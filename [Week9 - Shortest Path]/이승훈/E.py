import sys
from collections import deque
from heapq import *
input = sys.stdin.readline

n = int(input())
m = int(input())
board = [[float('inf') for _ in range(n)] for _ in range(n)]
for i in range(n):
    board[i][i] = 0

for i in range(m):
    u, v, w = map(int, input().split())
    board[u-1][v-1] = min(board[u-1][v-1], w)

for k in range(n):
    for i in range(n):
        for j in range(n):
            board[i][j] = min(board[i][j], board[i][k] + board[k][j])

for b in board:
    for bVal in b:
        print(bVal if float('inf') != bVal else 0, end=' ')
    print()
