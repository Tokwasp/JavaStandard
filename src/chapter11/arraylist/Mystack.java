package chapter11.arraylist;

import java.util.Vector;

public class Mystack extends Vector {
    public Object push(Object item){
        addElement(item);
        return item;
    }

    public Object pop(){
        Object obj = peek();
        removeElement(size() - 1);
        return obj;
    }

    public Object peek(){
        return size() == 0;
    }

    public int search(Object o){
        int i= lastIndexOf(o);

        if( i >= 0){
            return size() - i;
        }
        return -1;
    }
}
