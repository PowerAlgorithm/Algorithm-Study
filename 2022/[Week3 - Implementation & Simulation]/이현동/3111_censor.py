import sys
from collections import deque

A = sys.stdin.readline().strip()
T = sys.stdin.readline().strip()
rA = A[::-1]
size = len(T)
start, end = 0, size-1

front, back = [], []

while start <= end:
    while start <= end:
        front.append(T[start])
        start += 1
        if len(front) >= len(A) and ''.join(front[-len(A):]) == A:
            del front[-len(A):]
            break
    
    while start <= end:
        back.append(T[end])
        end -= 1
        if len(back) >= len(A) and ''.join(back[-len(A):]) == rA:
            del back[-len(A):]
            break

tmp = front + back[::-1]
idx = 0
while idx >= 0:
    idx = ''.join(tmp).find(A)
    if idx != -1:
        del tmp[idx:idx+len(A)]

print(''.join(tmp))