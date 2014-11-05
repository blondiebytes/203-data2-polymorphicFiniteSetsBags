
package polymorphicsetbags.SetBag;

import polymorphicsetbags.Sequence.Sequence;
import polymorphicsetbags.Sequence.Sequenced;

public interface Bag<D extends Comparable> extends Sequenced<D>{
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
    public Sequence<D> seq();
    public int sumIt ();
    public int sumItS(Sequence<D> as);
    public String stringIt();
    public String stringItS(Sequence<D> as);
    public Bag<D> blacken();
    public Bag<D> addInner(D elt, int n);
    public Bag<D> rbInsert(D elt, int n);
    public boolean isRedHuh();
}

