package com.company.Stackproblem;

import java.util.Stack;

public class CQueue {
    Stack<Integer> stackin;
    Stack<Integer> stackout;
    public CQueue() {
        stackin= new Stack<Integer>();
        stackout= new Stack<Integer>();
    }

    public void appendTail(int value) {
        stackin.push(value);
    }

    public int deleteHead() {
        if (!stackout.isEmpty()){
            return stackout.pop();
        }
        if (!stackin.isEmpty()) {
            while (!stackin.isEmpty()) {
                stackout.push(stackin.pop());
            }
            return stackout.pop();
        }
        else {
            return -1;
        }

    }
}
