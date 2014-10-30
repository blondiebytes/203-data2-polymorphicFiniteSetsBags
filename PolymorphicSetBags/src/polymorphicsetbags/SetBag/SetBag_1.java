/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags.SetBag;

// fake1 = func

import polymorphicsetbags.SetBag.Bag;
import polymorphicsetbags.FakeBag.FakeBag;
import polymorphicsetbags.FakeBag.FakeOrRealBag;

// fake2 = func

// T1 = class
// fake3 = class
// fakeL = class

//implement it on the next


public class SetBag_1<D extends Comparable> implements Bag<D>, FakeOrRealBag<D>{
    Bag<D> next;

   public SetBag_1(Bag next) {
       this.next = next;
   }
    
    public int cardinality() {
        return next.cardinality();
    }

    public int getCount(D elt) {
        return next.getCount(elt);
    }

    public boolean isEmptyHuh() {
        return next.isEmptyHuh();
    }

    public boolean member(D elt) {
        return next.member(elt);
    }

    public Bag remove(D elt) {
       return next.remove(elt);
    }
    
    public Bag removeN(D elt, int n) {
        return next.removeN(elt, n);
    }

    public Bag removeAll(D elt) {
        return next.removeAll(elt);
    }

    // ~NEED TO IMPLEMENT~
    @Override
    public Bag add(D elt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bag addN(D elt, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    // ------------------------

    public FakeOrRealBag smartInsertStep1() {
        return fake1(this.next.smartInsertStep1());
    }
    
    public Bag fake1(Bag next) {
        // really should just return t
        return next;
    }
    
    
    public Bag union(Bag u) {
        return next.union(u);
    }

    public Bag inter(Bag u) {
       return next.inter(u);
    }

    public Bag diff(Bag u) {
        return next.diff(u);
    }

    public boolean equal(Bag u) {
       return next.equal(u);
    }

    public boolean subset(Bag u) {
        return next.subset(u); 
    }

    @Override
    public FakeBag smartInsertStep1(D key, int value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
