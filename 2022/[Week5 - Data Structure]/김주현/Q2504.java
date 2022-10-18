// 2504번 괄호의 값(S2) [stack]
/*
<문제 정보>
 1. 괄호열이 나타내는 숫자 출력
    - ():2 []:3
    - 올바른 괄호열이 아니라면 0

<변수 범위>
 1. 1초 / 128MB
 2. 괄호열 문자 길이 1~30

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q2504 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] brackets =  br.readLine().toCharArray();
        int[] depthNum = new int[brackets.length];
        Deque<Character> stack = new ArrayDeque<>();
        boolean possible=true;
        int depth=0;
        for (char bracket : brackets) {
            // 괄호 열면 괄호 깊이를 증가시키고 stack에 추가하세요.
            if (bracket=='(' || bracket=='[') {
                stack.add(bracket);
                depth++;
                continue;
            }

            /*
            괄호가 닫히면
                1. 열린괄호가 없거나 짝이 안맞으면 OUT
                2. 짝이 맞는다면
                    - 괄호 안에 아무것도 없었다면(depthNum[depth]==0) 바로 이전의 깊이 값에 2 또는 3을 더하고
                    - 괄호 안에 무언가 있었다면 그 값에 2또는 3을 곱해서 바로 이전 깊이에 더하세요.
                    - 지금 깊이에 해당하는 값을 0으로 만들고 다음 괄호로 넘어갑니다.
             */
            if (bracket==')' || bracket==']') {
                if(!stack.isEmpty()
                    || (bracket==')' && stack.peekLast()!='(')
                    || (bracket==']' && stack.peekLast()!='[')) {
                    possible=false;
                    break;
                }
                char now = stack.pollLast();
                if(depthNum[depth]==0) depthNum[depth-1]+= (now=='(') ? 2 : 3;
                else {
                    depthNum[depth-1]+= (now=='(') ? 2*depthNum[depth] : 3*depthNum[depth];
                    depthNum[depth]=0;
                }
                depth--;
            }
        }
        bw.write(String.valueOf(possible ? depthNum[0] : 0));

        bw.flush();
        bw.close();
        br.close();
    }
}
















