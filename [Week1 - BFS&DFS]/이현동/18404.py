import sys
from collections import deque
input = sys.stdin.readline


def checkRange(rx, ry):
    if 0 <= rx < N and 0 <= ry < N:
        return True
    return False


def bfs(x, y):
    q = deque([])
    q.append([x, y])
    visited[x][y] = 0
    while q:
        x, y = q.popleft()
        for rx, ry in directions:
            dx, dy = x + rx, y + ry
            if checkRange(dx, dy) and visited[dx][dy] == -1:
                visited[dx][dy] = visited[x][y] + 1
                q.append([dx, dy])


N, M = map(int, input().split())
knights = []
X, Y = map(int, input().split())
X -= 1
Y -= 1
visited = [[-1]*N for _ in range(N)]
directions = [
    (-2, -1), (-2, 1), (-1, -2), (-1, 2),
    (1, -2), (1, 2), (2, -1), (2, 1)
]


for _ in range(M):
    a, b = map(int, input().split())
    knights.append([a-1, b-1])

bfs(X, Y)

for x, y in knights:
    print(visited[x][y], end=' ')
