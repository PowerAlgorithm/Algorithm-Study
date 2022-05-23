// 1918번 후위 표기식 [자료구조 - stack]
/*
<문제 정보>
 1. 주어진 중위표기식을 후위표기식으로 고치기

<변수 범위>
 1. 2초 / 128MB
 2. 주어지는 식은 길이 100이하
 3. 표기식은 알파벳 대문자와 + - * / ( ) 로 이루어져 있다.

<프로그램 진행>
 1.

<필요 함수>
 1.

 */

/*
필요 아스키코드
+ : 43
- : 45
* : 42
/ : 47
( : 40
) : 41

알파벳 대문자 : 65 ~ 90
 */


import java.io.*;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q1918 {
    static final char ADD = '+';
    static final char SUB = '-';
    static final char MUL = '*';
    static final char DIV = '/';
    static final char OPEN = '(';
    static final char CLOSE = ')';
    static final char START = '!';

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        char[] expression = br.readLine().toCharArray();
//        Deque<Character> answer = new ArrayDeque<>();
        Deque<Operator> operators = new ArrayDeque<>();
        int length = expression.length;
        int operatorDepth = 0;
        char operatorBefore = '!'; // 제일 작은 아스키코드
        for (int i=0;i<length;i++) {
            // 1. 이 문자가 알파벳일 경우에는 answer 스택에 넣는다.
            if (expression[i]>='A') sb.append(expression[i]);

            // 2. 연산자 또는 괄호인 경우에는 operatorBefore 를 확인하여 결정
            // 2-1. 연산자가 괄호일 경우
            else if (expression[i] == OPEN || expression[i] == CLOSE) {
                if (expression[i] == CLOSE) {
                    if (operatorBefore==MUL || operatorBefore==DIV) {
                        operatorDepth-=3;
                    }
                    else operatorDepth-=2;
                }
                else { // OPEN 인 경우
                    if (operatorBefore==MUL || operatorBefore==DIV) {
                        operatorDepth+=1;
                    }
                    else operatorDepth+=2;
                }
                operatorBefore = expression[i];
            }

            // 3. 연산자가 + - * / 일 경우 -> 연산자 스택의 맨 마지막 연산자의 depth보다 크면 add, 자신보다 작은것이 나올때까지 poll
            else {
                // operatorDepth 의 변화 먼저
                operatorDepth = changeDepth(operatorBefore, expression[i], operatorDepth);
                if (operatorDepth == Integer.MAX_VALUE) return; // 에러 일 경우
                /*if (!operators.isEmpty()) {
                    System.out.printf("now : %c, operatorDepth : %d, stack의 마지막 Depth : %d\n", expression[i], operatorDepth, operators.peekLast().depth);
                }*/

                while(!operators.isEmpty() && operatorDepth <= operators.peekLast().depth) sb.append(operators.pollLast().operator);
                operators.add(new Operator(expression[i], operatorDepth));
                operatorBefore = expression[i];
            }
        }

        // 표현식을 전부 돌면 나머지 연산자를 붙여줌
        while(!operators.isEmpty()) sb.append(operators.pollLast().operator);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 여기서 now는 + - * / 중 하나
    public static int changeDepth (char before, char now, int depthBefore) {
        if (before == START) return depthBefore;

        if (before == now) return depthBefore;

        if (before == OPEN || before == CLOSE) {
            if (now == MUL || now == DIV) return depthBefore+1;
            else return depthBefore;
        }

        if (before == MUL || before == DIV) {
            if (now == MUL || now == DIV) return depthBefore;
            else return depthBefore-1;
        }

        if (before == ADD || before == SUB) {
            if (now == ADD || now == SUB) return depthBefore;
            else return depthBefore+1;
        }

        // 이 위에서 다 끝나야 함
        System.out.printf("before : %c, now : %c\n",before, now);
        System.out.println("ERROR");
        return Integer.MAX_VALUE;
    }
}

class Operator {
    char operator;
    int depth;

    public Operator (char operator, int depth) {
        this.operator = operator;
        this.depth = depth;
    }
}
























