package com.finedge.finedge.DTO;

import java.util.List;

public class ExpenseCategoryReport {
    private static Integer year;;
    private static  List<String> category;

    public ExpenseCategoryReport(Integer year,List<String> category ){
        this.year=year;
        this.category=category;
    }

    public static Integer getYear(){
        return year;
    }

    public static List<String> getCategory(){
        return category;
    }

}
