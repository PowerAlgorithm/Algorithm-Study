import sys
from collections import deque

input = sys.stdin.readline
n, m = map(int, input().split())

board = [list(map(int, input().split())) for _ in range(n)]
dist = [[board[0][i], board[0][i], board[0][i]] for i in range(m)]
# 왼 가 오

# print(dist)
for i in range(1, n):
    tmp = []
    for j in range(m):
        if j == 0:
            left = float('inf')
        else:
            left = min(dist[j-1][1], dist[j-1][2]) + board[i][j]
        mid = min(dist[j][0], dist[j][2]) + board[i][j]

        if j == m-1:
            right = float('inf')
        else:
            right = min(dist[j+1][0], dist[j+1][1]) + board[i][j]
        tmp.append([left, mid, right])
    # print(tmp)
    dist = tmp

ans = float('inf')
for d in dist:
    ans = min(ans, min(d))
print(ans)
