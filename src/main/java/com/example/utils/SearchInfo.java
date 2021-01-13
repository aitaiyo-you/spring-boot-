package com.example.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SearchInfo {
    private Boolean ispage=false;
    private int max=5;
    private Integer pageno=1;
    private Map<String, String> params=new HashMap<String, String>();
    private String where="";


    public boolean isParam(String name){
        return params.containsKey(name);
    }

    public boolean isNoNull(String name){
        if( params.containsKey(name)){
            if(params.get(name)!=null) return true;
        }
        return false;
    }
    public int getInt(String name){
        if(!isNoNull(name))return 0;
        return Integer.valueOf(params.get(name));
    }
    public String get(String name){
        return params.get(name);
    }
    public void set(String name,String val){
        params.put(name, val);
    }
    public String getLimit(){
        if(!ispage) return "";
        return " limit "+((pageno-1)*max)+","+max;
    }


    public static SearchInfo create(HttpServletRequest req, Boolean ispage){

        SearchInfo info=	new SearchInfo();
        info.ispage=ispage;
        String pageno=req.getParameter("pageno");
        if(pageno!=null) info.pageno=Integer.valueOf(pageno);
        if(ispage){
            req.setAttribute("pre", info.pageno>1?info.pageno-1:1);
            req.setAttribute("next", info.pageno+1);
        }

        Enumeration<String> pn=req.getParameterNames();
        while(pn.hasMoreElements()){
            String name=pn.nextElement();
            info.params.put(name, req.getParameter(name));
        }

        req.setAttribute("search", info.params);
        return info;
    }


    public void setWhere(String where) {
        this.where = where;
    }


    public String getWhere() {

        return where;
    }
}
