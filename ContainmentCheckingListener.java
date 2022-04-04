package edu.psu.ist311.bst.traversal;

import edu.psu.ist311.bst.ISearchTree;

public final class ContainmentCheckingListener<E> implements ITreeListener<E>{

    private boolean found = false;
    private E key;

    public ContainmentCheckingListener(E key){
        this.key = key;
    }

    public boolean isFound(){
        return found;
    }

    @Override
    public void enterISearchTree(ISearchTree<E> e) {
        if(e.data().equals(key)){
            found = true;
        }
    }
}
