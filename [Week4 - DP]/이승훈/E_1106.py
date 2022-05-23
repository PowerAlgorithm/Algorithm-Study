import sys

"""

조건

"""

input = sys.stdin.readline

c, n = map(int, input().split())
clients = [list(map(int, input().split())) for _ in range(n)]

board = [float('inf') for _ in range(c+100)]

board[0] = 0
for cost, num in clients:
    for i in range(num, c+100):
        board[i] = min(board[i-num] + cost, board[i])

print(min(board[c:]))
