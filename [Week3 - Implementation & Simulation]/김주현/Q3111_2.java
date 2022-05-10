// 번
/*
<문제 정보>
 1.

<변수 범위>
 1.

<프로그램 진행>
--- 스택으로 풀이 ---
 1. 양쪽에서 단어가 발견되면 번갈아가며 쌓아간다.
 2. 둘이 만나면 그때부턴 하나로만 진행
 3. 만나는 접점에서 해당단어 발견이 안된다면 프로그램 종료

<필요 함수>
 1.

 */


import java.io.*;
import java.util.Deque;
import java.util.ArrayDeque;

public class Q3111_2 {
    static final char EMPTY = '0';

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        char[] word = br.readLine().toCharArray();
        char[] text = br.readLine().toCharArray();
        Deque<Character> frontStack = new ArrayDeque<>();
        Deque<Character> backStack = new ArrayDeque<>();
        Deque<Character> temp = new ArrayDeque<>();
        char tmp;
        boolean isSame;
        if (word.length==0) {
            System.out.println(String.valueOf(text));
            return;
        }
        char wordFirstChar = word[0];
        char wordLastChar = word[word.length - 1];

        int lo = 0;
        int hi = text.length-1;

        while(lo<=hi) {
            // -------------------------- 앞 탐색 --------------------------------

            for (int i=lo; i<=hi; i++) {
            /*System.out.printf("이번 문자 : %c\n",text[i]);
            for (char c : stack) System.out.print(c);
            System.out.println();*/

                isSame = false;
                // 이번 문자가 주어진 단어의 마지막 문자라면
                if (text[i] == wordLastChar) {
                    isSame = true;
                    for (int j = 0; j<word.length-1; j++) {
                        tmp = frontStack.peekLast()==null ? EMPTY : frontStack.pollLast();
                        if(tmp != EMPTY) temp.add(tmp);
                        if (tmp==EMPTY || tmp != word[word.length-2-j]) {
                            /*System.out.println("다시 돌려줄때 temp 상태");
                            for (char c : temp) System.out.print(c);
                            System.out.println("\n----------------");*/
                            isSame = false;
                            while(!temp.isEmpty()) frontStack.add(temp.pollLast());
                            break;
                        }
                    }
                }
                if(!isSame) frontStack.add(text[i]);
                else {
                    temp.clear();
                    lo++;
                    break;
                }
                lo++;
            }

            // -------------------------- 앞 탐색 끝 --------------------------------

            // -------------------------- 뒤 탐색 --------------------------------

            for (int i=hi; i>=lo; i--) {
//            System.out.printf("이번 문자 : %c\n",text[i]);
            /*for (char c : stack) System.out.print(c);
            System.out.println();*/

                isSame = false;
                // 이번 문자가 주어진 단어의 첫번째 문자라면
                if (text[i] == wordFirstChar) {
                    isSame = true;
                    for (int j = 0; j<word.length-1; j++) {
                        tmp = backStack.peekLast()==null ? EMPTY : backStack.pollLast();
                        if(tmp != EMPTY) temp.add(tmp);
//                        System.out.printf("tmp : %c, word[j+1] : %c\n",tmp, word[j+1]);
                        if (tmp==EMPTY || tmp != word[j+1]) {
                            /*System.out.println("다시 돌려줄때 temp 상태");
                            for (char c : temp) System.out.print(c);
                            System.out.println("\n----------------");*/
                            isSame = false;
                            while(!temp.isEmpty()) backStack.add(temp.pollLast());
                            break;
                        }
                    }
                }
                if(!isSame) backStack.add(text[i]);
                else {
                    temp.clear();
                    hi--;
                    break;
                }
                hi--;
            }

            // -------------------------- 뒤 탐색 끝--------------------------------

            /*System.out.println("FrontStack Status");
            for (char c : frontStack) System.out.print(c);
            System.out.println("\nBackQueue Status");
            for (char c : backStack) System.out.print(c);
            System.out.println();
            System.out.printf("lo : %d, hi : %d\n",lo, hi);*/
        }

        // 이제 앞과 뒤를 합치면서 단어 골라냄
        // Front에 Back을 하나씩 추가하면서 검열 과정 진행

        char charFromBackStack;
        while(!backStack.isEmpty()) {
            isSame = false;
            charFromBackStack = backStack.pollLast();
            if (charFromBackStack == wordLastChar) {
                isSame = true;
                for (int j = 0; j<word.length-1; j++) {
                    tmp = frontStack.peekLast()==null ? EMPTY : frontStack.pollLast();
                    if(tmp != EMPTY) temp.add(tmp);
                    if (tmp==EMPTY || tmp != word[word.length-2-j]) {
                            /*System.out.println("다시 돌려줄때 temp 상태");
                            for (char c : temp) System.out.print(c);
                            System.out.println("\n----------------");*/
                        isSame = false;
                        while(!temp.isEmpty()) frontStack.add(temp.pollLast());
                        break;
                    }
                }
            }
            if(!isSame) frontStack.add(charFromBackStack);
            else temp.clear();

            /*System.out.println("\nFrontStack Status");
            for (char c : frontStack) System.out.print(c);*/
        }




//        System.out.println("\n결과");
        // 검열후 문자는 stack에 남아있는 문자
        for (char c : frontStack) sb.append(c);




        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

