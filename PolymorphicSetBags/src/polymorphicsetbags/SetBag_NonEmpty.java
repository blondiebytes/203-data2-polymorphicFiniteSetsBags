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

    public SetBag_NonEmpty(D root) {
        // Setting Properties
        this.count = 1;
        this.root = root;
        this.left = empty();
        this.right = empty();
    }

    // We need something that puts this in a sequence. 
    public Sequence<D> seq() {
        return new Sequence_Empty();
    }
    
   // IMPLEMENTATION: 
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
        } else if (elt.compareTo(this.root) == -1) {
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
        } else if (elt.compareTo(this.root) == -1) {
            return new SetBag_NonEmpty(this.root, this.left.remove(elt), this.right);
        } else {
            return new SetBag_NonEmpty(this.root, this.left,
                    this.right.remove(elt));
        }
    }


    public Bag removeAll(D elt) {
        if (elt.equals(this.root)) {
            return new SetBag_NonEmpty(this.root, 0,
                    this.left, this.right);
        } else if (elt.compareTo(this.root) == -1) {
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
            if (elt.compareTo(this.root) == -1) {
                return new SetBag_NonEmpty(this.root, this.left.add(elt), this.right);
            } // If the root is less than the element, then add it to the 
            // right tree
            else {
                return new SetBag_NonEmpty(this.root, this.left, this.right.add(elt));
        }
    }
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
        Bag removed = u.removeAll(root);
        return (left.union(right)).diff(removed);
    }

    public Boolean equal(Bag u) {
        return (this.subset(u) && u.subset(this));
    }

    public Boolean subset(Bag u) {
        return (u.member(this.root) && this.left.subset(u)
                && this.right.subset(u));
    }

    public static void main(String[] args) {
        // HERE we run the tests....
    }
}
