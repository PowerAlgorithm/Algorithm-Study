import sys
input = sys.stdin.readline

N, M = list(map(int, input().split()))

S = [input().strip() for _ in range(N)]
S = set(S)
ans = 0

for _ in range(M):
    tmp = input().strip()
    if tmp in S:
        ans+=1

print(ans)