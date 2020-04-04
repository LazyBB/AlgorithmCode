package com.company.StringProblem;

public class juzhenlujing {
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) {
            return false;
        }
        char[] chars = word.toCharArray();
        int count = 1;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == chars[0]) {
                    if (findway(i, j, count, chars, visited, board))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean findway(int i, int j, int count, char[] chars, boolean[][] visited, char[][] board) {
        if (count == chars.length) {
            return true;
        }
        visited[i][j] = true;
        if (i - 1 >= 0 && visited[i - 1][j] == false && board[i-1][j]==chars[count]) {
            boolean up = findway(i-1,j,count+1,chars,visited,board);
            if (up) return true;
            visited[i - 1][j] = false;
        }
        if (i +1 <board.length && visited[i +1][j] == false && board[i +1][j]==chars[count]) {
            boolean down = findway(i +1,j,count+1,chars,visited,board);
            if (down) return true;
            visited[i +1][j] = false;
        }
        if (j - 1 >= 0 && visited[i][j-1] == false && board[i][j-1]==chars[count]) {
            boolean left = findway(i,j-1,count+1,chars,visited,board);
            if (left) return true;
            visited[i][j-1] = false;
        }
        if (j+1<board[0].length && visited[i][j+1] == false && board[i][j+1]==chars[count]) {
            boolean right = findway(i,j+1,count+1,chars,visited,board);
            if (right) return true;
            visited[i][j+1] = false;
        }
        visited[i][j] = false;
        return false;
    }
}
