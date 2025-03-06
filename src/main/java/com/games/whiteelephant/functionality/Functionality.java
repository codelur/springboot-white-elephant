package com.games.whiteelephant.functionality;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Functionality {
    
    public class ListNode {
             int val;
             ListNode next;
             ListNode() {}
             ListNode(int val) { this.val = val; }
             ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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

    //LeetCode 115
    private int distinctSubsequences(String s, String t,int i,int j, Integer[][] dp){
        //count when there is no more characters to match in t
        if (t.length() == j)
            return 1;
        if(s.length()==i)
            return 0;

        if(dp[i][j] != null)    return dp[i][j];

        int countSkip = distinctSubsequences( s,  t, i+1, j, dp);            
        int countMatch = 0;
        if(s.charAt(i)==t.charAt(j)){
            countMatch = distinctSubsequences( s,  t, i+1, j+1, dp);  
            
        }

        return dp[i][j] = countMatch + countSkip;

    }

    //0,1,2 are three different colors
    public int minCostPaintHouses(int[][] costs) {
        int numHouses = costs.length;
        int[][] dp = new int[numHouses][3];
        dp[0] = costs[0];
        for(int i =1;i<numHouses;i++){

                dp[i][0] = costs[i][0] + Math.min(dp[i-1][1],dp[i-1][2]);
                dp[i][1] = costs[i][1] + Math.min(dp[i-1][0],dp[i-1][2]);
                dp[i][2] = costs[i][2] + Math.min(dp[i-1][0],dp[i-1][1]);


        }
        return Math.min(dp[numHouses-1][0], Math.min(dp[numHouses-1][1], dp[numHouses-1][2]));
    }

    //Leetcode 10
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][ p.length() + 1];
        dp[0][0] = true;
        
        for(int i=1;i<=p.length();i++)
        //if *, check if we can ignore the previous char
            dp[0][i] = p.charAt(i-1)=='*'?dp[0][i-2]:false;
        

        for(int i=1;i<=s.length();i++)
            for(int j=1;j<=p.length();j++){
                if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='.')
                    //if they are equal or the place is required for p (".")
                    dp[i][j] = dp[i-1][j-1];
                
                else{
                    if( p.charAt(j-1)=='*'){
                        //ignore last char in pattern
                        dp[i][j] = dp[i][j - 2];

                        //keep last char in pattern so either
                        //the previous in pattern is '.'
                        //OR the previous in pattern match current in s
                        if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                            dp[i][j] = dp[i][j] || dp[i - 1][j]; 
                        }

                    }
                        
                } 

            }

        return dp[s.length() ][ p.length() ];

        
    }

    //Leetcode 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int digit = 0;
        int remainder = 0;
        ListNode result = new ListNode();
        ListNode node = new ListNode();
        result = node;
        while(l1 != null || l2 != null){
            int num1 = l1!=null?l1.val:0;
            int num2 = l2!=null?l2.val:0;
            int sum = num1 + num2 + remainder;
            digit = sum % 10;
            remainder = sum / 10;
            ListNode current = new ListNode(digit);
            node.next = current;
            node = current;
            l1 = l1!=null?l1.next:l1;
            l2 = l2!=null?l2.next:l2;

        }

        if(remainder>0)
            node.next = new ListNode(remainder);
        
        return result.next;
        
    }

}
