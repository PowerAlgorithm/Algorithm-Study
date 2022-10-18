// 2504번 괄호의 값(S1)
/*
<문제 정보>
 1.

<변수 범위>
 1.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;

public class Q2504_2 {
    static char[] parentheses;
    static int searchedIndex=-1;
    static int length;

    static final int IMPOSSIBLE = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        parentheses = br.readLine().toCharArray();
        length = parentheses.length;

        if (parentheses[0] == ')' || parentheses[0] == ']') {
            System.out.println(0);
            return;
        }
        int value=0;
        int eachValue;
        while(searchedIndex<parentheses.length-1) {
            eachValue = getValue(++searchedIndex);
            if (eachValue == IMPOSSIBLE) {
                value = 0;
                break;
            }
            value+=eachValue;
        }

        sb.append(value);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // ( 가 열리면 )가 닫히는 순간에 return 2*value;
    // [ 가 열리면 ]가 닫히는 순간에 return 3*value;
    // 중간 value 가 0이라면 2 또는 3 return
    public static int getValue(int index) {
        searchedIndex = index;
//        System.out.printf("[IN] index %d\n",index);
        if (searchedIndex == length-1) return IMPOSSIBLE;

        char parenthesis = parentheses[index];
        if (parenthesis==')' || parenthesis==']') return IMPOSSIBLE;

        int times = parenthesis=='(' ? 2 : 3;

        int myValue = 0;
        // 먼저 다음에 바로 괄호가 닫히는지 확인
        if (parentheses[index+1] == ')' || parentheses[index+1] == ']') {
            // 불가능한 괄호면 IMPOSSIBLE return
            if ( (times==2 && parentheses[index+1] == ']') || (times==3 && parentheses[index+1] == ')')) {
                return IMPOSSIBLE;
            }
            else {
                searchedIndex = index+1;
                return times;
              }
        }

        // 다음 괄호도 열리는 괄호라면, 현재 괄호가 닫힐때까지 괄호값은 myValue에 누적해서 합
        int eachValue;
        while(true) {
            eachValue = getValue(searchedIndex+1);
            if (eachValue == IMPOSSIBLE || searchedIndex == length-1) {
//                System.out.printf("[OUT] index %d\n",index);
                return IMPOSSIBLE;
            }
            myValue += eachValue;
            if (parentheses[searchedIndex+1] == (times==2 ? ')' : ']')) {
                searchedIndex++;
                break;
            }
        }
        return times*myValue;
    }
}
















