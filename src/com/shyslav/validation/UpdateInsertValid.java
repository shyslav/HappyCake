package com.shyslav.validation;

/**
 * Created by Shyshkin Vladyslav on 19.05.2016.
 */
public class UpdateInsertValid {
    public static boolean MaxMinLenght(int max , int min , String str)
    {
        if(str == null)
        {
            return false;
        }
        if(str.length()>max || str.length() < min)
        {
            return false;
        }
        return true;
    }
}
