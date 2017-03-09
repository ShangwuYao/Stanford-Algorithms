import java.io.*;
import java.util.HashSet;

/**
 * Created by Administrator on 2017/3/7.
 */
public class mwis {
    private int[] Weights;
    private int[] A;// maximum weight value
    int numberOfVertices;
    HashSet<Integer> Ai_1;
    HashSet<Integer> Ai_2;
    public static void main(String[] args){
        try {
            mwis solution = new mwis();
            solution.read();
            solution.calculate();
            System.out.println(solution.getA().toString());
            //1, 2, 3, 4, 17, 117, 517, and 997
            System.out.println(solution.getA().contains(1 - 1));
            System.out.println(solution.getA().contains(2 - 1));
            System.out.println(solution.getA().contains(3 - 1));
            System.out.println(solution.getA().contains(4 - 1));
            System.out.println(solution.getA().contains(17 - 1));
            System.out.println(solution.getA().contains(117 - 1));
            System.out.println(solution.getA().contains(517 - 1));
            System.out.println(solution.getA().contains(997 - 1));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public HashSet<Integer> getA(){
        return Ai_1;
    }
    public void calculate(){
        A[0] = 0;
        A[1] = Weights[0];
        Ai_1.add(0);

        for (int i = 2; i < numberOfVertices + 1; i++){
            /** notice */
            if (A[i - 1] > A[i - 2] + Weights[i - 1]){
                A[i] = A[i-1];
                Ai_2 = new HashSet<>(Ai_1);
            }else {
                A[i] = A[i - 2] + Weights[i - 1];
                Ai_2.add(i-1);
                HashSet<Integer> temp = new HashSet<>(Ai_1);
                Ai_1 = new HashSet<>(Ai_2);
                Ai_2 = new HashSet<>(temp);
            }
        }
    }
    public void read() throws FileNotFoundException, IOException{
        File file = new File("D:/javaer/abc/StanAl/p3/mwis.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String str = br.readLine();
        numberOfVertices = Integer.parseInt(str);
        Weights = new int[numberOfVertices];
        A = new int[numberOfVertices + 1];
        Ai_1 = new HashSet<>();
        Ai_2 = new HashSet<>();
        int index = 0;
        while ((str = br.readLine()) != null){
            Weights[index] = Integer.parseInt(str);
            index++;
        }
    }
}
