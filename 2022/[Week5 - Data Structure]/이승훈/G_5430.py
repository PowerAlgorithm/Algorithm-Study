from collections import deque
import sys
input = sys.stdin.readline


t = int(input())
ans = []
for _ in range(t):
    exp = input().strip()
    n = int(input())
    x = input().strip()[1:-1]
    deq = deque([]) if x == '' else deque(x.split(','))
    left = True
    success = True

    for e in exp:
        if e == 'R':
            left = not left
        else:
            if not deq:
                ans.append('error')
                success = False
                break
            elif left:
                deq.popleft()
            else:
                deq.pop()

    if success:
        if left:
            ans.append('[' + ','.join(deq) + ']')
        else:
            ans.append('[' + ','.join(list(reversed(deq))) + ']')
for a in ans:
    print(a)
