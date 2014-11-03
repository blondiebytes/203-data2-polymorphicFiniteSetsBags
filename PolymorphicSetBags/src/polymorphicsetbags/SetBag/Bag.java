
package polymorphicsetbags.SetBag;

import polymorphicsetbags.FakeBag.FakeBag;
import polymorphicsetbags.Sequence.Sequence;
import polymorphicsetbags.Sequence.Sequenced;

public interface Bag<D extends Comparable> extends FakeBag<D>, Sequenced<D>{
    public int cardinality();
    public int getCount(D elt);
    public  boolean isEmptyHuh();
    public  boolean member(D elt); 
    public Bag remove (D elt);
    public Bag removeN(D elt, int n);
    public Bag removeAll(D elt);
    public Bag add(D elt);
    public Bag addN(D elt, int n);
    public Bag union(Bag u);
    public Bag inter(Bag u);
    public Bag diff(Bag u);
    public boolean equal (Bag u);
    public boolean subset (Bag u);
    public FakeBag<D> smartInsertStep1(D key, int value);
    public Bag<D> smartInsert(D key);
   public Bag<D> smartInsertN(D key, int value);
    public String toStringBST();
    public Sequence<D> seq();
}

