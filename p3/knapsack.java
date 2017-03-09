import java.io.*;
import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/7.
 */
public class knapsack {
    int[] values;
    int[] weights;
    int sizeOfknapsack;
    int numberOfItem;
    public static void main(String[] args){
        try {
            knapsack solution = new knapsack();
            solution.read();
            int A = solution.calculate();
            System.out.println(A); //2493893
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public int calculate(){
        int[][] A = new int[numberOfItem][sizeOfknapsack + 1];
        for (int i = 0; i < numberOfItem; i++){
            for (int x = 0; x < sizeOfknapsack + 1; x++){
                /** very interesting solution, I did it!
                 * instead of complicated nested situations
                 * treat the two values individually
                 * */
                int left = i - 1 >= 0? A[i - 1][x] : 0;
                int right = (i - 1 >= 0 && (x - weights[i]) >= 0)?
                        A[i-1][x-weights[i]] + values[i] : 0;
                A[i][x] = Math.max(left, right);
            }
        }
        return A[numberOfItem - 1][sizeOfknapsack];
    }
    public void read() throws FileNotFoundException, IOException{
        File file = new File("D:/javaer/abc/StanAl/p3/knapsack1.txt");
        FileReader fr  = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String str = br.readLine();
        sizeOfknapsack = Integer.parseInt(str.split(" ")[0]);
        numberOfItem = Integer.parseInt(str.split(" ")[1]);

        int val1, val2;
        values = new int[numberOfItem];
        weights = new int[numberOfItem];
        int index = 0;
        while((str = br.readLine()) != null){
            val1 = Integer.parseInt(str.split(" ")[0]);
            val2 = Integer.parseInt(str.split(" ")[1]);

            values[index] = val1;
            weights[index] = val2;
            index++;
        }
    }
}
