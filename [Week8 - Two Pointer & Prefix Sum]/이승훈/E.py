from heapq import *

n, m = map(int, input().split())
ability = [list(sorted(map(int, input().split()))) for _ in range(n)]

heap = []  # 값, 학급, 위치
maxVal = 0
for i in range(n):
    maxVal = max(ability[i][0], maxVal)
    heappush(heap, [ability[i][0], i, 0])

ans = float('inf')
while True:
    minVal, idx, pos = heappop(heap)
    ans = min(maxVal - minVal, ans)
    if pos + 1 >= m:
        break
    nextVal = ability[idx][pos+1]
    heappush(heap, [nextVal, idx, pos+1])
    maxVal = max(nextVal, maxVal)

print(ans)
