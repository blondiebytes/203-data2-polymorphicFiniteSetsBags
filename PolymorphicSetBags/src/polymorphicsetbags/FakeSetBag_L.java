/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags;


public class FakeSetBag_L<D extends Comparable> implements FakeBag<D>{
    
    D key;
    int value;
    
    void FakeSetBag_L(D key, int value){
    this.key = key;
    this.value = value;
}
    
  public Bag fake1() {
       
    }
    
    public Bag fake2() {
        
    }
    
    
}
