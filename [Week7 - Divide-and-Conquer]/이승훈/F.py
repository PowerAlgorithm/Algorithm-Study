n = int(input())
s = 'moo'
k = 0
length = len(s)
while length <= n:
    k += 1
    length = length * 2 + k+3


def moo(cur_len, k, n):
    prev_len = (cur_len - k)//2
    if n < prev_len:
        moo(prev_len, k-1, n)
    elif prev_len + k <= n:
        moo(prev_len, k-1, n - (prev_len+k))
    else:
        if prev_len == n-1:
            print('m')
        else:
            print('o')


moo(length, k+3, n)
