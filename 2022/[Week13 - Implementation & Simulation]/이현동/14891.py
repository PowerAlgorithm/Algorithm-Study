import sys
from collections import deque

gear = [deque(list(input())) for _ in range(4)]
k = int(input())



for _ in range(k):
    gear_num, wise = map(int, input().split())
    gear_num -= 1
    tmp = wise
    left, right = gear[gear_num][6], gear[gear_num][2] # 원래 왼쪽 오른쪽 비교해서 확장
    # 왼쪽 방향
    tmp = wise

    for i in range(gear_num-1, 0-1, -1):
        if gear[i][2] != left:    
            left = gear[i][6]
            gear[i].rotate(-1*tmp)
            tmp *= -1
        else:
            break
    # 오른쪽 방향
    tmp = wise
    for i in range(gear_num+1, 4):
        if gear[i][6] != right:    
            right = gear[i][2]
            gear[i].rotate(-1*tmp)
            tmp *= -1
        else:
            break
    gear[gear_num].rotate(wise)
ans=0
for i in range(4):
    if gear[i][0]=='1':
        ans += (2**i)
print(ans)