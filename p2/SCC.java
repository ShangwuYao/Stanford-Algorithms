import edu.princeton.cs.algs4.MaxPQ;

import java.io.*;
import java.util.*;
/**
 * Created by Administrator on 2017/2/25.
 */
public class SCC {
    /**
     * the goal is to find the five biggest SCC, and print them like 5,4,3,2,1
     */
    //implement kosaraju's algorithm
    private int t;
    private int s;
    private int n = 875714;//TODO: this should change  875714
    int[] finishingtime;
    /**
     * change isExplored from set to boolean[], easier
     */
    boolean[] isExplored;
    HashMap<Integer,Integer> leader = new HashMap<>();

    private void DFS_loop(ArrayList<Integer>[] G){

        boolean[] terminated = new boolean[n+1];
        //refresh the value of isExplored
        isExplored = new boolean[n+1];
        for (int i =0;i<n+1;i++){
            isExplored[i]=false;
            terminated[i] = false;
        }

        /*for (int i = n;i>0;i--){
            // in the order of the finishing time reversed.
            // should use the index as finishing time, and point to the vertices
            int v =finishingtime[i];
            s = v;
            if (!isExplored[v]){
                DFS(G,v);
            }
        }*/

        /** try and rewrite recursion with a stack*/
        Stack<Integer> stack = new Stack();

        for (int i = n;i>0;i--){
            int v = finishingtime[i];
            s = v;
            /** SHOULD use peek() and if(!isEmpty()), then last one will still be processed*/
            if (!isExplored[v]) {
                boolean isinital = true;
                int current = v;
                while (!stack.isEmpty() || isinital) {
                    isinital = false;
                    if (!isExplored[current]) {
                        isExplored[current] = true;
                        stack.push(current);

                        int val;
                        if (leader.containsKey(s)){
                            val = (int) leader.get(s) + 1;
                        }else{
                            val = 1;
                        }
                        leader.put(s,val);

                        //if it is not explored
                        for (Integer j : G[current]) {
                            // if j not yet explored
                            if (!isExplored[j]) {
                                stack.push(j);
                            }// if explored, current becomes this one, so don't push.
                        }
                    } else if (!terminated[current]) {
                        // end
                        terminated[current] = true;
                    }//if terminated, do nothing.
                    // iterate
                    current = stack.pop();
                }
            }
        }
    }
    private void DFS_loop_rev(ArrayList<Integer>[] G){
        t = 0;
        s = -1;
        // SCC 875714
        finishingtime = new int[n+1];
        boolean[] terminated = new boolean[n+1];
        //refresh the value of isExplored
        isExplored = new boolean[n+1];
        for (int i =0;i<n+1;i++){
            isExplored[i]=false;
            terminated[i] = false;
        }
        /*
        for (int i = n;i>0;i--){
            if (!isExplored[i]) {
                s = i;
                DFS_rev(G, i);
            }
        }
        */

        /** try and rewrite recursion with a stack*/
        Stack<Integer> stack = new Stack();

        for (int i = n;i>0;i--){
            if (!isExplored[i]) {
                int current = i;
                while (true) {
                    if (!isExplored[current]) {
                        isExplored[current] = true;
                        stack.push(current);
                        //if it is not explored
                        for (Integer j : G[current]) {
                            // if j not yet explored
                            if (!isExplored[j]) {
                                stack.push(j);
                            }// if explored, current becomes this one, so don't push.
                        }
                    } else if (!terminated[current]) {
                        // end
                        t++;
                        finishingtime[t] = current;
                        terminated[current] = true;
                    }//if terminated, do nothing.
                    // iterate
                    if (stack.isEmpty()) break;
                    current = stack.pop();
                }
            }
        }
    }
    /**method with recursion, will cause a StackOverflowError when the data is too big.*/
    private void DFS(ArrayList<Integer>[] G,int i){
        //mark i as explored
        isExplored[i] = true;
        //set leader(i) to be s
        int val;
        if (leader.containsKey(s)){
            val = (int) leader.get(s) + 1;
        }else{
            val = 1;
        }
        leader.put(s,val);

         //arc(i,j)
            for (Integer j : G[i]) {
                // if j not yet explored
                if (!isExplored[j]) {
                    DFS(G, j);
                }
            }
    }
    private void DFS_rev(ArrayList<Integer>[] G,int i){
        //mark i as explored
        isExplored[i] = true;
        // arc(i,j)
        /**stackoverflow error
         * change G from hashmap of list to array of list.
         */

        for (Integer j : G[i]) {
            // if j not yet explored
            if (!isExplored[j]) {
                DFS_rev(G, j);
            }
        }
        t++;
        finishingtime[t] = i;
    }

    private int findbiggest(HashMap<Integer,Integer> map){
        int biggest = 0;
        int biggestindex = 0;
        /**the time complexity is actually nlogn, don't do this, sort and count instead.*/
        for (int i = 1; i<n+1;i++){
            if (map.containsKey(i) && biggest < map.get(i)){
                biggest =map.get(i);
                biggestindex = i;
            }
        }
        map.remove(biggestindex);
        return biggest;
    }
    private int[] returnbiggests(){
        int[] res = new int[5];
        for (int i = 0;i<5;i++){
            res[i] = findbiggest(leader);
        }
        return res;
    }



    public static void main(String[] args){
        //reverse the graph
        SCC scc = new SCC();
        try {
            scc.graph();
            //run DFS-loop on G reversed
            scc.DFS_loop_rev(scc.arrayreversed);
            //run DFS-loop on G
            //processing	nodes	in	decreasing	order	of	finishing	time
            scc.DFS_loop(scc.arrayOfLines);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        int[] res = scc.returnbiggests();
        System.out.print(Arrays.toString(res));
    }

        //instantiate
        private ArrayList<Integer>[] arrayOfLines = new ArrayList[n+1];
        private ArrayList<Integer>[] arrayreversed= new ArrayList[n+1];
        private void graph() throws FileNotFoundException{
            /**
             * SHOULD use array of list instead, much easier
             */

            //initialization
            for (int i = 0; i<n+1;i++) {
                arrayOfLines[i] = new ArrayList<>(1);
                arrayreversed[i] = new ArrayList<>(1);
            }

            //TODO: this should change
            File file = new File("D:/javaer/abc/StanAl/p2/SCC.txt");
            FileReader fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);
            String str;
            StringTokenizer st;

            try {
                while ((str = bufferedReader.readLine()) != null){
                    /**
                     * memorize this.
                     */
                    st = new StringTokenizer(str);
                    Integer first = Integer.valueOf(st.nextToken());
                    Integer second = Integer.valueOf(st.nextToken());

                    arrayOfLines[first].add(second);
                    arrayreversed[second].add(first);

                }
                fr.close();
                bufferedReader.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
}


