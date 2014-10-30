/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags;

import static polymorphicsetbags.SetBag_NonEmpty.empty;


public class FakeSetBag_L<D extends Comparable> implements FakeBag<D>{
    
    D key;
    int value;
    
    void FakeSetBag_L(D key, int value){
    this.key = key;
    this.value = value;
}
    
  public Bag fake1() {
      return new SetBag_NonEmpty(this.key, this.value, empty(), empty());
    }
  
  // [t (T:1 t)] ?? Statement
    
    public FakeBag fake2(D key2, int value2, Bag t1) {
        return new FakeSetBag_3(empty(), this.key, this.value, empty(), key2, value2, t1);
    }
    
    
}
