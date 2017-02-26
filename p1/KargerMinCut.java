import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.In;
import javafx.beans.property.ReadOnlyIntegerWrapper;

/**
 * Created by Administrator on 2017/2/8.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class KargerMinCut {
        Map<Integer,List> adjList;
        Random r = new Random();
        List<Integer> vList;
        int size;
        int mincut = Integer.MAX_VALUE;
        public KargerMinCut(){
            init();//TODO: this is because we can repeatedly call the init() function.
        }
        void init(){
            //first read the input
            adjList = new HashMap<Integer,List>();//TODO: notice
            vList = new ArrayList<>();//TODO: notice
            try {
                //TODO: notice
                Scanner scanner = new Scanner(new InputStreamReader(
                        this.getClass().getResourceAsStream("KargerMinCut.txt")));
                while (scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    String[] num = line.split("\\t");//TODO: notice
                    List<Integer> adj = new ArrayList<>();
                    for (int i = 1; i<num.length; i++){
                        adj.add(Integer.parseInt(num[i]));
                    }
                    adjList.put(Integer.parseInt(num[0]),adj);
                    vList.add(Integer.parseInt(num[0]));
                }
                size = vList.size();
            }
            catch (Exception ex){
                ex.printStackTrace();//TODO: notice: detailed error message for debug
            }
        }
        private void calculate(){
            while (vList.size() > 2) {
                //find the start point
                //TODO: remember the r.nextInt() method.
                //TODO: notice : keep one list to store the index for reference
                int vIndex = vList.get(r.nextInt(vList.size()));
                List<Integer> v = adjList.get(vIndex);
                //find the end point
                int dIndex = v.get(r.nextInt(v.size()));
                List<Integer> d = adjList.get(dIndex);
                //find all connected to d, remove d and add v
                //TODO: remember to use iterator like this.
                for (Iterator<Integer> iterator = d.iterator();iterator.hasNext();){
                    int auxIndex = iterator.next();
                    List<Integer> aux = adjList.get(auxIndex);
                    //remove all the end points from aux
                    aux.remove(new Integer(dIndex));
                    //TODO: adding a new Integer means
                    //it would remove the first occurrence of the specified element.

                    //add start point to aux
                    aux.add(vIndex);
                    // connect v to aux
                    v.add(auxIndex);
                }
                //remove d from adjList and vList
                vList.remove(new Integer(dIndex));
                adjList.remove(dIndex);
                //remove self loops
                while(v.remove(new Integer(vIndex)));
                //TODO: adding a new Integer will make it return a boolean
            }
            mincut = Math.min(mincut,adjList.get(vList.get(0)).size());
            //TODO: notice the difference between List and Map
            //List.get() could use both the index and the key as reference
            //while Map.get() could only use the key as reference.
        }
    public static void main(String[] args){
        //TODO: notice that this has to be instantiated, even though they are in the same
        // class.
        KargerMinCut mincut = new KargerMinCut();
        int mincutvalue = Integer.MAX_VALUE;
        for (int i = 0; i<mincut.size*mincut.size; i++){
            mincut.init();
            mincut.calculate();
            mincutvalue = Math.min(mincutvalue, mincut.mincut);
            System.out.println("Iteration " + i + " " + mincut.mincut);
        }
        System.out.println(mincutvalue);
    }
}
