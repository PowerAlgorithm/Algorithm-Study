import sys
input = sys.stdin.readline

n = int(input())
dy = [0, -1, 0, 1]
dx = [1, 0, -1, 0]
dragon_set = set()
visited = [[False]*101 for _ in range(101)]


def dragon_curve(dir, gen):
    prev_curve = [dir]
    for _ in range(gen):
        new_curve = [(p+1) % 4 for p in reversed(prev_curve)]
        prev_curve = prev_curve + new_curve
    return prev_curve


def drawVisited(x, y, line):
    visited[y][x] = 1
    for l in line:
        nx = x + dx[l]
        ny = y + dy[l]
        visited[ny][nx] = 1
        x, y = nx, ny


for i in range(n):
    x, y, d, g = map(int, input().split())
    curve = [d]
    line = dragon_curve(d, g)
    drawVisited(x, y, line)

ans = 0
for i in range(100):
    for j in range(100):
        if visited[i][j] and visited[i+1][j] \
                and visited[i][j+1] and visited[i+1][j+1]:
            ans += 1
print(ans)
