import sys
input = sys.stdin.readline


n = int(input())
ls = list(map(int, input().split()))

top = [[0, 0]]  # val, pos
ans = []
for i in range(0, n):
    while top and top[-1][0] < ls[i]:
        top.pop()
    if top:
        ans.append(top[-1][1]+1)
    else:
        ans.append(0)
    top.append([ls[i], i])

for a in ans:
    print(a, end=' ')
