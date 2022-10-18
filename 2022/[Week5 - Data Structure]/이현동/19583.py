import sys
input = sys.stdin.readline

tlst = list(input().rsplit())

for i in range(len(tlst)):
    time = tlst[i].split(':')
    tlst[i] = 60*int(time[0]) + int(time[-1])

# user info
userDic = dict()
users = []
while True:
    tmp = input().strip()
    if tmp == '':
        break
    users.append(tmp)

for userlog in users:
    time, user = userlog.split(' ')
    time = time.split(':')
    if user not in userDic.keys():
        userDic[user] = []
    userDic[user].append(int(time[0])*60 + int(time[-1]))

answer = 0

for user in userDic.keys():
    if len(userDic[user]) <= 1:
        continue
    userDic[user].sort()
    cnt = [0, 0]
    for t in userDic[user]:
        if t <= tlst[0]:
            cnt[0]+=1
        elif tlst[1] <= t <= tlst[-1]:
            cnt[-1]+=1
        
    if cnt[0] >= 1 and cnt[-1] >= 1:
        answer+=1

print(answer)