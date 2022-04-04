package edu.psu.ist311.bst;

import edu.psu.ist311.bst.traversal.AveragingTreeListener;
import edu.psu.ist311.bst.traversal.ContainmentCheckingListener;
import edu.psu.ist311.bst.traversal.ITreeListener;
import edu.psu.ist311.bst.traversal.MaxHeightComputingListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreeTests {

    @Test
    public void testHeightComputingListener() {
        ISearchTree<Integer> bst =
                new STTree<>(10, Integer::compareTo)
                        .add(12).add(5)
                        .add(4).add(20)
                        .add(8).add(7).add(15);

        MaxHeightComputingListener<Integer> l = new MaxHeightComputingListener<>();
        bst.accept(l);
        Assertions.assertEquals(3, l.getMaxHeight());

        // now add 16...
        bst = bst.add(16);
        l = new MaxHeightComputingListener<>();
        bst.accept(l);
        Assertions.assertEquals(4, l.getMaxHeight());
    }
    @Test
    public void testAveragingTreeListener() {
        ISearchTree<Integer> bst =
                new STTree<>(10, Integer::compareTo)
                        .add(1).add(2)
                        .add(3).add(4)
                        .add(5);
        AveragingTreeListener a = new AveragingTreeListener();
        bst.accept(a);
        Assertions.assertEquals(4, a.getAverage()); //technically 4.16
    }

    @Test
    public void testContainmentCheckingListener() {
        ISearchTree<Integer> bst =
                new STTree<>(10, Integer::compareTo)
                        .add(1).add(2)
                        .add(3).add(4)
                        .add(5);
        ContainmentCheckingListener<Integer> c = new ContainmentCheckingListener<>(3);
        bst.accept(c);
        Assertions.assertEquals(true, c.isFound());
    }

    @Test
    public void testAdd1(){
        ISearchTree<Integer> bst = new STTree<>(10, Integer::compareTo);
        bst.add(5);
        bst.add(15);
        bst.add(20);
        Assertions.assertEquals("10, 5, 15, 20", bst.toString());
    }

    @Test
    public void testAddIter1(){
        ISearchTree<Integer> bst = new STTree<>(10, Integer::compareTo);
        bst.addIter(5);
        bst.addIter(15);
        bst.addIter(20);
        Assertions.assertEquals("10, 5, 15, 20", bst.toString());
    }


    @Test
    public void testAddFailure(){
        ISearchTree<Integer> bst = new STTree<>(10, Integer::compareTo);
        bst.add(5);
        bst.add(15);
        Assertions.assertThrows(Exception.class, () -> bst.add(15));
    }

    @Test
    public void testPreOrder1(){
        ISearchTree<Integer> bst = new STTree<>(10, Integer::compareTo);
        bst.add(5);
        bst.add(15);
        bst.add(20);
        String result = bst.dumpPreorder();
        int x = 0;
        Assertions.assertEquals("10, 5, 15, 20", bst.toString());
    }

    @Test
    public void testPostOrder1(){
        ISearchTree<Integer> bst = new STTree<>(10, Integer::compareTo);
        bst.add(5);
        bst.add(15);
        bst.add(20);
        String result1 = bst.dumpPostorder();
        int x = 0;
        Assertions.assertEquals("5, 20, 15, 10", bst.dumpPostorder());
    }

    @Test
    public void testAdd2(){
        ISearchTree<Integer> bst = new STTree<>(10, Integer::compareTo);
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(4);
        bst.add(5);

        Assertions.assertEquals("10, 1, 2, 3, 4, 5", bst.toString());
    }


    @Test
    public void testPreOrder2(){
        ISearchTree<Integer> bst = new STTree<>(10, Integer::compareTo);
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(4);
        bst.add(5);
        String result = bst.dumpPreorder();
        Assertions.assertEquals("10, 1, 2, 3, 4, 5", bst.toString());
    }

    @Test
    public void testPostOrder2(){
        ISearchTree<Integer> bst = new STTree<>(10, Integer::compareTo);
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(4);
        bst.add(5);
        String result1 = bst.dumpPostorder();
        Assertions.assertEquals("5, 4, 3, 2, 1, 10", bst.dumpPostorder());
    }


}
