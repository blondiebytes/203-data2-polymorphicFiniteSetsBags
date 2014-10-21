
package polymorphicsetbags;

public interface Bag<Comparable> {
    public int cardinality();
    public  boolean isEmptyHuh();
    public  boolean member(Comparable elt); 
    public Bag remove (Comparable elt);
    public Bag add(Comparable elt);
    public Bag union(Bag u);
    public Bag inter(Bag u);
    public Bag diff(Bag u);
    public Boolean equal (Bag u);
    public Boolean subset (Bag u);
    
}

