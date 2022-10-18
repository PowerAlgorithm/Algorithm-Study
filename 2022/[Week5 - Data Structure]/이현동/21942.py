import sys
input = sys.stdin.readline

# DD * 24 * 60 (분단위로) + hh
N, D, F = input().rsplit()
N = int(N)
F = int(F)
D = D.replace('/', ' ').replace(':', ' ')
d, h, m = D.split(' ')
D = int(d)*24*60 + int(h)*60 + int(m)
userDict = dict()

def toMinute(start, end):
    # 달 계산
    tmp, tmp1 = 0, 0
    if(start[0] != end[0]):
        for i in range(1, start[0]):
            mm = i
            if(mm == 1 or mm == 3 or mm == 5 or mm ==8 or mm == 7 or mm == 10 or mm == 12):
                tmp += 31
            elif(mm == 4 or mm == 6 or mm == 9 or mm == 11):
                tmp += 30
            elif(mm == 2):
                tmp += 28
        for i in range(1, end[0]):
            mm = i
            if(mm == 1 or mm == 3 or mm == 5 or mm ==8 or mm == 7 or mm == 10 or mm == 12):
                tmp1 += 31
            elif(mm == 4 or mm == 6 or mm == 9 or mm == 11):
                tmp1 += 30
            elif(mm == 2):
                tmp1 += 28
        s = (tmp+start[1])*24*60 + start[2]*60+ start[-1] 
        e = (tmp1+end[1])*24*60 + end[2]*60 + end[-1] 
        
    else:
        s = start[1]*24*60 + start[2]*60 + start[-1] 
        e = end[1]*24*60 + end[2]*60 + end[-1]
    
    return e - s 

for _ in range(N):
    date, time, product, user = input().split(' ')
    user = user.strip()
    yy, mm, dd = map(int, date.split('-'))
    h, m = map(int, time.split(':'))
    if user not in userDict.keys():
        userDict[user] = dict()
    if product not in userDict[user].keys():
        userDict[user][product] = []
    userDict[user][product].append([mm, dd, h, m])


ans = dict()
for user in userDict.keys():
    for product in userDict[user].keys():
        for i in range(0, len(userDict[user][product]), 2):
            tmp = toMinute(userDict[user][product][i], userDict[user][product][i+1])
            if tmp > D:
                if user not in ans.keys():
                    ans[user] = 0
                ans[user] += (tmp-D)*F
            
if len(ans) == 0:
    print(-1)
else:
    ans = sorted(ans.items())
    for user in ans:
        print(*user)
