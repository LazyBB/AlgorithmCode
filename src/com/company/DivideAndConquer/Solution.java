package com.company.DivideAndConquer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n=s.length();
        int maxlen=0;
        Map<Character,Integer> map=new HashMap<>();
        for (int end=0,start=0;end<n;end++){
            if (map.containsKey(s.charAt(end))){
                start=Math.max(map.get(s.charAt(end))+1,start);
            }
            maxlen=Math.max(maxlen,end-start+1);
            map.put(s.charAt(end),end);
        }
        return maxlen;
    }
}
