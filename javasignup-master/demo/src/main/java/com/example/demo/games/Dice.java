package com.example.demo.games;

import java.util.ArrayList;
import java.util.List;

public class Dice {
    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    private List<Integer>list= new ArrayList<>();
    public Dice(List<Integer> list) {
        this.list = list;
    }
}
