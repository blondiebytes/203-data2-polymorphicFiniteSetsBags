
package polymorphicsetbags.SetBag;


import polymorphicsetbags.Sequence.Sequence;
import polymorphicsetbags.Sequence.Sequence_Empty;
import static polymorphicsetbags.SetBag.SetBag_NonEmpty.empty;



public class SetBag_Empty<D extends Comparable> implements Bag<D> {
    boolean isRed;
    
    
    public void SetBag_Empty() {
       
    }
    
    public void SetBag_Empty(Boolean isRed) {
        this.isRed = isRed;
    }
    
      public Bag<D> left() throws Exception {
        throw new Exception();
    }
    
    public Bag<D> right() throws Exception {
        throw new Exception();
    }
    
    
    //Balance Tree methods
    
    public Bag<D> blacken() {
        return this;
    }
    
    public Bag<D> addInner(D elt, int n) {
        return new SetBag_Empty();
    }
    
    public boolean isRedHuh() {
        return isRed;
    }
           
    // Sequence Methods
    public Sequence<D> seq() {
        return new Sequence_Empty();
    }
    
    public int sumIt () {
        return sumItS(this.seq());
    }
    
    public int sumItS(Sequence<D> as) {
        return 0;
    }
    
    public String stringIt() {
        return stringItS(this.seq());
    }
    
    public String stringItS(Sequence<D> as) {
        return "";
    }
    
    // Finite Set Bag Methods
    
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
