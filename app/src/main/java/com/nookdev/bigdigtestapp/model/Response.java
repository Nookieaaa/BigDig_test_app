package com.nookdev.bigdigtestapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Response {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private Data data;


    public List<Item> extractData(){
        return data.getItems();
    }

    public class Data {
        @SerializedName("items")
        @Expose
        private List<Item> items = new ArrayList<Item>();

        public List<Item> getItems(){
            return items;
        }
    }



}