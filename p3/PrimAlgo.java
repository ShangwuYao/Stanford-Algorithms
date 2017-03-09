import edu.princeton.cs.algs4.ST;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Administrator on 2017/3/2.
 */
public class PrimAlgo {
    /**naive implementation of Prim's MST algorithm*/
    public static void main(String[] args){
        try {
            File file = new File("D:/javaer/abc/StanAl/p3/edges.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String[] two =  br.readLine().split(" ");
            int nodes = Integer.parseInt(two[0]);
            int edges = Integer.parseInt(two[1]);

            List<Integer> X = new ArrayList<>();
            HashMap<Integer,HashMap<Integer,Integer>> allEdges = new HashMap();


            String str;
            String[] three;
            int first,second,third;
            while ((str = br.readLine()) != null){
                three = str.split(" ");
                first = Integer.parseInt(three[0]);
                second = Integer.parseInt(three[1]);
                third = Integer.parseInt(three[2]);
                if (!allEdges.containsKey(first)) {
                    HashMap tempmap = new HashMap<>();
                    tempmap.put(second,third);
                    allEdges.put(first,tempmap);
                }
                else {
                    allEdges.get(first).put(second,third);
                }
                if (!allEdges.containsKey(second)){
                    HashMap tempmap = new HashMap();
                    tempmap.put(first,third);
                    allEdges.put(second,tempmap);
                }else{
                    allEdges.get(second).put(first,third);
                }
            }

            /**brute force implementation*/
            int v;
            long sum = 0;
            int mincost;
            //while X not U
            // X should start with an number inside
            X.add(1);

            while (X.size() < nodes){
                mincost = Integer.MAX_VALUE;
                v = 0;
                for (int j : X){
                    //j is start node u, entry.getKey() is the end node v
                    for (Map.Entry<Integer,Integer> entry : allEdges.get(j).entrySet()){
                        // v not in X
                        if (!X.contains(entry.getKey()) && entry.getValue() < mincost){
                            mincost = entry.getValue();
                            v = entry.getKey();

                        }
                    }
                }
                //update X
                X.add(v);
                sum += mincost;
            }
            System.out.println(sum);
            //-3612829   correct

            /**heap implementation*/
            /**priorityqueue with a reversed comparator is a heap*/
            //TODO: don't know how to use mapping
            /*PrimAlgo solution = new PrimAlgo();
            PriorityQueue pq = new PriorityQueue();
            //initialize
            for (int i = 1;i<nodes+1;i++){
                pq.add(i);
            }

            int newadded;
            int sum = 0;
            while (X.size() < nodes){
                //When v added to X:   newadded is v
                //-for each edge (v,w) in E:

                //need a mapping from key to vertex
                //newadded = solution.Extract_min();
                //X.add(newadded);
                //sum +=

                for (int i : X){
                    // i is w
                    //-if w in V – X
                    if (!X.contains(i)){
                        //        -delete w from heap


                    }
                }
            }



            //        -recompute key[w] = min{key[w], Costvw
            //        -re-insert w into heap
*/
            }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
