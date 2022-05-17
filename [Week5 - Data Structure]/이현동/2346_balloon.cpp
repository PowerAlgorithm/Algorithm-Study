#include <bits/stdc++.h>
using namespace std;
using pii = std::pair<int, int>;

int N;
deque<pii> element;


int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    cin >> N;
    for (int i = 0; i < N;i++){
        int o;
        cin >> o;
        element.push_back({o, i + 1});
    }

    
    while (!element.empty())
    {
        int place = element.front().first;
        cout << element.front().second << ' ';
        element.pop_front();

        if (place > 0){
            place--;
            while (place != 0)
            {
                element.push_back(element.front());
                element.pop_front();
                place--;
            }
        }else{
            while (place != 0){
                element.push_front(element.back());
                element.pop_back();
                place++;
            }
        }
    }
    
    return 0;
}




// 5
// 3 2 1 -3 -1
// 1 2 3 4 5

// 3
// 1
// 1 -3 -1 2
// 3 4 5 2
// 2
// -3 -1 2 1
// 4 5 2 3

// [1 4]
// -3
// -2
// -1 2 1
// 5 2 3

// -1
// 2 1 -1
// 2 3 5

// 1 -1 2
// 3 5 2
