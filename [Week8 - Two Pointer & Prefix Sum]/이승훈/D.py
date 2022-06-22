import sys

n = int(input())
ls = list(map(int, input().split()))

ls.sort()

left = 0
right = n-1

ans = [float('inf'), -1, -1]
while left < right:
    lval = ls[left]
    rval = ls[right]

    tot = lval + rval

    if abs(tot) < ans[0]:
        ans = [abs(tot), lval, rval]

    if tot < 0:
        left += 1
    else:
        right -= 1

print(ans[1], ans[2])
