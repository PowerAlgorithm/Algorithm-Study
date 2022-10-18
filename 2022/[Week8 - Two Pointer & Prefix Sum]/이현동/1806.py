import sys
input = sys.stdin.readline

n, s = map(int, input().split())
arr = list(map(int, input().split()))
l, r = 0, 0
tot = 0
ans = sys.maxsize

while True:
    if tot >= s:
        ans = min(r-l, ans)
        tot -= arr[l]
        l += 1
    elif r == n:
        break
    else:
        tot += arr[r]
        r += 1

print(ans) if ans != sys.maxsize else print(0)