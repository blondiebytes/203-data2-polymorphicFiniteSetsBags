package polymorphicsetbags.SetBag;

// REQUIREMENTS: 
import polymorphicsetbags.Sequence.Sequence;
import polymorphicsetbags.Sequence.Sequence_Cat;
import polymorphicsetbags.Sequence.Sequence_Empty;
import polymorphicsetbags.Sequence.Sequence_NonEmpty;
import polymorphicsetbags.Sequence.Sequenced;

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
public class SetBag_NonEmpty<D extends Comparable> implements Bag<D>, Sequenced<D> {

    int count;
    D root;
    Bag<D> left;
    Bag<D> right;

    public SetBag_NonEmpty(D root, int count, Bag<D> left, Bag<D> right) {
        this.count = count;
        this.root = root;
        this.left = left;
        this.right = right;
    }

    public SetBag_NonEmpty(D root, Bag<D> left, Bag<D> right) {
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
        return new Sequence_NonEmpty(root, count, (new Sequence_Cat(left.seq(),right.seq())));
    }

   // IMPLEMENTATION OF SEQUENCES: 
    public int sumIt () {
        return sumItS(this.seq());
    }
    
    public int sumItS(Sequence<D> as) {
        int sum = 0;
        while (as.hasNext()) {
            sum = sum + 1;
            as = as.next();
        }
        return sum;
    }
    
    public String stringIt() {
        return stringItS(this.seq());      
    }
    
    public String stringItS(Sequence<D> as) {
        StringBuffer all = new StringBuffer("");
        while (as.hasNext()) {
           all.append(as.next().toStringSequence());
           all.append("     ");
           as = as.next();
        }
        return all.toString();
    }
   
    
    // NEED TO WRITE TESTS
    
    
    public static Bag empty() {
        return new SetBag_Empty();
    }

    public int cardinality() {
        return count + left.cardinality() + right.cardinality();

    }

    public boolean isEmptyHuh() {
        if (this.getCount(root) == 0) {
            //now check left an right trees
            if (!left.isEmptyHuh()) {
                return right.isEmptyHuh();
            } else {
                return left.isEmptyHuh();
            }
        } else {
            return false;
        }
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
            return new SetBag_NonEmpty(this.root, this.count, this.left.remove(elt), this.right);
        } else {
            return new SetBag_NonEmpty(this.root, this.count, this.left,
                    this.right.remove(elt));
        }
    }

    // Not working!
    public Bag removeN(D elt, int n) {
        if (elt.equals(this.root)) {
            int max = Math.max(0, this.count - n);
            return new SetBag_NonEmpty(this.root, max, this.left, this.right);
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
            return new SetBag_NonEmpty(this.root, this.left.removeAll(elt), this.right);
        } else {
            return new SetBag_NonEmpty(this.root, this.left,
                    this.right.removeAll(elt));
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

    public Bag addN(D elt, int n) {
        if (elt.equals(this.root)) {
            int max = Math.max(0, this.count + n);
            return new SetBag_NonEmpty(this.root, max, this.left, this.right);
        } else {
            if (elt.compareTo(this.root) < 0) {
                return new SetBag_NonEmpty(this.root, this.count, this.left.addN(elt, n), this.right);
            } else {
                return new SetBag_NonEmpty(this.root, this.count, this.left, this.right.addN(elt, n));
            }
        }
    }

     public Bag rotate(boolean toRight) {
        if (toRight) {
            if (!left.isEmptyHuh()) {
                SetBag_NonEmpty tl = (SetBag_NonEmpty) left;
                return new SetBag_NonEmpty(tl.root, tl.count, tl.left, new SetBag_NonEmpty(this.root, this.count, right, tl.right));
            } else {
                throw new RuntimeException("AVLTree cannot rotate in that direction!");
            }
        } else {
            if (!right.isEmptyHuh()) {
                SetBag_NonEmpty tr = (SetBag_NonEmpty) right;
                return new SetBag_NonEmpty(tr.root, tr.count, new SetBag_NonEmpty(root, count,left,tr.left), tr.right);
            } else {
                throw new RuntimeException("AVLTree cannot rotate in that direction!");
            }
        }
    }
    
  
    public Bag union(Bag u) {
        return u.union(left.union(right)).addN(root, this.getCount(root));
    }

    public Bag inter(Bag u) {
        if (u.member(this.root)) {
            if (u.getCount(root) > this.getCount(root)) {
                return new SetBag_NonEmpty(this.root, this.getCount(root),
                        this.left.inter(u), this.right.inter(u));
            } else {
                return new SetBag_NonEmpty(this.root, u.getCount(root),
                        this.left.inter(u), this.right.inter(u));
            }
        } else {
            return this.left.inter(u).union(this.right.inter(u));
        }
    }

    public Bag diff(Bag u) {
        // U - this
        // 6 - 5 --> subtract how many in this
        Bag removed = u.removeN(root, this.getCount(root));
        return (left.union(right)).diff(removed);
    }

    public boolean equal(Bag u) {
        return (this.subset(u) && u.subset(this));
    }

    public boolean subset(Bag u) {
        return (u.getCount(root) >= this.getCount(root)) && this.left.subset(u)
                && this.right.subset(u);
    }
    
    public String toStringBST() {
        return "[SB:2 Left: "+ this.left.toStringBST() + " Right: " 
                + this.right.toStringBST() + "Root: " + this.root + " Value: " + this.getCount(root) + "]";
    }
    
      // Another way to balance
//     public Bag balance(Bag tree) {
//        if (!tree.isEmptyHuh()) {
//            SetBag_NonEmpty<D> oldTree = (SetBag_NonEmpty) tree;
//            SetBag_NonEmpty newSet = new SetBag_NonEmpty(oldTree.root, oldTree.count, balance(oldTree.left), balance(oldTree.right));
//            if ((newSet.left.cardinality() - newSet.right.cardinality()) > 1) {
//                //left is overweight
//                if (newSet.left.left.cardinality() > newSet.left.right.cardinality()) {
//                    //left left case
//                    newSet = newSet.rotate(true);
//                } else {
//                    //left right case
//                    newSet = new SetBag_NonEmpty (newSet.root, newSet.count, 
//                            newSet.left.rotate(false)), newSet.right.rotatenewSet.getRight(),
//                                         newSet.getLeft().rotate(false),
//                                         newSet.getData()).rotate(true);
//                }
//            } else if (newSet.getLeft().height() - newSet.getRight().height() < -1) {
//                //right is overweight
//                if (newSet.getRight().getRight().height() > newSet.getRight().getLeft().height()) {
//                    //right right case
//                    newSet = newSet.rotate(false);
//                } else {
//                    //right left case
//                    newSet = new AVLTree(newSet.getRight().rotate(true),
//                                         newSet.getLeft(),
//                                         newSet.getData()).rotate(false);
//                }
//            }
//            return newSet;
//        } else {
//            return tree;
//        }
//    }
    
    
    
    
}
