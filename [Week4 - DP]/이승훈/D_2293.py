import sys

input = sys.stdin.readline

n, k = map(int, input().split())
coins = [int(input()) for _ in range(n)]
board = [0 for _ in range(k+1)]


for i in range(1, n+1):
    coin = coins[i-1]
    for j in range(1, k+1):
        if j == coin:
            board[j] += 1
        if j > coin:
            board[j] += board[j-coin]

print(board[k])
