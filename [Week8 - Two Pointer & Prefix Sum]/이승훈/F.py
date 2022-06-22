from heapq import *

n, m = map(int, input().split())
ls = [0] + list(map(int, input().split()))
section = [list(map(int, input().split())) for _ in range(m)]

for i in range(1, n+1):
    ls[i] += ls[i-1]

for i, j in section:
    print(ls[j] - ls[i-1])
