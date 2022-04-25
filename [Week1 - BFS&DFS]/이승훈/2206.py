import sys
from collections import deque


input = sys.stdin.readline

dy = [0, 1, 0, -1]
dx = [1, 0, -1, 0]
n, m = map(int, input().split())
board = [list(map(int, list(input().strip()))) for _ in range(n)]
visited = [[[False for _ in range(2)] for _ in range(m)] for _ in range(n)]


def bfs(deq):
    while deq:
        curY, curX, cnt, breakWall = deq.popleft()
        if curY == n-1 and curX == m-1:
            return cnt

        for i in range(4):
            ny, nx = curY+dy[i], curX+dx[i]
            if 0 <= ny < n and 0 <= nx < m:
                if board[ny][nx] == 1 and not breakWall:
                    visited[ny][nx][1] = True
                    deq.append([ny, nx, cnt+1, True])
                    continue
                if board[ny][nx] == 0:
                    if breakWall and not visited[ny][nx][1]:
                        visited[ny][nx][1] = True
                        deq.append([ny, nx, cnt+1, breakWall])
                    if not breakWall and not visited[ny][nx][0]:
                        visited[ny][nx][0] = True
                        deq.append([ny, nx, cnt+1, breakWall])
    return -2


visited[0][0][0] = True
print(bfs(deque([[0, 0, 0, False]]))+1)
