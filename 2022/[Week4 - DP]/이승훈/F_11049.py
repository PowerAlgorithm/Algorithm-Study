import sys

n = int(sys.stdin.readline())
pocession = []
for i in range(n):
    pocession.append(list(map(int, sys.stdin.readline().split())))

board = [[float('inf')]*n for _ in range(n)]

for d in range(n):
    for i in range(n - d):
        j = i + d

        if i == j:
            board[i][j] = 0
        else:
            for k in range(i, j):
                board[i][j] = min(board[i][j],
                                  board[i][k] + board[k+1][j] +
                                  pocession[i][0]*pocession[k][1]*pocession[j][1])
print(board[0][-1])
