from collections import deque

R, C = 12, 6
grid = [list(input().rstrip()) for _ in range(R)]
dRow, dCol = [0, 0, 1, -1], [1, -1, 0, 0]


def get_explosive_puyo():
    candidates = []
    visited = [[False for _ in range(C)] for _ in range(R)]
    for r in range(R):
        for c in range(C):
            if grid[r][c] == '.':
                continue
            elif visited[r][c]:
                continue
            q = deque()
            q.append((r, c))
            visited[r][c] = True
            members = [(r, c)]
            while q:
                _r, _c = q.popleft()
                for d in range(4):
                    nr, nc = _r + dRow[d], _c + dCol[d]
                    if nr < 0 or nc < 0 or nr >= R or nc >= C:
                        continue
                    elif grid[nr][nc] != grid[r][c]:
                        continue
                    elif visited[nr][nc]:
                        continue
                    q.append((nr, nc))
                    visited[nr][nc] = True
                    members.append((nr, nc))
            if len(members) >= 4:
                candidates.append(members)
    return candidates


def activate_gravity():
    for c in range(C):
        empty = 0
        for r in range(R - 1, -1, -1):
            if grid[r][c] != '.':
                if empty == 0:
                    continue
                grid[r + empty][c] = grid[r][c]
                grid[r][c] = '.'
            else:
                empty += 1


answer = 0
while True:
    explosive_puyos = get_explosive_puyo()
    if not explosive_puyos:
        break
    for puyo in explosive_puyos:
        for r, c in puyo:
            grid[r][c] = '.'
    activate_gravity()
    answer += 1

print(answer)
