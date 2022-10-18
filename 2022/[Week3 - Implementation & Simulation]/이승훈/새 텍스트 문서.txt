from re import A
import sys
from itertools import *
from copy import deepcopy
input = sys.stdin.readline

n = int(input())
board = [list(map(int, input().split())) for i in range(n)]


def move():
    for i in range(n):
        tmp = []
        j = 0
        board_list = [b for b in board[i] if b != 0]
        while j < len(board_list):
            if j < len(board_list)-1 and board_list[j] == board_list[j+1]:
                tmp.append(board_list[j]*2)
                j += 2
            else:
                tmp.append(board_list[j])
                j += 1
        if len(tmp) < n:
            tmp = tmp + [0] * (n-len(tmp))
        board[i] = tmp


def rotate(num):
    global board

    for i in range(num):
        rotated_board = [[0 for _ in range(n)] for _ in range(n)]
        for i in range(n):
            for j in range(n):
                rotated_board[j][n - 1 - i] = board[i][j]
        board = rotated_board


def find_max_block():
    global board

    max_val = 0
    for i in range(n):
        for j in range(n):
            max_val = max(board[i][j], max_val)
    return max_val


num = [[0, 1, 2, 3]] * 5
cases = list(product(*num))
init_board = deepcopy(board)

ans = 0
for case in cases:
    board = deepcopy(init_board)
    for idx, c in enumerate(case):
        rotate(c)
        move()
        ans = max(ans, find_max_block())
print(ans)
