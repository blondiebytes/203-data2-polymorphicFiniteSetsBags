
package polymorphicsetbags;

public interface Bag<D extends Comparable> {
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
    public Boolean equal (Bag u);
    public Boolean subset (Bag u);
    
}

