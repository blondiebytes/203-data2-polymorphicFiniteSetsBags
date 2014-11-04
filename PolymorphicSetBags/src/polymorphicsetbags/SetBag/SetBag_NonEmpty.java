package polymorphicsetbags.SetBag;

// REQUIREMENTS: 
import polymorphicsetbags.Sequence.Sequence;
import polymorphicsetbags.Sequence.Sequence_Cat;
import polymorphicsetbags.Sequence.Sequence_NonEmpty;
import polymorphicsetbags.Sequence.Sequenced;
        
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
           all.append(" ");
           as = as.next();
        }
        return all.toString();
    }
    
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

    public Bag remove(D elt) {
       return removeN(elt, 1);
    }


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
       return addN(elt, 1);
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
    
    
}
