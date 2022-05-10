import sys
from collections import deque

input = sys.stdin.readline
n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
blizzard = []
for _ in range(m):
    d, s = map(int, input().split())
    blizzard.append([d-1, s])

shark = [(n+1)//2 - 1, (n+1)//2 - 1]
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]
seq_dy = [0, 1, 0, -1]
seq_dx = [1, 0, -1, 0]
nums = [0, 0, 0]


def magic(d, s):
    global board

    cy, cx = shark
    for _ in range(s):
        ny, nx = cy+dy[d], cx+dx[d]
        cy, cx = ny, nx
        # nums[board[ny][nx]-1] += 1
        board[ny][nx] = 0


def serialize():
    cy, cx = 0, 0
    visited = [[False for _ in range(n)] for _ in range(n)]
    visited[cy][cx] = True
    dir = 0
    seq = [[0, 0]]
    while not(cy == shark[0] and cx == shark[1]):
        ny, nx = cy+seq_dy[dir], cx+seq_dx[dir]
        if 0 <= ny < n and 0 <= nx < n and not visited[ny][nx]:
            seq.append([ny, nx])
            cy, cx = ny, nx
            visited[ny][nx] = True
        else:
            dir = (dir+1) % 4
    seq.pop()
    seq.reverse()
    return seq


def explode(seq):
    marbles = []
    marble_length = len(seq)
    for y, x in seq:
        marbles.append(board[y][x])

    while True:
        before_len = len(marbles)
        marbles = [m for m in marbles if m != 0]
        if before_len == len(marbles):
            break
        prev_val = 0
        prev_pos = 0
        marbles.append(0)  # 끝부분 처리를 위해
        for i in range(len(marbles)):
            if prev_val == marbles[i]:
                continue
            if i - prev_pos > 3:   # 4개 이상이면 기존 marble들을 0으로
                nums[prev_val-1] += (i - prev_pos)
                for k in range(prev_pos, i):
                    marbles[k] = 0
            prev_pos = i
            prev_val = marbles[i]

        if marbles[-1] == 0:
            marbles.pop()

    prev_val = 0
    prev_pos = 0
    marbles.append(0)  # 끝부분 처리를 위해
    new_marbles = []
    for i in range(len(marbles)):
        if i != 0 and prev_val != marbles[i]:
            ls = marbles[prev_pos:i]
            new_marbles.extend([len(ls), ls[0]])
            prev_pos = i
            prev_val = marbles[i]
        else:
            prev_val = marbles[i]
    if marbles[-1] == 0:
        marbles.pop()

    new_marbles = new_marbles[:marble_length]
    new_marbles = new_marbles + [0] * (marble_length - len(new_marbles))

    for i, s in enumerate(seq):
        y, x = s
        board[y][x] = new_marbles[i]


seq = serialize()
for d, s in blizzard:
    # d, s = blizzard[0]
    magic(d, s)
    explode(seq)
# for b in board:
#     print(b)

# print(nums)
print(nums[0] + nums[1]*2 + nums[2]*3)
