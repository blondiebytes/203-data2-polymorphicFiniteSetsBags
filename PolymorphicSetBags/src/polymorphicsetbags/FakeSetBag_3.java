/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags;


public class FakeSetBag_3<D extends Comparable> implements FakeBag<D> {
    Bag<D> leftTree;
    D keyOne;
    int valueOne;
    Bag<D> middleTree;
    D keyTwo;
    int valueTwo;
    Bag<D> rightTree;
    
    public Bag<D> fake1() {
        return new SetBag_NonEmpty(keyTwo, valueTwo, new SetBag_NonEmpty
        (this.keyOne, this.valueOne, this.leftTree, this.middleTree),
                new SetBag_1(this.rightTree));
    }
    
    public Bag<D> fake2() {
        
    }
            
}
