import sys
from collections import deque
input = sys.stdin.readline


def checkRange(rx, ry):
    if 0 <= rx < N and 0 <= ry < N:
        return True
    return False


def bfs(x, y):
    q = deque([])
    visited[x][y] = fatigue[x][y]
    q.append([x, y])
    res = []
    p = 0
    while q:
        x, y = q.popleft()
        tmp = deque([])
        minDiff = int(1e9)  # 최소 피로도 저장
        flag = False
        for i in range(8):
            rx, ry = x + dx[i], y + dy[i]
            if checkRange(rx, ry):
                if graph[rx][ry] == 'K' and not visited[rx][ry]:
                    flag = True
                    q.append([rx, ry])
                    visited[rx][ry] = fatigue[rx][ry]
                    p += 1  # 피로도 증가
                    if p == post:
                        return
                elif graph[rx][ry] == '.':
                    tmp.append([rx, ry])
                    minDiff = min(minDiff, abs(
                        fatigue[rx][ry] - fatigue[x][y]))
        while tmp and not flag:
            tx, ty = tmp.popleft()
            if abs(fatigue[tx][ty] - fatigue[x][y]) == minDiff:
                q.append([tx, ty])
                visited[tx][ty] = fatigue[tx][ty]


N = int(input())
graph = []  # 그래프
visited = [[0]*N for _ in range(N)]
dx = [0, 0, -1, 1, -1, 1, -1, 1]
dy = [-1, 1, -1, 1, 0, 0, 1, -1]
start = []
post = 0

for i in range(N):
    graph.append(list(input().strip()))
    for j in range(N):
        if graph[i][j] == 'P':
            start = [i, j]
        elif graph[i][j] == 'K':
            post += 1

fatigue = [list(map(int, input().split())) for _ in range(N)]  # 피로도

bfs(start[0], start[1])

res = []
for i in range(N):
    print(visited[i])
    for j in range(N):
        if visited[i][j] != 0:
            res.append(visited[i][j])

print(max(res)-min(res))
