/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sparrow.io.buffer.view;

/**
 *
 * @author ugurkara
 */
public interface StringConverter<T> {
    
    public String toString(T object);
    public T fromString(String string) ;
    
    
}
