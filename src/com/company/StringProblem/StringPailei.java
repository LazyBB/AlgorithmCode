package com.company.StringProblem;

import java.util.*;

/*
输入一个字符串，打印出该字符串中字符的所有排列。
 */
public class StringPailei {
    public String[] permutation(String s) {
        List<String> ans = new ArrayList<>();
        Set<Character> set= new HashSet<>();
        char[] arrayS = s.toCharArray();
        Arrays.sort(arrayS);
        permutationHelper(ans, new boolean[s.length()], new LinkedList<Character>(), arrayS);
        String[] res = new String[ans.size()];
        for(int i = 0; i < ans.size(); i++){
            res[i] = ans.get(i);
        }
        return res;
    }
    private void permutationHelper(List<String> ans, boolean[] visited, LinkedList<Character> list, char[] s){
        if(list.size() == s.length){
            StringBuilder sb = new StringBuilder();
            for(char c : list){
                sb.append(c);
            }
            ans.add(sb.toString());
            return;
        }

        for(int i = 0; i < s.length; i++){
            if(i != 0 && s[i] == s[i - 1] && visited[i - 1]) continue;
            if(!visited[i]){
                list.addLast(s[i]);
                visited[i] = true;
                permutationHelper(ans, visited, list, s);
                list.removeLast();
                visited[i] = false;
            }
        }
    }

}
