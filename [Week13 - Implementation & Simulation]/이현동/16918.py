import sys
from collections import deque
input = sys.stdin.readline


dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
data = []


def findBomb():
    for i in range(r):
        for j in range(c):
            if data[i][j] == 'O':
                bombL.append([i, j])


def allBombSet():
    for i in range(r):
        for j in range(c):
            if data[i][j] != 'O':
                data[i][j] = 'O'


def bomb():
    while bombL:
        x, y = bombL.popleft()
        data[x][y] = '.'
        for i in range(4):
            rx, ry = x + dx[i], y + dy[i]
            if 0 <= rx < r and 0 <= ry < c:
                if data[rx][ry] == 'O':
                    data[rx][ry] = '.'


r, c, n = map(int, input().split())
#data = [list(map(int, input().rstrip())) for _ in range(r)]
for i in range(r):
    tmp = input().rstrip()
    data.append(list(tmp))
n -= 1

while n:
    bombL = deque()
    findBomb()
    allBombSet()
    n -= 1
    if n == 0:
        break
    bomb()
    n -= 1

for i in range(r):
    for j in range(c):
        print(data[i][j], end='')
    print()
