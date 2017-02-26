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
    private int n;
    int[] finishingtime;
    private void DFS_loop(graph G){
        //refresh the value of isExplored
        isExplored = new HashSet<>();


        for (int i = n;i>0;i--){
            // in the order of the finishing time reversed.
            // should use the index as finishing time, and point to the vertices
            int v =finishingtime[i];
            s = v;
            if (!isExplored.contains(v)){
                DFS(G,v);
            }
        }
    }
    private void DFS_loop_rev(graph G){
        t = 0;
        s = -1;
        // SCC 875714
        n = 875714;//TODO: this should change
        finishingtime = new int[n+1];
        //refresh the value of isExplored
        isExplored = new HashSet<>();
        for (int i = n;i>0;i--){
            if (!isExplored.contains(i)) {
                s = i;
                DFS_rev(G, i);
            }
        }
    }


    Set<Integer> isExplored = new HashSet<>();
    HashMap<Integer,Integer> leader = new HashMap<>();
    private void DFS(graph G,int i){
        //mark i as explored
        isExplored.add(i);
        //set leader(i) to be s
        int val;
        if (leader.containsKey(s)){
            val = (int) leader.get(s) + 1;
        }else{
            val = 1;
        }
        leader.put(s,val);

        // arc(i,j)
        List<Integer> templist = G.getGraph().get(i);
        if (templist != null) {
            for (int j : templist) {
                // if j not yet explored
                if (!isExplored.contains(j)) {
                    DFS(G, j);
                }
            }
        }
    }
    private int findbiggest(HashMap<Integer,Integer> map){
        int biggest = 0;
        int biggestindex = 0;
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

    private void DFS_rev(graph G,int i){
        //mark i as explored
        isExplored.add(i);
        // arc(i,j)
        List<Integer> templist = G.getRevGraph().get(i);

        if (templist != null) {
            for (int j : templist) {
                // if j not yet explored
                if (!isExplored.contains(j)) {
                    DFS_rev(G, j);
                }
            }
        }
        t++;
        finishingtime[t] = i;
    }

    public static void main(String[] args){
        //reverse the graph
        SCC scc = new SCC();
        try {
            graph G = new graph();
            //run DFS-loop on G reversed
            scc.DFS_loop_rev(G);
            //run DFS-loop on G
            //processing	nodes	in	decreasing	order	of	finishing	time
            scc.DFS_loop(G);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        int[] res = scc.returnbiggests();
        System.out.print(Arrays.toString(res));
    }
}
    class graph{

        private HashMap<Integer,List> TableOfLines;
        private HashMap<Integer,List> Tablereversed;

        public graph() throws FileNotFoundException{

            //get the value from the file to listOfLines

            /**
             * SHOULD use array of list instead, much easier
             */
            TableOfLines = new HashMap<>();
            Tablereversed = new HashMap<>();

            //TODO: this should change
            File file = new File("D:/javaer/abc/StanAl/p2/SCC.txt");
            //FileInputStream fis = new FileInputStream(file);
            FileReader fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);
            String temp ="";
            String[] two = new String[2];
            int first = 0;
            int second = 0;

            List<Integer> listofsamevalue = new LinkedList<>();
            List<Integer> listofreversed = new LinkedList<>();
            int tempfirst = 0;
            try {
                while ((temp = bufferedReader.readLine()) != null){
                    two = temp.split(" ");
                    first = Integer.parseInt(two[0]);
                    second = Integer.parseInt(two[1]);
                    /*if (listofsamevalue.isEmpty() || first == tempfirst){
                        listofsamevalue.add(second);
                    }
                    else {
                        TableOfLines.put(first-1,listofsamevalue);
                        listofsamevalue = new LinkedList<>();
                    }
                    tempfirst = first;*/
                    if (TableOfLines.containsKey(first)) {
                        listofsamevalue = TableOfLines.get(first);
                    }else {
                        listofsamevalue = new LinkedList<>();
                    }
                    listofsamevalue.add(second);
                    TableOfLines.put(first, listofsamevalue);

                    if (Tablereversed.containsKey(second)) {
                        listofreversed = Tablereversed.get(second);
                    }else {
                        listofreversed = new LinkedList<>();
                    }
                    listofreversed.add(first);
                    Tablereversed.put(second, listofreversed);

                }
                //TableOfLines.put(first,listofsamevalue);
                fr.close();
                bufferedReader.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        public HashMap<Integer,List> getGraph(){
            return TableOfLines;
        }

        public HashMap<Integer,List> getRevGraph(){
            return Tablereversed;
        }
    }

