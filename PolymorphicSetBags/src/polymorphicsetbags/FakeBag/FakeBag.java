/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags.FakeBag;

import polymorphicsetbags.SetBag.Bag;


public interface FakeBag<D extends Comparable> {
    
   public Bag<D> smartInsertStep2();
   public FakeBag<D> fake1();
   
    
}
