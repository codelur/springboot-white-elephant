package com.games.whiteelephant.functionality;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Functionality {
    
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

}
