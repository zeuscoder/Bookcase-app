package com.zeus.bookcase.app.home.model.annotation;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by zeus_coder on 2016/4/3.
 */
public class BookAnnotation extends BmobObject implements Serializable {

    private int count;

    private int start;

    private int total;

    private List<Annotations> annotations ;

    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setStart(int start){
        this.start = start;
    }
    public int getStart(){
        return this.start;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    public void setAnnotations(List<Annotations> annotations){
        this.annotations = annotations;
    }
    public List<Annotations> getAnnotations(){
        return this.annotations;
    }
}
