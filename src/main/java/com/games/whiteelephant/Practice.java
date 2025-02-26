package com.games.whiteelephant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.firebase.database.core.utilities.Tree;
import com.google.firebase.database.core.utilities.TreeNode;

import io.grpc.netty.shaded.io.netty.handler.codec.DateFormatter;

public class Practice{
    public static void main(String[] args) {
        System.out.println("Hello");
        int max = lengthOfLongestSubstringTwoDistinct("ccaabbb");
;    }
        
            public void streamsFromArrays(){
                int[] array = new int[]{1,2,3,4,5,6,7,8,9,10};
        
                List<Integer> Larrays = Arrays.stream(array).boxed().collect(Collectors.toList());
                Integer[] boxedArray1 = Larrays.stream().toArray(Integer[]::new);
                Integer[] boxedArray = Arrays.stream(array).boxed().toArray(Integer[]::new);
                List<Integer> Larray = Arrays.asList(boxedArray);
        
                ArrayList<Integer> LarrayList = Arrays.stream(array)
                                              .boxed()  // Convert int to Integer
                                              .collect(Collectors.toCollection(ArrayList::new));
                
                Set<Integer> LarraysSet = Arrays.stream(array)
                                             .boxed()
                                             .collect(Collectors.toCollection(HashSet::new));
                
                String[] arrayString = new String[]{"Hola","Adios","Tomorrow"};
        
                List<String> lString = Arrays.stream(arrayString).collect(Collectors.toCollection(ArrayList::new));
            }
        
            public void streamsFromLists(){
                List<String> list = new ArrayList<>(List.of("Hola", "Adios", "Tomorrow"));
                List<String> result = list.stream()
                                          .filter(s -> s.startsWith("A"))  // Filter strings starting with "A"
                                          .map(String::toUpperCase)        // Convert to uppercase
                                          .sorted()
                                          .collect(Collectors.toList());
        
                String[] stringsArray = list.stream().toArray(String[]::new);
                String k = "Dsadsa";
                List<Integer> intList = new ArrayList<>(List.of(3,4,5,6));
                Integer[] integersArray = list.stream().toArray(Integer[]::new);
                Stream<Integer> intStream = Arrays.stream(integersArray);
                
                int[] intArray = intList.stream()          // Convert List to Stream
                                        .mapToInt(Integer::intValue) // Convert Integer to int
                                        .toArray();  
        
                //List<List<String>> result = new ArrayList<>();
        
            }
        
            public void stacksAndQueues(){
                Stack<Integer> sI = new Stack<>();
                List<Integer> itemsToAdd = Arrays.asList(1, 2, 3, 4, 5);
                sI.addAll(itemsToAdd);
                sI.push(10);
        
                Queue<Integer> qI = new LinkedList<>();
                qI.offer(6); // returns false if the queue is full
                qI.add(9); // exception if queue is full
        
            }
        
    public static void maps(){
        LocalDate today = LocalDate.now();
        LocalDate inOneWeek = LocalDate.now().plusWeeks(1);
        System.out.println(today.isAfter(inOneWeek));
        System.out.println(LocalDate.parse("2025-08-03"));
        String dateFormat = "MM/dd/yyyy";
        LocalDate date = LocalDate.parse("03/05/2025", DateTimeFormatter.ofPattern(dateFormat));
        System.out.println(date.lengthOfMonth());
    }

    public static void doMath(){
        Float fFloat = 3.54F;
        Float gFloat = 3.54F;
        Float hFloat = fFloat * gFloat;
        System.out.println( String.format("%.3f", hFloat));

        Integer items = 345;
        Integer itemsPerBag = 58;
        Integer bags = (int) Math.ceil((double)items/itemsPerBag);
        System.out.println(bags);
        Double gt = 4.432D;
        Float ft = Float.valueOf((float) gt.doubleValue()) ;
        System.out.println(ft);
        
        Deque<String> pathDeque = new ArrayDeque<>();
        
        
        int[] preorder = new int[9];
        int[] inorder = new int[9];
        List<Integer> visited = new ArrayList<>();
        int i=0;
        int j=0;
        
        while(i<preorder.length){
            while(j<preorder.length && preorder[i]!=inorder[j]){
                visited.add(inorder[i]);
                
                j++;
            }
            j++;
            while(visited.contains(preorder[i]))
                i++;
            


        }

    }

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character,Integer> index = new HashMap<>();

        int max = 1;
        int currentLength = 1;
        index.put(s.charAt(0),0);
        for(int i=1;i<s.length();i++){
            Character c = s.charAt(i);
            if(index.size()!=2){
                if(!index.containsKey(c) || (index.containsKey(c) && index.get(c)!=i-1))
                    index.put(c,i);
                currentLength++;
            }else{
                Map.Entry<Character, Integer> entryMax = index.entrySet().stream()
                .max((a,b)->a.getValue()-b.getValue()).orElse(null);
                Map.Entry<Character, Integer> entryMin = index.entrySet().stream()
                .min((a,b)->a.getValue()-b.getValue()).orElse(null);

                if(index.containsKey(c)){
                    if(index.get(c)==entryMin.getValue())
                        index.put(c,i);
                    currentLength++;
                }else{
                    
                    index.remove(entryMin.getKey());
                    index.put(c,i);
                    currentLength = i - entryMax.getValue() + 1;

                }

            }
            max = Math.max(max,currentLength);    
        }

        return max;
    }

    public int flipgame(int[] fronts, int[] backs) {
        boolean[] ignoreList = new boolean[2000];
        int min = 2001;

        //getting the list of numbers to be ignored
        for(int i=0;i<fronts.length;i++)
            if(fronts[i]==backs[i])
                ignoreList[fronts[i]-1] = true;

        //
        for(int i=0;i<fronts.length;i++){
            if(!ignoreList[fronts[i]-1])
                min = Math.min(min,fronts[i]);
            if(!ignoreList[backs[i]-1])
                min = Math.min(min,backs[i]);

        }
      
        return min==2001?0:min;
    }


}