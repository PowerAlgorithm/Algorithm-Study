import sys
from collections import deque


input = sys.stdin.readline

m, n = map(int, input().split())
board = [list(input().strip()) for _ in range(m)]
dy = [0, 1, 0, -1]
dx = [1, 0, -1, 0]
deq = deque()
for i in range(n):
    if board[0][i] == '0':                  # 가장 위에 전류가 통하는 곳을 deq에 넣음
        deq.append([0, i])

visited = [[False] * n for _ in range(m)]
for startY, startX in deq:                  # 가장 위에 전류가 통하는 곳의 방문처리
    visited[startY][startX] = True

success = False
while deq:
    curY, curX = deq.popleft()
    if curY == m-1:
        success = True
        break

    for i in range(4):
        ny, nx = curY+dy[i], curX+dx[i]
        if 0 <= ny < m and 0 <= nx < n \
                and board[ny][nx] == '0' and not visited[ny][nx]:
            deq.append([ny, nx])
            visited[ny][nx] = True

if success:
    print('YES')
else:
    print('NO')
