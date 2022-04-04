package edu.psu.ist311.bst;

import edu.psu.ist311.bst.traversal.ContainmentCheckingListener;
import edu.psu.ist311.bst.traversal.ITreeListener;

import java.util.Comparator;

// todo: implementation of ISearchTree in here
public class STTree<E> implements ISearchTree<E> {


    private ISearchTree<E> left;
    private ISearchTree<E> right;
    private E data;
    private final Comparator<E> order;



    //creating a STTree with some data, and (non-null) left and right subtrees
    private STTree(E data, ISearchTree<E> left, ISearchTree<E> right, Comparator<E> order){
        this.left = left;
        this.right = right;
        this.data = data;
        this.order = order;
    }

    //"leaf" constructor
    public STTree(E data, Comparator<E> order){
        this(data, null, null, order);
    }


    @Override
    public E data() {
        return data;
    }

    @Override
    public Comparator<E> orderCmp() {
        return order;
    }

    @Override
    public ISearchTree<E> left() {
        return left;
    }

    @Override
    public ISearchTree<E> right() {
        return right;
    }

    @Override
    public ISearchTree<E> add(E e) {

        if(order.compare(e, this.data) < 0){
            if(left != null){
                left.add(e);
            }
            else{
                ISearchTree<E> newLeft = new STTree<E>(e, order);
                this.setLeft(newLeft);
            }
        }else if(order.compare(e, this.data) > 0){
            if(right != null){
                right.add(e);
            }else{
                ISearchTree<E> newRight = new STTree<E>(e, order);
                this.setRight(newRight);
            }
        } else{
            throw new IllegalArgumentException("duplicate key!");
        }
        return this;
    }

    @Override
    public ISearchTree<E> addIter(E e) {

        ISearchTree<E> siteToAttach = search(e);
        if(order.compare(e, siteToAttach.data()) < 0){
            ISearchTree<E> newLeft = new STTree<E>(e, order);
            siteToAttach.setLeft(newLeft);
        }else if(order.compare(e, siteToAttach.data()) > 0){
            ISearchTree<E> newRight = new STTree<E>(e, order);
            siteToAttach.setRight(newRight);
        }else{
            throw new IllegalArgumentException("duplicate key!");
        }
        return this;

    }

    private ISearchTree<E> search(E key){
        ISearchTree<E> v = this;
        boolean absent = false;

        while(!absent && !(key.equals(v.data()))){
            if((order.compare(key, v.data()) < 0) && (v.left() != null)){
                v = v.left();
            }else if((order.compare(key, v.data()) > 0) && (v.right() != null)){
                v = v.right();
            }else{
                absent = true;
            }
        }
        return v;
    }

    @Override
    public boolean contains(E e) {
        //return search(e).equals(e);
        ContainmentCheckingListener<E> c = new ContainmentCheckingListener<>(e);
        this.accept(c);
        return true;
    }

    @Override
    public void setLeft(ISearchTree<E> l) {
        this.left = l;

    }
    @Override
    public void setRight(ISearchTree<E> r) {
        this.right = r;
    }

    @Override
    public String dumpPreorder() {
        String result = this.data.toString();

        if(left != null){
            result += ", " + left.dumpPreorder();
        }
        if(right != null){
            result += ", " + right.dumpPreorder();
        }
        return result;
    }

    @Override
    public String dumpPostorder() {
        String result1 = "";

        if(left != null){
            result1 = result1 + left.dumpPostorder() + ", ";
        }
        if(right != null){
            result1 = result1 + right.dumpPostorder() + ", ";
        }
        return result1 + this.data;
    }

    @Override
    public String toString(){

        return dumpPreorder();
    }

    @Override
    public void accept(ITreeListener<E> listener){
        listener.enterISearchTree(this);

        if(left != null){
            left.accept(listener);
        }
        if(right != null){
            right.accept(listener);
        }
        listener.exitISearchTree(this);
    }


}
