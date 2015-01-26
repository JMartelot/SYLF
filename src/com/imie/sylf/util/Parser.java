package com.imie.sylf.util;

import java.util.List;

public abstract interface Parser<T>{
    public abstract void parseJSON(String output);
    public abstract void addDataBase(List<T> liste);
}
