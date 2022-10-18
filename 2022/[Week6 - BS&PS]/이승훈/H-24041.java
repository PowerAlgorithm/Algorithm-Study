import java.util.*;
import java.io.*;

/***
 * 키워드
 *
 * 조건
 *
 * 풀이
 *
 */

class Ingredient {
    int s;
    int l;
    int o;
    public Ingredient(int s, int l, int o) {
        this.s = s;
        this.l = l;
        this.o = o;
    }
}
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Ingredient[] mealkit = new Ingredient[n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            mealkit[i] = new Ingredient(s, l, o);
        }
        long min = 1;
        long max = 2000000001;

        while(min <= max) {
            long day = (min + max) / 2;

            long totalNumOfGerm = 0;
            ArrayList<Ingredient> canDump = new ArrayList<>();
            for (Ingredient ingredient : mealkit) {
                long numOfGerm = ingredient.s * Math.max(1, day - ingredient.l);
                if(ingredient.o == 1) {
                    canDump.add(ingredient);
                } else {
                    totalNumOfGerm += numOfGerm;
                }
            }
            canDump.sort(Comparator.comparing(
                    ingredient -> ingredient.s * Math.max(1, day - ingredient.l)));
            for(int i=0; i<canDump.size() - k; i++) {
                Ingredient ingredient = canDump.get(i);
                long numOfGerm = ingredient.s * Math.max(1, day - ingredient.l);
                totalNumOfGerm += numOfGerm;
            }

            if(totalNumOfGerm > g) {
                max = day - 1;
            } else {
                min = day + 1;
            }
        }
        System.out.println(max);

        br.close();
    }
}