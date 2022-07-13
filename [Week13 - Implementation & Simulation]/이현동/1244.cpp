#include <bits/stdc++.h>
using namespace std;

/*
1 - 스위치 on
0 - 스위치 off

성별과 받은 수에 따라 스위치 조작
남 - 스위치 번호가 자기가 받은 수의 배수 -> 스위치 상태 바꾼다(on -> off, off -> on).
ex) 남학생 3 -> 3, 6의 상태를 바꾼다.

여 - 자기가 받은 수와 같은 번호가 붙은 스위치 중심 좌우가 대칭 + 가장 많은 스위치 포함된 구간 찾아 
스위치 상태 반전, 구간에 속한 스위치 홀수 개 

*/

int N, T;
int arr[101];

void oper(int gender, int swit){
    if(gender == 1){ //남
        for (int i = swit; i <= N;i += swit)
            arr[i] = !arr[i];
    } else if(gender == 2){ //여
        int l = swit-1;
        int r = swit+1;
        arr[swit] = !arr[swit];
        while (l != 0 && r != N + 1)
        {
            if(arr[l] != arr[r]){
                break;
            }
            arr[l] = !arr[l];
            arr[r] = !arr[r];
            l--;
            r++;
        }
    }
    return;
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    arr[0] = -1;
    for (int i = 1; i <= N; i++)
        cin >> arr[i];

    cin >> T;
    int gen, op;
    for (int i = 0; i < T; i++)
    {
        cin >> gen >> op;
        oper(gen, op);
    }
    for (int i = 1; i <= N;i++){
        cout << arr[i] << ' ';
        if(i % 20 == 0)
            cout << '\n';
        
    }

        return 0;
}
// 25
// 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0 1 0
// 1
// 1 1