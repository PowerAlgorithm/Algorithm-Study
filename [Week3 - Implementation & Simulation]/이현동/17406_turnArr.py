import sys
import copy
from itertools import permutations

N, M, T = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]


def minCol(arr, size):
    ans = int(1e9)
    for i in range(size):
        ans = min(ans, sum(arr[i]))
    return ans

def turn(arr, r, c, s):
    
    for k in range(s, 0, -1):
        prev = arr[r-k][c-k]
        curr = arr[r-k][c-k]
        for i in range(c-k+1, c+k+1):
            curr = arr[r-k][i]
            arr[r-k][i] = prev
            prev = curr
        for i in range(r-k+1, r+k+1):
            curr = arr[i][c+k]
            arr[i][c+k] = prev
            prev = curr
        for i in range(c+k-1, c-k-1, -1):
            curr = arr[r+k][i]
            arr[r+k][i] = prev
            prev = curr
        for i in range(r+k-1, r-k-1, -1):
            curr = arr[i][c-k]
            arr[i][c-k] = prev
            prev = curr
    return arr

test = [list(map(int, input().split())) for _ in range(T)]

res = int(1e9)

for i in permutations([j for j in range(T)], T):
    tmp = copy.deepcopy(arr)
    for p in i:
        R, C, S = test[p]
        tmp = turn(tmp, R-1, C-1, S)
    res = min(minCol(tmp, N), res)
print(res)