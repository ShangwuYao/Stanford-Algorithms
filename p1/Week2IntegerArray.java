/**
 * Created by Administrator on 2017/1/27.
 */
import edu.princeton.cs.algs4.Count;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.lang.Object;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
public class Week2IntegerArray {
    public static void main(String[] args) {
        //int[] A = {5,3,2,4,7,9,10,1,6,11,8};

        try {

            Scanner s = new Scanner(new File("D:\\courses\\stanfordalgorithm\\week2\\IntegerArray.txt"));
            int[] A = new int[100000];
            for (int i = 0; i < A.length; i++)
                A[i] = s.nextInt();
            System.out.println("All read.");

            Entry<int[], Long> pair = SortAndCount(A, A.length);
            System.out.println(Arrays.toString(pair.getKey()));
            System.out.println(pair.getValue());
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    private static Entry<int[],Long> SortAndCount(int[] A,int n){
        if (n==1){
            Entry<int[],Long> pair = new SimpleEntry<int[], Long>(A,0l);
            return pair;
        }
        else {
            int num1 = n/2;
            int num2 = n-(n/2);
            int[] half1 = new int[num1];
            int[] half2 = new int[num2];
            System.arraycopy(A,0,half1,0,num1);
            System.arraycopy(A,num1,half2,0,num2);

            Entry<int[],Long> pair1= SortAndCount(half1,num1);
            Entry<int[],Long> pair2 = SortAndCount(half2,num2);
            Entry<int[],Long> pair3 = CountSplitInv(pair1.getKey(),pair2.getKey(),n);
            int[] key = pair3.getKey();
            long value = pair1.getValue().longValue()+pair2.getValue().longValue()+pair3.getValue().longValue();
            return new SimpleEntry<int[], Long>(key,value);
        }
    }
    private static Entry<int[],Long> CountSplitInv(int[] half1,int[] half2,int n){
        int i = 0;
        int j = 0;

        int num1 = half1.length;
        int num2 = half2.length;


        int[] newtotal = new int[n];
        long count = 0;
        for (int k = 0;k<n;k++){
            if (i == num1){
                newtotal[k] = half2[j];
                j++;
            }
            else if (j == num2){
                newtotal[k] = half1[i];
                i++;
            }
            else if (half1[i] > half2[j]){
                newtotal[k] = half2[j];
                j++;
                count += num1-i;
            }
            else {
                newtotal[k] = half1[i];
                i++;
            }
        }
        return new SimpleEntry<int[], Long>(newtotal,count);
    }
}
