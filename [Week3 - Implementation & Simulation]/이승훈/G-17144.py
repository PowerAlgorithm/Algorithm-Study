import sys
from copy import deepcopy
input = sys.stdin.readline
r, c, t = map(int, input().split())

board = [list(map(int, input().split())) for _ in range(r)]
dy = [0, 1, 0, -1]
dx = [1, 0, -1, 0]
for i in range(r):
    if board[i][0] == -1:
        up_point = i, 0
        break
down_point = up_point[0]+1, up_point[1]


def spread():
    global board
    tmp_board = deepcopy(board)

    for i in range(r):
        for j in range(c):
            if board[i][j] in [0, -1]:
                continue
            dust = board[i][j] // 5
            for d in range(4):
                ny, nx = i+dy[d], j+dx[d]
                if 0 <= ny < r and 0 <= nx < c and board[ny][nx] != -1:
                    tmp_board[ny][nx] += dust
                    tmp_board[i][j] -= dust
    board = tmp_board


def rotate():
    global board

    up_y, up_x = up_point
    down_y, down_x = down_point
    tmp_board = deepcopy(board)
    for i in range(1, c-1):
        tmp_board[up_y][i+1] = board[up_y][i]
    for i in range(up_y, 0, -1):
        tmp_board[i-1][c-1] = board[i][c-1]
    for i in range(c-1, 0, -1):
        tmp_board[0][i-1] = board[0][i]
    for i in range(up_y-1):
        tmp_board[i+1][0] = board[i][0]

    for i in range(1, c-1):
        tmp_board[down_y][i+1] = board[down_y][i]

    for i in range(down_y, r-1):
        tmp_board[i+1][c-1] = board[i][c-1]

    for i in range(c-1, 0, -1):
        tmp_board[r-1][i-1] = board[r-1][i]

    for i in range(r-1, down_y+1, -1):
        tmp_board[i-1][0] = board[i][0]

    tmp_board[down_y][down_x+1] = 0
    tmp_board[up_y][up_x+1] = 0
    board = tmp_board


# rotate()
# for b in board:
#     print(b)


for _ in range(t):
    spread()
    # for b in board:
    #     print(b)
    rotate()
    # print()
    # for b in board:
    #     print(b)
ans = 0
for i in range(r):
    for j in range(c):
        ans += board[i][j]
print(ans+2)
