import sys
input = sys.stdin.readline

t = int(input())
ans = []

for _ in range(t):
    op = input().rstrip()
    n = int(input())
    l = input().strip()
    if n == 0:
        l = []
    else:
        l = l[1:len(l)-1]
        l = l.split(',')
        l = [int(j) for j in l]
    cnt = 0
    # print(op)
    for i in op:
        flag = False
        if i == "R":
            cnt += 1
        elif i == "D":
            if len(l) == 0:
                ans.append('error')
                flag = True
                break
            else:
                if cnt % 2:
                    l.pop()
                else:
                    l.pop(0)

    if cnt % 2:
        l.reverse()
    if not flag:
        l = str(l)
        l = l.replace(" ", "")
        ans.append(l)

for i in ans:
    print(i)
