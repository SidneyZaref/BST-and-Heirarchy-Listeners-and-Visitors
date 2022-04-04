package edu.psu.ist311.bst.traversal;

import edu.psu.ist311.bst.ISearchTree;


public final class AveragingTreeListener implements ITreeListener<Integer> {
    private int average;
    private int sum;
    private int count;

    public int getAverage(){
        return average;
    }

    @Override
    public void enterISearchTree(ISearchTree<Integer> e){
        sum = sum + e.data();
        count++;
        average = sum/count;
    }
}
