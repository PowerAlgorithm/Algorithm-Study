import sys
from copy import deepcopy
from itertools import combinations
input = sys.stdin.readline

exp = input().strip()

par = []
ls = []
for i in range(len(exp)):
    if exp[i] == '(':
        par.append(['(', i])
    elif exp[i] == ')' and par[-1][0] == '(':
        ls.append([par[-1][1], i])
        par.pop()

comb = []
for iter in range(1, len(ls)+1):
    a = [i for i in range(len(ls))]
    comb.extend(list(combinations(a, iter)))

# print(comb, ls)
ans = []
for c in comb:
    tmp = list(exp)
    for e in c:
        # print(ls[e])
        start, end = ls[e]
        tmp[start] = '!'
        tmp[end] = '!'
    ans.append(''.join([t for t in tmp if t != '!']))

ans = list(set(ans))
ans.sort()
for a in ans:
    print(a)
