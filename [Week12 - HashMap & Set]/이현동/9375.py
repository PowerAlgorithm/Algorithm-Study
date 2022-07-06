import sys
input = sys.stdin.readline

t = int(input())
ans = []

for _ in range(t):
    n = int(input())
    clothes = dict()

    for _ in range(n):
        t = input().strip()
        t = t.split()
        if t[1] in clothes:
            clothes[t[1]].append(t[0])
        else:
            clothes[t[1]] = []
            clothes[t[1]].append(t[0])
    tot = 1
    for i in clothes:
        tot *= (len(clothes[i])+1)
    ans.append(tot-1)

for i in ans:
    print(i)
