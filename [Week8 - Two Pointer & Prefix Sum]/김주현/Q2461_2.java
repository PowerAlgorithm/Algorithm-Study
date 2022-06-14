// 2461번 대표 선수 (G2) [투포인터]
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
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2461_2 {
    static int N, M;
    static Ability[] abilities;
    static int[] numOfStudents;
    static int selectionCompleteClasses = 0;
    static int minDiff = 1_000_000_001;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        abilities = new Ability[N*M];
        numOfStudents = new int[N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++) abilities[M*i+j] = new Ability(i,Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(abilities);
        getMinDiff();
        sb.append(minDiff);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void getMinDiff() {
        int left = 0;
        int right = 0;
        numOfStudents[abilities[right].classNum]++;
        selectionCompleteClasses++;

        while (true) {
            if (selectionCompleteClasses<N) {
                if (right==N*M-1) break;

                numOfStudents[abilities[++right].classNum]++;
                if(numOfStudents[abilities[right].classNum]==1) selectionCompleteClasses++;
            }
            else { // -> selectionCompleteClasses==N 일 경우
                minDiff = Math.min(minDiff, abilities[right].ability-abilities[left].ability);

                // 가장 왼쪽의 대표를 뺀다. 만약 뺐는데 해당 반의 대표수가 0이 되었다면 대표선정반의 수를 하나 줄인다.
                // 왼쪽포인터를 오른쪽으로 한번 이동한다.
                if(--numOfStudents[abilities[left++].classNum]==0) selectionCompleteClasses--;
            }
        }
    }
}

class Ability implements Comparable<Ability>{
    int classNum;
    int ability;

    public Ability(int classNum, int ability) {
        this.classNum = classNum;
        this.ability = ability;
    }

    @Override
    public int compareTo(Ability o) {
        return this.ability == o.ability ? this.classNum-o.classNum : this.ability-o.ability;
    }
}
















