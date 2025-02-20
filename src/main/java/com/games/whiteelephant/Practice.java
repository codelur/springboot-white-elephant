package com.games.whiteelephant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.grpc.netty.shaded.io.netty.handler.codec.DateFormatter;

public class Practice{
    public static void main(String[] args) {
        System.out.println("Hello");
        doMath();
            }
        
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
        
                List<Integer> intList = new ArrayList<>(List.of(3,4,5,6));
                Integer[] integersArray = list.stream().toArray(Integer[]::new);
                Stream<Integer> intStream = Arrays.stream(integersArray);
        
                int[] intArray = intList.stream()          // Convert List to Stream
                                        .mapToInt(Integer::intValue) // Convert Integer to int
                                        .toArray();  
        
        
        
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

        int items = 345;
        int itemsPerBag = 58;
        int bags = (int) Math.ceil((double)items/itemsPerBag);
        System.out.println(bags);
        Double gt = 4.432D;
        Float ft = Float.valueOf((float) gt.doubleValue()) ;
        System.out.println(ft);

    }


}