import sys
input = sys.stdin.readline

N, C = map(int, input().split())
mes = dict()
tmp = list(map(int, input().split()))

for i in range(N):
    if tmp[i] not in mes.keys():
        mes[tmp[i]] = 0
    mes[tmp[i]] += 1

res = sorted(mes.items(), key=lambda x:-x[-1])

for i in res:
    for j in range(i[-1]):
        print(i[0], end = ' ')
