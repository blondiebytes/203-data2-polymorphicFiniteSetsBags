/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags.SetBag;

// fake1 = func

import polymorphicsetbags.FakeBag.FakeBag;

// fake2 = func

// T1 = class
// fake3 = class
// fakeL = class

//implement it on the next


public class SetBag_1<D extends Comparable> implements Bag<D> {
    Bag<D> next;

   public SetBag_1(Bag<D> next) {
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
    
    public Bag<D> smartInsert(D key) {
     return smartInsertN(key, 1);
    }

    public Bag<D> smartInsertN(D key, int value) {
        return smartInsertStep1(key, value).smartInsertStep2();
    }

//    [(T:1 N)
//       (fake:1 (step1 N))]

    public FakeBag<D> smartInsertStep1(D key, int value) {
        return this.next.smartInsertStep1(key, value).fake1();
    }
    
    public Bag<D> smartInsertStep2() {
        return this;
    }
    
//    [t
//       t]))
    public FakeBag<D> fake1() {
        return new SetBag_1(this);
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
    
    public String toStringBST() {
       return "[SB:1 next: " + this.next.toStringBST() + "]";
    }
    
    
    
    
}
