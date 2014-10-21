
package polymorphicsetbags;


public class PolymorphicSetBags<D> implements Bag {
     
    D root;
    Bag left;
    Bag right;
    
    // REQUIREMENTS: 
   
   // A finite bag is also called a mulitset and is like a set, 
   // but each element may occur many times. 
    
   // Your bags should be polymorphic, i.e. use generics to allow 
   // any kind of contents. 
  // By "pure", we mean that operations on the set return new sets and 
  // do not modify the old set. 
    
    
    // Need to figure out a way to rotate trees
    // TO DO: 
    // FIRST CREATE TESTING HMMMMPH. Property-based testing!
    // USE self-balancing binary search tree
    // Design API for bags; need for Iteration
    // Match Up code with tests
    // Write paper
    
    // The main methods we are changing are cardinality, member, add, remove
    // IMPLEMENT USING ITERATOR! How to create iterators?
    
    // For the iterator, we will need:
    // HasNext?
    // next()
    // previous() --> if we are fancy
    
    // Need "Tree Rotation"
    // Right rotation
    // Left rotation
   
   public PolymorphicSetBags(D root, Bag left, Bag right) {
        this.root = root;
        this.left = left;
        this.right = right;
    }

   public PolymorphicSetBags(D root) {
        // Setting Properties
        this.root = root;
        this.left = empty();
        this.right = empty();
    }
    
    
    
   // IMPLEMENTATION: 
   
    public static Bag empty() {
        return new PolymorphicSetBags_Empty();
    }
    
    // These will be implemented using AVI Trees and an iterator:
    
    public int cardinality() {
        // NEED TO IMPLEMENT
    }
    
    public  boolean isEmptyHuh() {
        return false;
    }
    
    
    // It errors when I do (Comparable elt) --> anyway to fix this?
    // Plus they need to be iterable and comparable.... You have to be
    // able to go through them AND compare them to each other so they 
    // can be in the right order. 
    public  boolean member(Object elt) {
        // NEED TO IMPLEMENT
    }
    
    public Bag remove (Object elt) {
        // NEED TO IMPLEMENT
    }
    
    public Bag add(Object elt) {
        // NEED TO IMPLEMENT
    }
    
    
    // None of these will change too much because they are all based on the
    // top main functions
    
    public Bag union(Bag u) {
         Bag bag = new PolymorphicSetBags(this.root, this.left,
                this.right);
        return u.union(left.union(right)).add(root);
    }
    
    public Bag inter(Bag u) {
      if (u.member(this.root)) {
            return new PolymorphicSetBags(this.root, this.left.inter(u),
                    this.right.inter(u));
        } else {
            return this.left.inter(u).union(this.right.inter(u));
        }
    }
    
    public Bag diff(Bag u) {
        Bag removed = u.remove(root);
        return (left.union(right)).diff(removed);
    }
    
    public Boolean equal (Bag u) {
        return (this.subset(u) && u.subset(this));
    }
    
    public Boolean subset (Bag u) {
        return (u.member(this.root) && this.left.subset(u)
                && this.right.subset(u));
    }

    
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
}

