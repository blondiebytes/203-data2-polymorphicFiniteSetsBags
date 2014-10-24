package polymorphicsetbags;

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
    // Need "Tree Rotation"
// Right rotation
// Left rotation

public class SetBag_NonEmpty<D extends Comparable> implements Bag<D> {

    int count;
    D root;
    Bag left;
    Bag right;

    public SetBag_NonEmpty(D root, int count, Bag left, Bag right) {
        this.count = count;
        this.root = root;
        this.left = left;
        this.right = right;
    }

    public SetBag_NonEmpty(D root, Bag left, Bag right) {
        this.count = 1;
        this.root = root;
        this.left = left;
        this.right = right;
    }
    
    public SetBag_NonEmpty(D root, int count) {
        // Setting Properties
        this.count = count;
        this.root = root;
        this.left = empty();
        this.right = empty();
    }
    
    public SetBag_NonEmpty(D root) {
        // Setting Properties
        this.count = 1;
        this.root = root;
        this.left = empty();
        this.right = empty();
    }
    
    public int getCount(D elt) {
        if (elt.equals(this.root)) {
            return count;
        } else {
            if (elt.compareTo(root) < 0) {
                return this.left.getCount(elt);
            } else {
                return this.right.getCount(elt);
            }
            
    }
    }

    // We need something that puts this in a sequence. 
    public Sequence<D> seq() {
        return new Sequence_Empty();
    }
    
   // IMPLEMENTATION: 
    
    // NEED TO WRITE TESTS
    public static Bag empty() {
        return new SetBag_Empty();
    }

    public int cardinality() {
        return count + left.cardinality() + right.cardinality();

    }

    public boolean isEmptyHuh() {
        return false;
    }

    public boolean member(D elt) {
        if (elt.equals(this.root)) {
            return this.count != 0;
        } else if (elt.compareTo(this.root) < 0) {
            return this.left.member(elt);
        } else {
            return this.right.member(elt);
        }
    }

        // Check if count is 0 for that element
    // else do what we did before
    public Bag remove(D elt) {
        if (elt.equals(this.root)) {
            return new SetBag_NonEmpty(this.root, this.count - 1,
                    this.left, this.right);
        } else if (elt.compareTo(this.root) < 0) {
            return new SetBag_NonEmpty(this.root, this.left.remove(elt), this.right);
        } else {
            return new SetBag_NonEmpty(this.root, this.left,
                    this.right.remove(elt));
        }
    }

    public Bag removeN(D elt, int n) {
        if (elt.equals(this.root)) {
          int max = Math.max(0, this.count - n);
          return new SetBag_NonEmpty(this.root, this.count - max, this.left, this.right);
        } else {
            if (elt.compareTo(this.root) < 0) {
                return new SetBag_NonEmpty(this.root, this.count, this.left.removeN(elt, n), this.right);
            } else {
                return new SetBag_NonEmpty(this.root, this.count, this.left, this.right.removeN(elt, n));
            }
        }
    }
    
    public Bag removeAll(D elt) {
        if (elt.equals(this.root)) {
            return new SetBag_NonEmpty(this.root, 0,
                    this.left, this.right);
        } else if (elt.compareTo(this.root) < 0) {
            return new SetBag_NonEmpty(this.root, this.left.remove(elt), this.right);
        } else {
            return new SetBag_NonEmpty(this.root, this.left,
                    this.right.remove(elt));
        }
    }


    public Bag add(D elt) {
        if (elt.equals(this.root)) {
            return new SetBag_NonEmpty(this.root, this.count + 1, this.left, this.right);
        } else {
            if (elt.compareTo(this.root) < 0) {
                return new SetBag_NonEmpty(this.root, this.count, this.left.add(elt), this.right);
            } // If the root is less than the element, then add it to the 
            // right tree
            else {
                return new SetBag_NonEmpty(this.root, this.count, this.left, this.right.add(elt));
        }
      }
    }

