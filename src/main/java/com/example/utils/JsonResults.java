package com.example.utils;

public class JsonResults {
    private int status;
    private Object list;
    private Object pageinfo;
    private Object searchinfo;

    public JsonResults() {
    }
    public JsonResults(int status) {
        super();
        this.status = status;
    }
    public JsonResults(int status, Object list) {
        super();
        this.status = status;
        this.list = list;
    }
    public JsonResults(Object list, Object pageinfo, Object searchinfo) {
        super();
        this.list = list;
        this.pageinfo = pageinfo;
        this.searchinfo = searchinfo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public Object getPageinfo() {
        return pageinfo;
    }

    public void setPageinfo(Object pageinfo) {
        this.pageinfo = pageinfo;
    }

    public Object getSearchinfo() {
        return searchinfo;
    }

    public void setSearchinfo(Object searchinfo) {
        this.searchinfo = searchinfo;
    }
}
