from collections import deque
import sys
input = sys.stdin.readline


n = int(input())
k = int(input())
apples = [list(map(lambda x: x-1, map(int, input().split())))
          for _ in range(k)]
l = int(input())
moves = []
for _ in range(l):
    sec, dir = input().split()
    moves.append([int(sec), dir])
moves.sort(key=lambda x: x[0], reverse=True)
snake = deque([[0, 0]])  # y, x
dy = [0, 1, 0, -1]
dx = [1, 0, -1, 0]

dir = 0
for sec in range(0, 10001):
    head_y, head_x = snake[0]

    if moves and sec == moves[-1][0]:
        if moves[-1][1] == 'D':
            dir = (dir + 1) % 4
        else:
            dir = (dir - 1) % 4
        moves.pop()

    ny, nx = head_y + dy[dir], head_x + dx[dir]
    if not(0 <= ny < n and 0 <= nx < n) or [ny, nx] in snake:  # 다음이 벽임 or 몸통임
        print(sec+1)
        break
    if [ny, nx] in apples:  # 사과 만남
        apples.remove([ny, nx])
        snake.appendleft([ny, nx])
    else:  # 그냥 움직임
        snake.appendleft([ny, nx])
        snake.pop()
