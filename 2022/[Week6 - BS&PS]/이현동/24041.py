import sys
input = sys.stdin.readline

N, G, K = map(int, input().split())
millkit = dict()
millkit[0] = []
millkit[1] = []

for _ in range(N):
    s, l, o = list(map(int, input().split()))
    millkit[o].append((s, l))

ans = 0
left, right = 0, int(2e9)

while left <= right:
    mid = (left+right)//2
    cnt = 0
    
    for s, l in millkit[0]:
        cnt += s*max(1, mid-l)
    millkit[1].sort(key = lambda x:-x[0]*max(1, mid-x[1]))

    for i in range(K, len(millkit[1])):
        s, l = millkit[1][i]
        cnt += s*max(1, mid-l)

    if cnt <= G:
        ans = mid
        left = mid + 1
    else:
        right = mid - 1

print(ans)