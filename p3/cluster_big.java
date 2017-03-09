import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Administrator on 2017/3/5.
 */
public class cluster_big {
    HashMap<String,Integer> nodesMap;
    int numberOfNodes;
    int numberOfBits;

    cluster_big(){
        read();
        cluster cluster = new cluster(nodesMap.size());//TODO: should avoid duplicates

        for (String sourceStr : nodesMap.keySet()){
            List<String> closes = getClose(sourceStr);

            for (int i = 0; i < closes.size(); i++){
                String compareStr = closes.get(i);
                if (nodesMap.containsKey(compareStr)){
                    cluster.union(nodesMap.get(sourceStr),nodesMap.get(compareStr));
                }
            }
        }
        System.out.println(cluster.getNumber());

    }

    private void read(){
        try {
            File file = new File("D:/javaer/abc/StanAl/p3/clustering_big.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String str = br.readLine();
            numberOfNodes = Integer.parseInt( str.split(" ")[0]);
            numberOfBits = Integer.parseInt(str.split(" ")[1]);

            nodesMap = new HashMap<>();

            int value = 0;
            while ((str = br.readLine()) != null){
                nodesMap.put(str.trim(),value++);
            }
            //TODO: doing this again will eliminate the error caused by duplicates in the hashtable
            //since there would be gaps in them
            value = 0;
            for (String key : nodesMap.keySet()){
                nodesMap.put(key,value++);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    private int[] getIntArrayFromString(String stringForm){
        String[] strings = stringForm.split(" ");
        int[] ints = new int[numberOfBits];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }

    private String getStringFromIntArray(int[] ints){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ints.length; i++){
            sb.append(ints[i]);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private List getClose(String source){
        int[] sourceInts = getIntArrayFromString(source);

        //String[] results = new String[numberOfNodes + numberOfNodes * (numberOfNodes - 1)/2];
        List<String> results = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < sourceInts.length; i++){
            for (int j = i; j< sourceInts.length; j++){
                int[] newsourceInts = sourceInts.clone(); // rather than copying the reference
                if (i != j) {
                    newsourceInts[i] = (sourceInts[i] + 1) % 2;
                    newsourceInts[j] = (sourceInts[j] + 1) % 2;
                }
                else {
                    newsourceInts[i] = (sourceInts[i] + 1) % 2;
                }
                if (nodesMap.containsKey(getStringFromIntArray(newsourceInts))){
                    results.add(getStringFromIntArray(newsourceInts));}
            }
        }
        return results;
    }

    public static void main(String[] args){
        cluster_big cb = new cluster_big();
        //7330
    }
}
