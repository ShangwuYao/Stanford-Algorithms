import edu.princeton.cs.algs4.Heap;

import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017/2/27.
 */
public class dijkstra {
    public static void main(String[] args){
        dijkstra solution = new dijkstra();
        try {
            solution.readData();
        }catch  (Exception ex){
            ex.printStackTrace();
        }
        solution.naiveImplementation();
        //System.out.println(Arrays.toString(solution.A));
        int[] ints = {7,37,59,82,99,115,133,165,188,197};
        for (int i = 0;i<ints.length;i++){
            System.out.print(","+solution.A[ints[i] - 1]);
        }
    }
    //public variant
    private int n = 200;//TODO : THIS SHOULD CHANGE
    TreeMap<Integer,Integer>[] lengthmatrix;
    private int[] A;//result

    private void naiveImplementation(){
        HashSet<Integer> X = new HashSet<>();
        A = new int[n];
        //default value for unreachable.
        for (int i = 0; i <n ; i++){
            A[i] = 1000000;
        }
        X.add(0);
        A[0] = 0;
        while (X.size() < n){
            int minend= -1;
            int minstart = -1;
            int minimum = Integer.MAX_VALUE;
            // v stands for the vertices we have so far
            for (int v : X){
                // get all edges, find the minimum
                for (Map.Entry<Integer,Integer> entry : lengthmatrix[v].entrySet()){
                    if (!X.contains(entry.getKey()) && entry.getValue() + A[v] < minimum){
                        minimum = entry.getValue() + A[v];
                        minstart = v;
                        minend = entry.getKey();
                    }
                }
            }
            if (minstart == -1) {
                // none solution
                break;
            }else {
                X.add(minend);
                A[minend] = minimum;
            }
        }
    }
    private void readData() throws FileNotFoundException, IOException{
        File file = new File("D:/javaer/abc/StanAl/p2/dijkstraData.txt");
        FileReader fr = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fr);

        //store in the list as i-1
        String str;
        int first;
        int second;
        //instantiate
        lengthmatrix = new TreeMap[n];
        //initialize
        for (int i = 0; i < n; i++){
            lengthmatrix[i] = new TreeMap<>();
        }

        while ((str = bufferedReader.readLine())!= null){

            String[] strings = str.split("\t");
            String[] twostring = new String[2];
            int index = Integer.parseInt(strings[0]) - 1;
            for (int i = 1; i <strings.length;i++) {
                twostring = strings[i].split(",");
                first = Integer.parseInt(twostring[0]) - 1;
                second = Integer.parseInt(twostring[1]);
                lengthmatrix[index].put(first,second);
            }
        }
    }
}
