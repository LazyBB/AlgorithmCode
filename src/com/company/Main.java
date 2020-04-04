package com.company;


import com.company.DivideAndConquer.Solution;
import com.company.tree.RBTree;
import com.company.tree.TreeOperation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RBTree<String, Object> rbt = new RBTree();
        //测试输入：ijkgefhdabc
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入key:");
            String key = sc.next();

            rbt.insert(key, null);
            rbt.print_tree();
        }
    }
}
