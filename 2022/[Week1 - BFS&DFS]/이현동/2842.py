import sys
from collections import deque
input = sys.stdin.readline


def checkRange(rx, ry):
    if 0 <= rx < N and 0 <= ry < N:
        return True
    return False


def bfs(q, x, y, lo, hi):
    p = 0
    visited = [[False]*N for _ in range(N)]
    visited[x][y] = True

    while q:
        x, y = q.popleft()
        for i in range(8):
            rx, ry = x + dx[i], y + dy[i]
            if checkRange(rx, ry):
                if visited[rx][ry]:
                    continue
                if (lo <= ff[rx][ry] <= hi):
                    visited[rx][ry] = True
                    q.append([rx, ry])
                    if graph[rx][ry] == 'K':
                        p += 1  # 피로도 증가
    return p


N = int(input())
graph = []  # 그래프
dx = [0, 0, -1, 1, -1, 1, -1, 1]
dy = [-1, 1, -1, 1, 0, 0, 1, -1]
start = []
poffice = []
fatigue = []  # 피로도
ff = []


for i in range(N):
    tmp = list(input().strip())
    graph.append(tmp)
    for j in range(N):
        if tmp[j] == 'P':
            start = [i, j]
        elif tmp[j] == 'K':
            poffice.append([i, j])

for i in range(N):
    ftmp = list(map(int, input().split()))
    ff.append(ftmp)
    fatigue.extend(ftmp)

fatigue = sorted(set(fatigue))
l, r = 0, 0
ans = sys.maxsize


while l < len(fatigue):
    q = deque([])
    t = 0
    if fatigue[l] <= ff[start[0]][start[-1]] <= fatigue[r]:
        q.append([start[0], start[-1]])
        
    t = bfs(q, start[0], start[1], fatigue[l], fatigue[r])
    if t == len(poffice):
        ans = min(abs(fatigue[l]-fatigue[r]), ans)        
        l += 1
    elif r + 1 < len(fatigue):
        r += 1
    else:
        break


print(ans)
