import sys
from collections import deque


input = sys.stdin.readline

dy = [0, 1, 0, -1]
dx = [1, 0, -1, 0]
n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]


def bfs(deq, visited):
    meltList = []
    while deq:
        curY, curX = deq.popleft()
        for i in range(4):
            ny, nx = curY+dy[i], curX+dx[i]
            if 0 <= ny < n and 0 <= nx < m:
                if board[ny][nx] == 0:
                    meltList.append([curY, curX])
                    continue
                if not visited[ny][nx]:
                    deq.append([ny, nx])
                    visited[ny][nx] = True
    for y, x in meltList:
        board[y][x] = board[y][x]-1 if board[y][x] > 0 else 0


def main():
    cnt = 0
    while True:
        endCnt = 0
        visited = [[False]*m for _ in range(n)]
        for i in range(n):
            for j in range(m):
                if board[i][j] != 0 and not visited[i][j]:
                    if endCnt > 0:
                        return cnt
                    visited[i][j] = True
                    bfs(deque([[i, j]]), visited)
                    endCnt += 1
        if endCnt == 0:
            return 0
        cnt += 1


print(main())
