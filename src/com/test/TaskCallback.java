/* 
**
** Copyright 2014, Jules White
**
** 
*/
package com.test;

public interface TaskCallback<T> {

    public void success(T result);

    public void error(Exception e);

}
