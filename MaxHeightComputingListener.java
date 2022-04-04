package edu.psu.ist311.bst.traversal;

import edu.psu.ist311.bst.ISearchTree;

public final class MaxHeightComputingListener<T> implements ITreeListener<T> {
    public int maxHeightSoFar, scratchHeight;

    public MaxHeightComputingListener() {
        this.scratchHeight = 0;
    }

    public int getMaxHeight() {
        return maxHeightSoFar;
    }

    @Override
    public void enterISearchTree(ISearchTree<T> e) {
        if (scratchHeight > maxHeightSoFar) {
            maxHeightSoFar = scratchHeight;
        }
        scratchHeight++;
    }

    @Override
    public void exitISearchTree(ISearchTree<T> e) {
        scratchHeight--;
    }

}
