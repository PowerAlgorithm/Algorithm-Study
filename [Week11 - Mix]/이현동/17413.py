import sys
input = sys.stdin.readline

S = input().rstrip()
st = []
ans = ''
flag = False

for i in S:
    if i == '<':
        flag = True
        while st:
            ans += st[-1]
            st.pop()
        ans += i
    elif i =='>':
        ans += i
        flag = False
    elif (i.isalpha() or i.isnumeric() or i == ' ') and flag:
        ans += i
    elif i == ' ' and not flag:
        while st:
            ans += st[-1]
            st.pop()
        ans += i
    else:
        st.append(i)

while st:
    ans += st[-1]
    st.pop()

print(ans)