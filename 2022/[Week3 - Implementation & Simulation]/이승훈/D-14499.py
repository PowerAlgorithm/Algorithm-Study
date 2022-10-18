import sys
input = sys.stdin.readline
n, m, x, y, k = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
move = list(map(int, input().split()))
move = [m-1 for m in move]
dy = [0, 0, -1, 1]
dx = [1, -1, 0, 0]
dice = [0, 0, 0, 0, 0, 0]
start = [0, 0]

curY, curX = x, y


def dice_roll(dir):
    if dir == 0:
        pos = [3, 0, 2, 5]  # 5 3 0 2
        next_pos = [pos[-1]] + pos[:-1]
    elif dir == 1:
        pos = [3, 0, 2, 5]
        next_pos = pos[1:] + [pos[0]]
    elif dir == 2:
        pos = [1, 0, 4, 5]  # [0, 4, 5, 1]
        next_pos = pos[1:] + [pos[0]]
    elif dir == 3:
        pos = [1, 0, 4, 5]
        next_pos = [pos[-1]] + pos[:-1]

    tmp = []
    for i in range(len(pos)):
        tmp.append(dice[next_pos[i]])
    for i in range(len(pos)):
        dice[pos[i]] = tmp[i]


for d in move:
    nx, ny = curX+dx[d], curY + dy[d]
    if nx < 0 or nx >= m or ny < 0 or ny >= n:
        continue
    curX, curY = nx, ny
    dice_roll(d)
    if board[ny][nx] == 0:
        board[ny][nx] = dice[5]
    else:
        dice[5] = board[ny][nx]
        board[ny][nx] = 0
    print(dice[0])
