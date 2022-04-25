import sys
from collections import deque


input = sys.stdin.readline

dy = [-1, 1, -2, 2, -2, 2, -1, 1]
dx = [-2, -2, -1, -1, 1, 1, 2, 2]
n, m = map(int, input().split())
startY, startX = map(int, input().split())
startY, startX = startY-1, startX-1
horses = []
ans = [0] * m
for i in range(m):
    y, x = map(int, input().split())
    horses.append([y-1, x-1])


def bfs(deq, visited):
    while deq:
        curY, curX = deq.popleft()
        cnt = visited[curY][curX]
        for i in range(8):
            ny, nx = curY+dy[i], curX+dx[i]
            if 0 <= ny < n and 0 <= nx < n and visited[ny][nx] == -1:
                deq.append([ny, nx])
                visited[ny][nx] = cnt+1


visited = [[-1 for _ in range(n)] for _ in range(n)]
visited[startY][startX] = 0
bfs(deque([[startY, startX]]), visited)
for h in horses:
    print(visited[h[0]][h[1]], end=' ')
