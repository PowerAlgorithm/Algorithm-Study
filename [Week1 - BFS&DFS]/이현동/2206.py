import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())
graph = [input() for _ in range(N)]
visited = [[[-1 for _ in range(2)] for _ in range(M)] for _ in range(N)]
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

# 범위 내에 있는지 확인


def checkRange(rx, ry):
    if 0 <= rx < N and 0 <= ry < M:
        return True
    return False


def bfs(x, y):
    visited[x][y][0] = 1
    q = deque([])
    q.append([x, y, 0])  # 벽을 부순뒤에 visited[][][1] 로 체크한다.
    while q:
        x, y, flag = q.popleft()
        for i in range(4):
            rx, ry = x + dx[i], y + dy[i]
            if checkRange(rx, ry): # 범위 체크
                # 벽 부수기 쓰기전 + 벽 부수기 시전 후
                if graph[rx][ry] == '0' and visited[rx][ry][flag] == -1: 
                    visited[rx][ry][flag] = visited[x][y][flag] + 1
                    q.append([rx, ry, flag])
                # 벽 부수기 전에 벽을 부숴야 하는 상황
                if graph[rx][ry] == '1' and flag == 0 and visited[rx][ry][flag+1] == -1: 
                    visited[rx][ry][flag+1] = visited[x][y][flag] + 1
                    q.append([rx, ry, 1])


bfs(0, 0)

if visited[N-1][M-1] == [-1, -1]:
    print(-1)
else:
    if visited[N-1][M-1][0] == -1:
        print(visited[N-1][M-1][1])
    elif visited[N-1][M-1][1] == -1:
        print(visited[N-1][M-1][0])
    else:
        print(min(visited[N-1][M-1]))
