from itertools import combinations
from collections import deque

grid = [input().rstrip() for _ in range(5)]
arr = [(r, c) for r in range(5) for c in range(5)]
comb = list(combinations(arr, 7))  # 480700
dRow, dCol = [0, 0, 1, -1], [1, -1, 0, 0]

answer = 0
for _comb in comb:
    cnt_s = 0
    for r, c in _comb:
        if grid[r][c] == 'S':
            cnt_s += 1
    if cnt_s < 4:
        continue
    r, c = _comb[0]
    q = deque()
    q.append((r, c))
    visited = set()
    visited.add((r, c))
    while q:
        _r, _c = q.popleft()
        for d in range(4):
            nr, nc = _r + dRow[d], _c + dCol[d]
            if (nr, nc) in _comb and (nr, nc) not in visited:
                q.append((nr, nc))
                visited.add((nr, nc))
    if len(visited) == 7:
        answer += 1
print(answer)
