
package polymorphicsetbags;
  
// REQUIREMENTS: 

import java.lang.reflect.Array;

   
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
    
    
    // Need "Tree Rotation"
    // Right rotation
    // Left rotation

public class SetBag_NonEmpty<D> implements Bag, Sequenced {
    
    int count; 
    D here;
    Bag left;
    Bag right;
   
   public SetBag_NonEmpty(D root, Bag left, Bag right) {
        this.count = 1;
        this.here = root;
        this.left = left;
        this.right = right;
    }

   public SetBag_NonEmpty(D root) {
        // Setting Properties
        this.count = 1; 
        this.here = root;
        this.left = empty();
        this.right = empty();
    }
    
    
    
   // IMPLEMENTATION: 
   
    public static Bag empty() {
        return new SetBag_Empty();
    }
    
    // These will be implemented using AVI Trees and an iterator:
    // We need something that puts this in a sequence. 
    
    public Sequence<D> seq() { 
        
    }
    
    public int cardinality() {
        // NEED TO IMPLEMENT
       
    }
    
    public  boolean isEmptyHuh() {
        return false;
    }
    
    
    public  boolean member(D elt) {
        // Check if count is 0 for that element
        // else
        // do what we did before
    }
    
    public Bag remove (D elt) {
        // Check if element is equal, remove -> subtract from count
    }
    
    // Remove everything or just one if there are duplicates
    
    public Bag removeAll (D elt) {
        // Check if equal, set count to 0
    }
    
    public Bag add(D elt) {
        // NEED TO ELEMENT
    }
    
    
    // None of these will change too much because they are all based on the
    // top main functions
    
    public Bag union(Bag u) {
         Bag bag = new SetBag_NonEmpty(this.root, this.left,
                this.right);
        return u.union(left.union(right)).add(root);
    }
    
    public Bag inter(Bag u) {
      if (u.member(this.root)) {
            return new SetBag_NonEmpty(this.root, this.left.inter(u),
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
        // HERE we run the tests....
    }
}

