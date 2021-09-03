package com.awssqssender.AwsSenderSQS.Controller.Model;

import java.util.HashMap;
import java.util.List;

public class TestData {

    public String data1;
    public String data2;
    public String date;
    public List<HashMap<String, String>> hasmapList;

    public String getData1() {
        return this.data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return this.data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public List<HashMap<String, String>> getHasmapList() {
        return this.hasmapList;
    }

    public void setHasmapList(List<HashMap<String, String>> hasmapList) {
        this.hasmapList = hasmapList;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TestData(String date) {
        this.date = date;
    }

    public TestData(String data1, String data2, String date, List<HashMap<String, String>> hasmapList) {
        this.data1 = data1;
        this.data2 = data2;
        this.date = date;
        this.hasmapList = hasmapList;
    }

}