#include <bits/stdc++.h>

int main(int argc, char* argv[])
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr), std::cout.tie(nullptr);

    std::string T, A, res;
    std::cin >> T >> A;

    std::deque<char> dq1, dq2;
    int low = 0, high = A.length() - 1;

    while(low <= high)
    {
        while(low <= high)
        {
            bool chk = false;
            dq1.push_back(A[low++]);

            if((int)dq1.size() >= (int)T.size())
            {
                chk = true;
                for(int i = 0; i < (int)T.size(); i++)
                {
                    if(T[i] != dq1[(int)dq1.size() - T.size() + i])
                    {
                        chk = false;
                        break;
                    }
                }
            }
            if(chk)
            {
                for(int i = 0; i < (int)T.size(); i++)
                    dq1.pop_back();
                break;
            }
        }

        while(low <= high)
        {
            bool chk = false;
            dq2.push_front(A[high--]);

            if((int)dq2.size() >= (int)T.size())
            {
                chk = true;
                for(int i = 0; i < (int)T.size(); i++)
                {
                    if(T[i] != dq2[i])
                    {
                        chk = false;
                        break;
                    }
                }
            }
            if(chk)
            {
                for(int i = 0; i < (int)T.size(); i++)
                    dq2.pop_front();
                break;
            }
        }
    }

    for(int i = 0; i < (int)dq1.size(); i++)
        res += dq1[i];
    for(int i = 0; i < (int)dq2.size(); i++)
        res += dq2[i];
    
    while(res.find(T) < 3e5)
        res.erase(res.find(T), (int)T.size());
    
    std::cout << res << '\n';
    return 0;
}