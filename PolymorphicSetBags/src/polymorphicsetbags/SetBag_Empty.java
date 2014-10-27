
package polymorphicsetbags;

import static polymorphicsetbags.SetBag_NonEmpty.empty;



public class SetBag_Empty<D extends Comparable> implements Bag<D> {
    
    // These method shouldn't change b/c empty doesn't change -> non-empty 
    // changes with the new rules of a set bag. Just have to make it generic
    
    public void SetBag_Empty() {
        
    }
    
    public Sequence<D> seq() {
        return new Sequence_Empty();
    }
    
    public int getCount(D elt) {
        return 0;
    }
    
    public int cardinality() {
	return 0;
    }
    
    public boolean isEmptyHuh() {
        return true; 
    }
    
    public boolean member(D elt) {
	return false;
    }

    public Bag remove (D elt) {
	return this;
    }
    
    public Bag removeN(D elt, int n) {
        return this.remove(elt);
    }
    
    public Bag removeAll (D elt) {
        return this.remove(elt);
    }

    public Bag add(D elt) {
        return new SetBag_NonEmpty(elt);
    }
    
    public Bag addN(D elt, int n) {
        return new SetBag_NonEmpty(elt, n);
    }
    
    public Bag union(Bag u) {
        return u;
    }
    
    public Bag inter(Bag u) {
        return empty();  
    }
    
    public boolean equal (Bag u) {
        return u.cardinality() == this.cardinality();
    }
    
    public Bag diff(Bag u) {
        return u;
    }
    
    public boolean subset (Bag u) {
        return true; 
    }
    
}