    public Bag addN (D elt, int n) {
        if (elt.equals(this.root)) {
            int max = Math.max(0, this.count - n);
            return new SetBag_NonEmpty(this.root, this.count + max, this.left, this.right);
        } else {
            if (elt.compareTo(this.root) < 0) {
                return new SetBag_NonEmpty(this.root, this.count, this.left.addN(elt, n), this.right);
            } else {
                return new SetBag_NonEmpty(this.root, this.count, this.left, this.right.addN(elt, n));
            }
        }
    }
    
    // Not Working
    public Bag union(Bag u) {
        return u.union(left.union(right)).addN(root, this.getCount(root) + u.getCount(root));
    }

    // Not Working
    public Bag inter(Bag u) {
        if (u.member(this.root)) {
            if (u.getCount(root) > this.getCount(root)) {
                return new SetBag_NonEmpty(this.root, this.getCount(root), 
                        this.left.inter(u), this.right.inter(u));
            } else {
                  return new SetBag_NonEmpty(this.root, u.getCount(root), 
                          this.left.inter(u),this.right.inter(u));
            }
        } else {
            return this.left.inter(u).union(this.right.inter(u));
        }
    }

    // Not Working
    public Bag diff(Bag u) {
        // U - this
        // 6 - 5 --> subtract how many in this
        Bag removed = u.removeN(root, this.getCount(root));
        return (left.union(right)).diff(removed);
    }

    public Boolean equal(Bag u) {
        return (this.subset(u) && u.subset(this));
    }

    public Boolean subset(Bag u) {
        return (u.getCount(root) >= this.getCount(root)) && this.left.subset(u)
                && this.right.subset(u);
    }

    public static void main(String[] args) {
        // HERE we run the tests....
        Bag bag = new SetBag_NonEmpty(5, 3, new SetBag_NonEmpty(3, 1, empty(), empty()), new SetBag_NonEmpty(7, 2, empty(), empty()));
        System.out.println("Cardinality: We should have 6 things = " + bag.cardinality());
        System.out.println("Cardinality & Remove: We should have 5 things = " + bag.remove(5).cardinality());
        System.out.println("Cardinality & Add: We should have 7 things = " + bag.add(5).cardinality());
        System.out.println("Cardinality & Add: We should have 7 things = " + bag.add(4).cardinality());
        System.out.println("Get Count & Remove: 2 = " + bag.remove(5).getCount(5));
        System.out.println("Get Count: 3 = " + bag.getCount(5));
        System.out.println("MemberNOT = " + bag.member(0));
        System.out.println("MemberYES = " + bag.member(5));
        System.out.println("Member & Add YES =" + bag.add(4).member(4));
        System.out.println("Equal = true = " + bag.equal(bag));
        System.out.println("Equal = false = " + bag.equal(bag.remove(5)));
        System.out.println("Subset = true = " + bag.remove(5).subset(bag));
        System.out.println("Subset = false = " + bag.subset(bag.remove(5)));
  
        System.out.println("Inter & equal: Bag inter empty() = empty() = true  = " + bag.inter(empty()).equal(empty()));
        System.out.println("Inter & equal:  empty() inter empty() = empty() true = " + empty().inter(empty()).equal(empty()));
        
        System.out.println("Union & equal: empty() U bag = bag | true = " + empty().union(bag).equal(bag));
       System.out.println("Union & equal: bag U empty() = bag | true = " + (bag.union(empty())).equal(bag));
        System.out.println("Union & equal: bag U empty() -> bag.cardinality " + bag.cardinality() +" = union.cardinality " + bag.union(empty()).cardinality());
        System.out.println("Inter & equal: bag inter bag = bag | true = " + bag.inter(bag).equal(bag));
        System.out.println("Inter & equal: bag(without 5) inter bag = bag(without 5) | true = " + bag.remove(5).inter(bag).equal(bag.remove(5)));
        
        
        System.out.println();
        // Not working
        System.out.println("Union & equal: bag U bag = bag | false = " + bag.union(bag).equal(bag));
               System.out.println("Union & equal: bag U bag = bag | cardinality should be doubled = 2x " + "U:" + 
                       bag.union(bag).cardinality() + "bag " + bag.cardinality());
        System.out.println("Difference: Bag - bag = bag | false = " + bag.diff(bag).equal(bag));
        System.out.println("Difference: Bag - bag = empty() | true = " + bag.diff(bag).equal(empty()));
     
    }
}
