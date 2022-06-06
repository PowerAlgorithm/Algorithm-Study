// 2179번 비슷한 단어 (G5) [정렬] [분할정복(풀이 아직)]
/*
<문제 정보>
 1. N개의 영단어들이 주어졌을 때 가장 비슷한 두 단어를 출력
    - 비슷한 정도 : 두단어의 공통 접두사의 길이
    - 여러개 있다면 가장 앞에 있는 단어 출력

<변수 범위>
 1. 2초 / 128MB
 2. 2<=N<=20,000
 3. 알파벳 소문자로만 이루어진 길이 100자 이하의 서로다른 영단어

<프로그램 진행>
 1.

<필요 함수>
 1.

 */


import java.io.*;
import java.util.Arrays;

public class Q2179 {
    static int N;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        Word[] words = new Word[N];
        for (int i=0;i<N;i++) words[i] = new Word(i, br.readLine());
        Arrays.sort(words);
        for (Word word : words) System.out.printf("%s - %d\n",word.word, word.number);

        int idxS=20000;
        int idx=0;
        int maxCommonLength = 0;
        int commonLength;
        for (int i=0;i<N-1;i++) {
            // 다음단어와 똑같다면 넘김
            if (words[i].word.equals(words[i+1].word)) continue;

            commonLength = getCommonLength(words[i].word, words[i+1].word);
            if (commonLength>maxCommonLength) {
                maxCommonLength = commonLength;
//                idxS = Math.min(words[i].number, words[i+1].number);
                if (words[i].number<words[i+1].number) {
                    idxS = words[i].number;
                    idx = i;
                }
                else {
                    idxS = words[i+1].number;
                    idx = i+1;
                }
            }

            else if (commonLength==maxCommonLength) {
//                idxS = Math.min(idxS, Math.min(words[i].number, words[i+1].number));
                if (idxS > Math.min(words[i].number, words[i+1].number)) {
                    if (words[i].number<words[i+1].number) {
                        idxS = words[i].number;
                        idx = i;
                    }
                    else {
                        idxS = words[i+1].number;
                        idx = i+1;
                    }
                }
            }
        }
//        System.out.printf("maxCommonLength : %d, idxS : %d\n",maxCommonLength,idxS);


        int idxT = 20000;
        int idx2=0;
        int move = 1;
        boolean left = true;
        boolean right = true;
        while (left || right) {
            if (idx-move<0) left = false;
            if (idx+move>=N) right = false;

            if (left) {
                if (getCommonLength(words[idx].word,words[idx-move].word)!=maxCommonLength) left = false;
//                else idxT = Math.min(idxT, words[idx-move].number);
                else if (idxT > words[idx-move].number) {
                    idxT = words[idx-move].number;
                    idx2 = idx-move;
                }
            }
            if (right) {
                if (getCommonLength(words[idx].word,words[idx+move].word)!=maxCommonLength) right = false;
//                else idxT = Math.min(idxT, words[idx+move].number);
                else if (idxT > words[idx+move].number) {
                    idxT = words[idx+move].number;
                    idx2 = idx+move;
                }
            }
            move++;
        }
//        System.out.printf("idxS : %d, idxT : %d, idx : %d\n",idxS, idxT, idx);
        sb.append(words[idx].word).append("\n").append(words[idx2].word);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getCommonLength (String s1, String s2) {
        int length = Math.min(s1.length(), s2.length());
        int commonLength = 0;
        for (int i=0;i<length;i++) {
            if(s1.charAt(i) != s2.charAt(i)) break;
            commonLength++;
        }
        return commonLength;
    }
}

class Word implements Comparable<Word>{
    int number;
    String word;

    public Word(int number, String word) {
        this.number = number;
        this.word = word;
    }

    @Override
    public int compareTo(Word o) {
        return this.word.compareTo(o.word);
    }
}
