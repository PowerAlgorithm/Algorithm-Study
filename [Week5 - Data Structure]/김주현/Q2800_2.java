/*
// 2800번 괄호 제거 (G5)
*/
/*
<문제 정보>
 1. 괄호를 제거해서 나올 수 있는 서로다른 식을 사전순서대로 출력

<변수 범위>
 1. 1초 / 128MB
 2. 수식 - 숫자, + * - / ( )
 3. 수식 길이 최대 200
 4. 괄호쌍 최소 1개 최대 10개

<프로그램 진행>
 1. 주어진 식을 char 배열로 저장
 2. 괄호의 인덱스를 Pair 로 저장
 3. 우선순위 순서에 맞게 배열에 배치하고 백트래킹 진행

<필요 함수>
 1.

 *//*



import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;

public class Q2800_2 {
    static char[] expression;
    static Parenthesis[] parentheses = new Parenthesis[10];
    static int numberOfParenthesis = 0; // numberOfParenthesis-1 이 최대 인덱스
    static HashSet<Integer> removeIdx = new HashSet<>();
    static HashSet<String> results = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        expression = br.readLine().toCharArray();
        getParentheses();
        */
/*for (int i=0;i<numberOfParenthesis;i++) {
            System.out.printf("start : %d, end : %d, depth : %d\n",
                    parentheses[i].startIdx, parentheses[i].endIdx, parentheses[i].depth);
        }
        System.out.println();*//*


        dfs(0);
        for(String result : results) sb.append(result).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int depth) {
        if (depth==numberOfParenthesis) {
            if (removeIdx.isEmpty()) return;
            for (int i=0;i<expression.length;i++) {
                if(removeIdx.contains(i)) continue;
                sb.append(expression[i]);
            }
            results.add(sb.toString());
            sb.setLength(0);
            return;
        }

        // 1. 괄호 포함 -> removeIdx 에 포함시키지 않고 넘김
        dfs(depth+1);

        // 2. 괄호 삭제 -> removeIdx 에 포함시키고 넘김
        removeIdx.add(parentheses[depth].startIdx);
        removeIdx.add(parentheses[depth].endIdx);
        dfs(depth+1);
        removeIdx.remove(parentheses[depth].startIdx);
        removeIdx.remove(parentheses[depth].endIdx);
    }

    public static void getParentheses() {
        Deque<Parenthesis> stack = new ArrayDeque<>();
        int depth = 0;
        Parenthesis completedParenthesis;
        for (int i=0;i<expression.length;i++) {
            if (expression[i]=='(') {
                stack.add(new Parenthesis(i,depth));
                depth++;
            }
            else if (expression[i]==')') {
                completedParenthesis = stack.pollLast();
                completedParenthesis.setEnd(i);
                parentheses[numberOfParenthesis++] = new Parenthesis(completedParenthesis);
                depth--;
            }
        }
        Arrays.sort(parentheses,0,numberOfParenthesis);
    }
}

*/
/*class Parenthesis implements Comparable<Parenthesis> {
    int startIdx, endIdx;
    int depth;

    public Parenthesis(int startIdx, int depth) {
        this.startIdx = startIdx;
        this.depth = depth;
    }

    public Parenthesis(Parenthesis p) {
        this.startIdx = p.startIdx;
        this.endIdx = p.endIdx;
        this.depth = p.depth;
    }

    public void setEnd(int endIdx) {
        this.endIdx = endIdx;
    }

    @Override
    public int compareTo (Parenthesis o) {
        return this.depth==o.depth ? this.startIdx - o.startIdx : this.depth - o.depth;
    }
}*//*












*/
