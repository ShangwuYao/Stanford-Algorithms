/**
 * Created by Administrator on 2017/3/5.
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Algorithms: Design and Analysis, Part 2
 * Programming Question - Week 2
 * @author Felix Garcia Lainez
 */
public class others
{
    private static int numberOfBits;
    private static int numberOfNodes;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String[] nodesArray = readNodesArrayFromFile();

        //Count Unique Entries
        Map<String, Integer> auxNodesMap = new HashMap<String, Integer>();
        for (int i = 0; i < nodesArray.length; i++) {
            auxNodesMap.put(nodesArray[i], i);
        }

        numberOfNodes = auxNodesMap.size();

        //Load Nodes in HashMap
        int value = 0;
        Map<String, Integer> nodesMap = new HashMap<String, Integer>();
        for(Iterator<String> it = auxNodesMap.keySet().iterator(); it.hasNext();)
        {
            String key = it.next();
            nodesMap.put(key, value);
            value++;
        }

        cluster unionFind = new cluster(numberOfNodes);

        for(String key : nodesMap.keySet())
        {
            String[] closeNodes = getCloseNodes(key);

            for(int j = 0; j < closeNodes.length; j++)
            {
                String k = closeNodes[j];

                if(nodesMap.containsKey(k)){
                    unionFind.union(nodesMap.get(key), nodesMap.get(k));
                }
            }
        }

        System.out.println("*** Number of Clusters => " + unionFind.getNumber());
    }

    /**
     * Return nodes whose distance is less than 3 (differ by only one or two bits)
     */
    private static String[] getCloseNodes(String node)
    {
        int[] nodeBinary = getBinaryIntArrayFromString(node);
        String[] output = new String[numberOfBits + (numberOfBits * (numberOfBits - 1)) / 2];

        // Make 1 and 2 bit different
        int count = 0;

        for(int i = 0; i < numberOfBits; i++)
        {
            for(int j = i; j < numberOfBits; j++)
            {
                int[] newNodeBinary = nodeBinary.clone();

                if(i != j)
                {
                    newNodeBinary[i] = (nodeBinary[i] + 1) % 2;
                    newNodeBinary[j] = (nodeBinary[j] + 1) % 2;
                }
                else{
                    newNodeBinary[i] = (nodeBinary[i] + 1) % 2;
                }

                //Convert to String and add to output
                output[count] = getStringFromBinaryIntArray(newNodeBinary);
                count++;
            }
        }

        return output;
    }

    /**
     * Get an array of binary integers from a String (delete whitespaces)
     */
    public static int[] getBinaryIntArrayFromString(String node)
    {
        int[] nodeBinary = new int[numberOfBits];
        int count = 0;

        for(int i = 0; i < node.length(); i++)
        {
            if(node.charAt(i) != ' ')
            {
                nodeBinary[count] = node.charAt(i) - '0';
                count++;
            }
        }

        return nodeBinary;
    }

    /**
     * Convert array of 0s and 1s to String with integers separated by spaces.
     */
    public static String getStringFromBinaryIntArray(int[] binArray)
    {
        StringBuilder sb = new StringBuilder((numberOfBits * 2) - 1);

        for(int i = 0; i < numberOfBits; i++)
        {
            sb.append(binArray[i]);

            if(i < numberOfBits - 1){
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    /**
     * Read the data to be used in the assignment
     * @return An String array that contains the nodes of the Graph
     */
    private static String[] readNodesArrayFromFile()
    {
        String[] nodesArray;

        try
        {
            FileInputStream f = new FileInputStream("D:/javaer/abc/StanAl/p3/clustering_bigTest.txt");
            DataInputStream d = new DataInputStream(f);
            BufferedReader b =  new BufferedReader(new InputStreamReader(d));

            String str = b.readLine();

            numberOfNodes = Integer.parseInt(str.split(" ")[0]);
            nodesArray = new String[numberOfNodes];

            numberOfBits = Integer.parseInt(str.split(" ")[1]);

            int index = 0;

            while((str=b.readLine())!=null)
            {
                nodesArray[index] = str.trim();
                index++;
            }

            b.close();
        }
        catch(Exception e){
            nodesArray = new String[0];
        }

        return nodesArray;
    }
}
