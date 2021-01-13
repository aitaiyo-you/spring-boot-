package com.example.utils;

public class PageInfo {
    private int pre;
    private int next;


    public PageInfo(int pre, int next) {
        super();
        this.pre = pre;
        this.next = next;
    }

    public int getPre() {
        return pre;
    }

    public void setPre(int pre) {
        this.pre = pre;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }
}
