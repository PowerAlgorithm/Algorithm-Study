import sys
input = sys.stdin.readline


def dfs(x, y, idx, total):
    global ans
    if ans >= total + max_val*(3-idx):
        return
    if idx == 3:
        ans = max(total, ans)
        return
    for i in range(4):
        rx = x + dx[i]
        ry = y + dy[i]
        if 0 <= rx < n and 0 <= ry < m and visited[rx][ry] == 0:
            if idx == 1:
                visited[rx][ry] = 1
                dfs(x, y, idx+1, total+graph[rx][ry])
                visited[rx][ry] = 0
            visited[rx][ry] = 1
            dfs(rx, ry, idx+1, total+graph[rx][ry])
            visited[rx][ry] = 0


n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
ans = 0
max_val = max(map(max, graph))
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
visited = [[0]*m for _ in range(n)]

for i in range(n):
    for j in range(m):
        visited[i][j] = 1
        dfs(i, j, 0, graph[i][j])
        visited[i][j] = 0

print(ans)
