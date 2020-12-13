package com.company;

import java.util.*;

interface MyCollection<T>{
    int size();
    boolean isEmpty();
    boolean Add(T el);
    boolean remove(T el);
}

public class DLinkedList<T> implements MyCollection<T>,Iterable<T>{
    DLinkedList<T> Next;
    DLinkedList<T> Prev;
    transient DLinkedList head;
    transient DLinkedList tail;
    transient int count;
    T data;
    public DLinkedList(){
        Next=null;
        Prev=null;
    }
    DLinkedList(T el){
     data=el;
     Next=null;
     Prev=null;
    }
    @Override
    public boolean Add(T el){//Appends the specified element to the end of this list.
        try {
            if(isEmpty()){
                data=el;
                count=1;
                head=this;
                tail=this;
            }
            else{
                tail.Next=new DLinkedList(el);
                tail.Next.Prev=tail;
                tail=tail.Next;
            }
            count++;
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean remove(T el) {//removes the first occurrence
        try {
           DLinkedList tmp=head;
           if(head.data==el){
               if(head==tail){
                   head=null;
                   tail=null;
                   count--;
               }
               else{
                   head=head.Next;
                   head.Prev=null;
                   count--;
               }
               return true;
           }
           else{
               tmp=tmp.Next;
               while(tmp!=tail){
                   if(tmp.data==el){
                       tmp.Prev.Next=tmp.Next;
                       tmp.Next.Prev=tmp.Prev;
                       count--;
                       return true;
                   }
                   tmp=tmp.Next;
               }
               if(tail.data==el){
                   tail=tail.Prev;
                   tail.Next=null;
                   count--;
               }
               return true;
           }

        }
        catch (Exception ex) {
            return false;
        }
    }

    @Override
    public int size(){
        return count;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0)
            return true;
        else
            return false;
    }

    @Override
    public Iterator<T> iterator()  {//implemented for foreach
        return new MyListIterator();
    }
    private class MyListIterator implements Iterator<T>{//class iterator
        private DLinkedList<T> curr;
        @Override
        public boolean hasNext() {
            return this.curr != null;
        }

        public MyListIterator() {
            this.curr = DLinkedList.this.head;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            T tmp=curr.data;
            curr=curr.Next;
            return tmp;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
