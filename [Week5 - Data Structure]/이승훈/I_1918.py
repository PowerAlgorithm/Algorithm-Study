from collections import deque
import sys
input = sys.stdin.readline


exp = input().strip()
oper = []
ans = ''

for e in exp:
    if e.isalpha():
        ans += e
    else:
        if e in ['*', '/']:
            while oper and oper[-1] in ['*', '/']:
                ans += oper.pop()
            oper.append(e)
        elif e in ['+', '-']:
            while oper and oper[-1] != '(':
                ans += oper.pop()
            oper.append(e)
        elif e == '(':
            oper.append(e)
        elif e == ')':
            while oper and oper[-1] != '(':
                ans += oper.pop()
            oper.pop()
for _ in range(len(oper)):
    ans += oper.pop()
print(ans)
