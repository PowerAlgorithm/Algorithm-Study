import sys
input = sys.stdin.readline

S = input().strip()
ans = set()
N = len(S)

for i in range(1, N+1):
    for j in range(0, N):
        ans.add(S[j:j+i])
print(len(ans))