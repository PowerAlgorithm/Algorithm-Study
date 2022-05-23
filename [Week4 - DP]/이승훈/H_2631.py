import sys

input = sys.stdin.readline
n = int(input())
kids = []
for _ in range(n):
    kids.append(int(input()))

seq = [kids[0]]
for i in range(1, n):
    for idx, s in enumerate(seq):
        if kids[i] < s:
            seq[idx] = kids[i]
            break
    else:
        seq.append(kids[i])

print(n - len(seq))
