// Write a program to sort HashMap by value.
package main.java.com.learning.day4;
import java.util.*;
import java.util.Map.Entry;
public class Q4 {
    public static void main(String[] args) {
        HashMap<Integer, String > hm=new HashMap<Integer, String>();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of students:");
        int n=sc.nextInt();
        int r=0;
        String s="";
        System.out.println("enter student roll and name for "+n+" students:");
        for(int i=0;i<n;i++){
            r=sc.nextInt();
            sc.nextLine();
            s=sc.nextLine();
            hm.put(r,s);
        }
        //  printMap(hm);
        sortMapByValue(hm);

    }
    static void printMap(HashMap <Integer, String >m) {
        for (Entry<Integer, String> entry : m.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }
    static void sortMapByValue(HashMap<Integer, String >m){
        List<Entry<Integer, String >> ll=new LinkedList<Entry<Integer, String >>(m.entrySet());
        Collections.sort(ll, new Comparator<Entry<Integer, String >>() {
            @Override
            public int compare(Entry<Integer, String > o1, Entry<Integer, String > o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        HashMap<Integer, String> sortedMap = new LinkedHashMap<Integer, String>();
        for (Entry<Integer, String> entry : ll)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        printMap(sortedMap);
    }
}
