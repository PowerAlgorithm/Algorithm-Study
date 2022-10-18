'''
로봇을 만들던 중에 구멍을 막을 두 레고 조각

구멍의 너비는 x센티미터
두 조각의 길이의 합 = 구멍의 너비

구멍을 막을 수 있는 두 조각을 구하는 프로그램을 작성하시오

테스트의 개수
레고 조각의 수
구멍의 너비 x
1 센티미터 = 10000000 나노미터
'''

import sys
input = sys.stdin.readline

SIZE = 10000000 # 단위, 나노미터 1센티미터

def findRightHole(N, hole, target):
    l, r = 0, N-1

    while l < r:
        tmp = (hole[l] + hole[r])
        
        if tmp == target:
            return [hole[l], hole[r]]
        elif tmp < target:
            l += 1
        else:
            r -= 1
    
    return [0, 0]

while True:
    try:
        x = int(input())
        N = int(input())
        hole = []
        x *= SIZE
        for _ in range(N):
            hole.append(int(input()))
        
        hole.sort()


        ret = findRightHole(N, hole, x)
        if ret == [0, 0]:
            print("danger")
        else:
            print("yes {} {}".format(ret[0], ret[1]))
    except:
        break