import sys

N, M, K = map(int, sys.stdin.readline().split())
paper = [[0 for _ in range(M)] for _ in range(N)]


def is_valid(r, c, sticker):
    if r + len(sticker) > N or c + len(sticker[0]) > M:
        return False
    for _r in range(len(sticker)):
        for _c in range(len(sticker[0])):
            if sticker[_r][_c] == 1 and paper[r + _r][c + _c] == 1:
                return False
    return True


def turn_clockwise(sticker):
    R, C = len(sticker), len(sticker[0])
    turned = [[0 for _ in range(R)] for _ in range(C)]
    for r in range(R):
        for c in range(C):
            turned[c][R - r - 1] = sticker[r][c]
    return turned


def put_sticker(r, c, sticker):
    for _r in range(len(sticker)):
        for _c in range(len(sticker[0])):
            if sticker[_r][_c] == 1:
                paper[r + _r][c + _c] = sticker[_r][_c]


def search(sticker):
    for r in range(N):
        for c in range(M):
            if is_valid(r, c, sticker):
                return r, c
    return -1, -1


for i in range(K):
    R, C = map(int, sys.stdin.readline().split())
    sticker = [list(map(int, sys.stdin.readline().split())) for _ in range(R)]
    turn_cnt, flag = 0, False
    while turn_cnt < 4:
        r, c = search(sticker)
        if r < 0:
            sticker = turn_clockwise(sticker)
            turn_cnt += 1
        else:
            flag = True
            break
    if flag:
        put_sticker(r, c, sticker)

answer = 0
for r in range(N):
    for c in range(M):
        if paper[r][c] == 1:
            answer += 1
print(answer)
