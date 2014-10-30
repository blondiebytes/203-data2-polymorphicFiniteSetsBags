/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags;


public class FakeSetBag_3<D extends Comparable> implements FakeBag<D> {
    Bag leftTree;
    D keyOne;
    int valueOne;
    Bag middleTree;
    D keyTwo;
    int valueTwo;
    Bag rightTree;
    
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
    
    public Bag<D> fake2() {
//      [((fake:3 t1 k1 v1 t2 k2 v2 t3) k3 v3 (T:1 t4))
//       (T:2 (T:2 t1 k1 v1 t2) k2 v2 (T:2 t3 k3 v3 t4))]
//      [((fake:3 t1 k1 v1 t2 k2 v2 t3) k3 v3 (? T:2? t4))
//       (fake:3 (T:2 t1 k1 v1 t2) k2 v2 (T:1 t3) k3 v3 t4)]
    }
    
    // Other Problems:
    
//      [(t1 k1 v1 (fake:L k2 v2))
//       (fake:3 t1 k1 v1 (T:0) k2 v2 (T:0))]
//      [((T:1 t1) k1 v1 (fake:3 t2 k2 v2 t3 k3 v3 t4))
//       (T:2 (T:2 t1 k1 v1 t2) k2 v2 (T:2 t3 k3 v3 t4))]
//      [((? T:2? t1) k1 v1 (fake:3 t2 k2 v2 t3 k3 v3 t4))
//       (fake:3 t1 k1 v1 (T:1 t2) k2 v2 (T:2 t3 k3 v3 t4))]
//
//      [(t1 k1 v1 t2)
//       (T:2 t1 k1 v1 t2)]))
            
}
