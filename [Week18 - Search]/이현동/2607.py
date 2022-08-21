import sys
input = sys.stdin.readline

N = int(input())
words = [[0 for _ in range(26)] for _ in range(N)]

for i in range(N):
    tmp = input().strip()
    for s in tmp:
        words[i][ord(s) - ord('A')] += 1

answer = 0
for word in words[1:]:
    tmp = [0, 0]
    for i in range(26):
        if word[i] > words[0][i]:
            tmp[0] += (word[i]-words[0][i])
        else:
            tmp[1] += (words[0][i]-word[i])
    
    if tmp[0] < 2 and tmp[1] < 2:
        answer += 1

print(answer)