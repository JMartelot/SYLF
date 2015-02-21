package com.imie.sylf.util;

import java.util.List;

import android.os.Bundle;
/**
 * Parser
 * @author Quentin
 *
 * @param <T>
 */
public abstract interface Parser<T>{
    public abstract void parseJSON(String output);
    public abstract void listPopulate(List<T> liste);
    public abstract void entityPopulate(T entity);
}
