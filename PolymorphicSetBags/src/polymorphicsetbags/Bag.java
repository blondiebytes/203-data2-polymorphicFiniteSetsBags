
package polymorphicsetbags;

public interface Bag<D> {
    public int cardinality();
    public  boolean isEmptyHuh();
    public  boolean member(D elt); 
    public Bag remove (D elt);
    public Bag add(D elt);
    public Bag union(Bag u);
    public Bag inter(Bag u);
    public Bag diff(Bag u);
    public Boolean equal (Bag u);
    public Boolean subset (Bag u);
    
}

