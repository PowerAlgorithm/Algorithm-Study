// 17413번 단어 뒤집기-2 (S3)
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

public class Q17413 {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringBuilder word = new StringBuilder();
        char[] str = br.readLine().toCharArray();
        boolean isTag = false;
        for (char c : str) {
            if (c == '<' || isTag || c == ' ') {
                if (word.length()>0) {
                    word.reverse();
                    sb.append(word);
                    word.setLength(0);
                }
                if (c != ' ') isTag = c != '>';
                sb.append(c);
                continue;
            }
            // 여기서부터는 tag도 아니고 공백도 아님 -> 단어
            isTag = false;
            word.append(c);
        }
        if (word.length()>0) {
            word.reverse();
            sb.append(word);
            word.setLength(0);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
