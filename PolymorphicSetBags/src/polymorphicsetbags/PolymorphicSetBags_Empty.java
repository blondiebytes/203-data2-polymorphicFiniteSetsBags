
package polymorphicsetbags;

import static polymorphicsetbags.PolymorphicSetBags.empty;


public class PolymorphicSetBags_Empty implements Bag {
    
    // These method shouldn't change b/c empty doesn't change -> non-empty 
    // changes with the new rules of a set bag. Just have to make it generic
    
    public void PolymorphicSetBags_Empty() {
    }
    
    public int cardinality() {
	return 0;
    }
    
    public boolean isEmptyHuh() {
        return true; 
    }
    
    public boolean member(Object elt) {
	return false;
    }

    public Bag remove (Object elt) {
	return this;
    }

    public Bag add(Object elt) {
        return new PolymorphicSetBags(elt);
    }
    
    public Bag union(Bag u) {
        return u;
    }
    
    public Bag inter(Bag u) {
        return empty();  
        }
    
    public Boolean equal (Bag u) {
        return u.cardinality() == this.cardinality();
    }
    
    public Bag diff(Bag u) {
        return u;
    }
    
    public Boolean subset (Bag u) {
        return true;
    }
    
}
