import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Administrator on 2017/2/28.
 */
public class Algo_2sum {
    /**implementing the 2 sum algorithm, with distinct solution
     * first don't care about the distinct
     * distinct means no duplications or only one pair of them?????
     */
    public static void main(String[] args){
        int n = 1000000;//TODO: THIS SHOULD CHANGE
        Algo_2sum solution = new Algo_2sum(n);
        try {
            solution.read();
            int count = 0;
            int tempcount;
            for (int t = -10000; t<=10000;t++) {
                //int t = 3;//TODO: this is used for debugging
                //HashSet<Long> visited = new HashSet<>(1);
                for (int j = 0; j < n; j++) {
                    /** I didn't quite get what does distinct mean at first...
                     * it means if there is at least one pair of solution and you are good to go
                     */
                    if (solution.map.containsKey(t-solution.values[j])
                            && (t != solution.values[j]*2 || !(solution.map.get(solution.values[j])==1))) {
                        // delete && (!(solution.map.get(solution.values[j]) > 1) && !(solution.map.get(t-solution.values[j]) > 1))
                        //not do, when #i > 1 or #j > 1
                        /**rather than not counting those with duplicates, should count only once.*/
                        count++;
                        break;
                        //visited.add(solution.values[j]);
                        //visited.add(t - solution.values[j]);
                    }
                }
            }
            System.out.println(count);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    HashMap<Long,Long> map;
    long[] values ;
    Algo_2sum(int n){
        map = new HashMap<>();
        values = new long[n];
    }

    private void read() throws FileNotFoundException, IOException{
        File file = new File("D:/javaer/abc/StanAl/p2/2sum.txt");
        FileReader fr = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fr);

        String str;
        int i =0;
        long count;
        while ((str = bufferedReader.readLine()) != null){
            long val = Long.parseLong(str);
            count = map.containsKey(val)? map.get(val)+1 : 1;
            map.put(val, count);
            values[i] = val;
            i++;
        }
    }
}
