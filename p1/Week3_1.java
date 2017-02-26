import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
/**
 * Created by Administrator on 2017/1/31.
 */
public class Week3_1 {
    public static int CountComparision = 0;
    public static void main(String[] args) {
        try {
            //int[] A = {5,3,2,4,9,6,7,1,8};

            Scanner s = new Scanner(new File("D:\\courses\\stanfordalgorithm\\week3\\QuickSort.txt"));
            int[] A = new int[10000];
            for (int i = 0; i < A.length; i++) {
                A[i] = s.nextInt();
            }

            System.out.println("All read.");
            //sort(A, 0, A.length - 1);
            System.out.println(Arrays.toString(A));
            System.out.println(CountComparision);
            System.out.println(0x69&0x55);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
    private static void sort(int[] A, int left, int right){
        if (right <= left) return;
        int p = partition(A,left,right); // get where the pivot is now.
        sort(A,left,p-1);
        sort(A,p+1,right);
    }
    private static int choosepivot(int[] A,int left, int right){
        /**
         * this can be easily modified.
         */
        //return left;  //162085 (correct)
        //return right;  //164123 (correct)
        //return median of three 138382 (correct)
        return median(A,left,right);
    }
    private static int median(int[] A,int left,int right){
        int[] B = new int[3];
        B[0] = A[left];
        B[1] = A[left + (right-left) / 2];
        B[2] = A[right];
        Arrays.sort(B);
        if (A[left]==B[1]) {return left;}
        else if (A[left+(right-left)/2]==B[1]){return left+(right-left)/2;}
        else return right;
    }
    private static int partition(int[] A,int left,int right){
        int p =choosepivot(A,left,right);
        //exchange pivot with the first before partition.
        int k = A[p]; A[p] = A[left]; A[left] = k;

        int i =left+1;
        for (int j = left+1;j<=right;j++){
            if (A[j]<k) {int q = A[i]; A[i]=A[j]; A[j]=q; i++;}//swap A[i],A[j]
        }
        CountComparision += (right - left);
        int q = A[left]; A[left]=A[i-1]; A[i-1]=q;//swap A[left],A[i-1]
        return i-1;

        /**
        //pivot : right 160361
        int i =left;
        for (int j = left;j<right;j++){
            if (A[j]<p) {int q = A[i]; A[i]=A[j]; A[j]=q; i++;}//swap A[i],A[j]
        }
        CountComparision += (right - left);
        int q = A[right]; A[right]=A[i]; A[i]=q;//swap A[left],A[i-1]
        return i;
        */
    }
}
