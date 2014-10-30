/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags.FakeBag;

import polymorphicsetbags.SetBag.Bag;
import polymorphicsetbags.FakeBag.FakeSetBag_3;
import polymorphicsetbags.SetBag.SetBag_NonEmpty;
import static polymorphicsetbags.SetBag.SetBag_NonEmpty.empty;


public class FakeSetBag_L<D extends Comparable> implements FakeBag<D>{
    
    public D key;
    public int value;
    
   public FakeSetBag_L (D key, int value){
    this.key = key;
    this.value = value;
}
    
  public Bag<D> fake1() {
      return new SetBag_NonEmpty(this.key, this.value, empty(), empty());
    }
  
  public Bag<D> smartInsertStep2(D key, int value) {
     return new SetBag_NonEmpty(this.key, this.value, empty(), empty());
  }
  
    
}
