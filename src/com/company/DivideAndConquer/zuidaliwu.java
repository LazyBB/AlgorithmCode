package com.company.DivideAndConquer;

public class zuidaliwu {
    public int maxValue(int[][] grid) {
        int value=0;
        int n=grid.length;
        int m=grid[0].length;
        if(n==1){
            for(int i=0;i<m;i++){
                value+=grid[0][i];
            }
            return value;
        }
        if(m==1){
            for(int i=0;i<n;i++){
                value+=grid[i][0];
            }
            return value;
        }
        return Math.max(grid[0][0]+maxValue(subgrid(grid,n-1,m)),grid[0][0]+maxValue(subgrid(grid,n,m-1)));

    }
    private int[][] subgrid(int[][] grid,int a,int b){
        int[][] temp=new int[a][b];
        int n=grid.length;
        int m=grid[0].length;
        for(int i=0;i<a;i++){
            for(int j=0;j<b;j++){
                temp[i][j]=grid[n-a+i][m-b+j];
            }
        }
        return temp;
    }
}
