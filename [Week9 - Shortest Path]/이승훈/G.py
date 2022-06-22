import sys
from itertools import combinations
from heapq import *
input = sys.stdin.readline

n, m = map(int, input().split())
board = [[float('inf') for _ in range(n)] for _ in range(n)]

for i in range(n):
    board[i][i] = 0

for i in range(m):
    u, v = map(int, input().split())
    board[u-1][v-1] = 1
    board[v-1][u-1] = 1


for k in range(n):
    for i in range(n):
        for j in range(n):
            board[i][j] = min(board[i][j], board[i][k] + board[k][j])


comb = combinations([i for i in range(n)], 2)
ans = [-1, -1, float('inf')]

for l, r in comb:
    num = 0
    for i in range(n):
        if i in [l, r]:
            continue
        num += min(board[i][l] + board[l][i], board[i][r] + board[r][i])

    if num < ans[2]:
        ans = [l, r, num]
    elif num == ans[2] and l < ans[0]:
        ans = [l, r, num]
    elif num == ans[2] and l == ans[0] and r < ans[1]:
        ans = [l, r, num]

print(ans[0]+1, ans[1]+1, ans[2])
