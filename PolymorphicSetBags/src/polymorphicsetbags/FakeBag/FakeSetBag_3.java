/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags.FakeBag;

import polymorphicsetbags.SetBag.Bag;
import polymorphicsetbags.SetBag.SetBag_1;
import polymorphicsetbags.SetBag.SetBag_NonEmpty;


public class FakeSetBag_3<D extends Comparable> implements FakeBag<D> {
    Bag<D> leftTree;
    D keyOne;
    int valueOne;
    Bag<D> middleTree;
    D keyTwo;
    int valueTwo;
    Bag<D> rightTree;
    
    public FakeSetBag_3(Bag leftTree, D keyOne, int valueOne, 
            Bag middleTree, D keyTwo, int valueTwo, Bag rightTree) {
        this.leftTree = leftTree;
        this.keyOne = keyOne;
        this.valueOne = valueOne;
        this.middleTree = middleTree;
        this.keyTwo = keyTwo;
        this.valueTwo = valueTwo;
        this.rightTree = rightTree;
                
    }
    
    public Bag<D> fake1() {
        return new SetBag_NonEmpty(keyTwo, valueTwo, new SetBag_NonEmpty
        (this.keyOne, this.valueOne, this.leftTree, this.middleTree),
                new SetBag_1(this.rightTree));
    }
   
    
    public Bag<D> smartInsertStep2(D key, int value) {
        return new SetBag_NonEmpty(keyTwo, valueTwo, new SetBag_NonEmpty(keyOne, valueOne, 
                leftTree, middleTree), new SetBag_1(rightTree));
    }
    

}
