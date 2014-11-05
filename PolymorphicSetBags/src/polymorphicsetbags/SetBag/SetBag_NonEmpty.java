package polymorphicsetbags.SetBag;

// REQUIREMENTS: 
import polymorphicsetbags.Sequence.Sequence;
import polymorphicsetbags.Sequence.Sequence_Cat;
import polymorphicsetbags.Sequence.Sequence_NonEmpty;
import polymorphicsetbags.Sequence.Sequenced;

public class SetBag_NonEmpty<D extends Comparable> implements Bag<D>, Sequenced<D> {

    boolean isRed;
    int count;
    D root;
    Bag<D> left;
    Bag<D> right;

    public SetBag_NonEmpty(D root, int count, boolean isRed, Bag<D> left, Bag<D> right) {
        this.count = count;
        this.root = root;
        this.left = left;
        this.right = right;
        this.isRed = isRed;
    }

    public SetBag_NonEmpty(D root, int count, Bag<D> left, Bag<D> right) {
        this.count = count;
        this.root = root;
        this.left = left;
        this.right = right;
        this.isRed = false;
    }

    public SetBag_NonEmpty(D root, Bag<D> left, Bag<D> right) {
        this.count = 1;
        this.root = root;
        this.left = left;
        this.isRed = false;
        this.right = right;
    }

    public SetBag_NonEmpty(D root, int count) {
        // Setting Properties
        this.isRed = false;
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
        this.isRed = false;
    }

    public int getCount(D elt) {
        if (elt.compareTo(this.root) == 0) {
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
        return new Sequence_NonEmpty(root, count, (new Sequence_Cat(left.seq(), right.seq())));
    }

    // IMPLEMENTATION OF SEQUENCES: 
    public int sumIt() {
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
        return this.getCount(root) == 0 && left.isEmptyHuh() && right.isEmptyHuh();

    }

    public boolean member(D elt) {
        return this.getCount(elt) != 0;
    }

    public Bag remove(D elt) {
        return removeN(elt, 1);
    }

    public Bag removeN(D elt, int n) {
        if (elt.compareTo(this.root) == 0) {
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
        return removeN(elt, this.getCount(elt));
    }

    public Bag add(D elt) {
        return addN(elt, 1);
    }

    public Bag addN(D elt, int n) {
        if (elt.compareTo(this.root) == 0) {
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
//    
//    public Bag add(D elt) {
//        return this.addN(elt, 1);
//    }
//
//    public Bag addN(D elt, int n) {
//        return this.addInner(elt, n).blacken();
//    }

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

    // Balancing Methods

    public boolean isRedHuh() {
        return isRed;
    }

    public SetBag_NonEmpty balance() {

        if (!isRedHuh()
                && this.left instanceof SetBag_NonEmpty
                && ((SetBag_NonEmpty) this.left).left instanceof SetBag_NonEmpty
                && ((SetBag_NonEmpty) this.left).isRed
                && ((SetBag_NonEmpty) ((SetBag_NonEmpty) this.left).left).isRed) {
      // left.color is red, left.left.color is red

            SetBag_NonEmpty L1 = ((SetBag_NonEmpty) this.left);
            SetBag_NonEmpty L2 = ((SetBag_NonEmpty) L1.left);

            //System.out.println("Case 1");
            return new SetBag_NonEmpty(/*this.left.root*/L1.root,
                    /*this.left.count*/ L1.count, true,
                    new SetBag_NonEmpty(/*this.left.left.root*/L2.root,
                            /*this.left.left.count*/ L2.count,
                            false,
                            /*this.left.left.left*/ L2.left,
                            /*this.left.left.right*/ L2.right),
                    new SetBag_NonEmpty(this.root,
                            this.count,
                            false,
                            /*this.left.right*/ L1.right,
                            this.right));
        } else if (!isRed
                && this.left instanceof SetBag_NonEmpty
                && ((SetBag_NonEmpty) this.left).right instanceof SetBag_NonEmpty
                && ((SetBag_NonEmpty) this.left).isRed
                && ((SetBag_NonEmpty) ((SetBag_NonEmpty) this.left).right).isRed) { // left.color is red, left.right.color is red

            SetBag_NonEmpty L1 = ((SetBag_NonEmpty) this.left);
            SetBag_NonEmpty LR = ((SetBag_NonEmpty) L1.right);

      //System.out.println("Case 2");
            return new SetBag_NonEmpty(/*this.left.right.root*/LR.root,
                    /*this.left.right.count*/ LR.count,
                    true,
                    new SetBag_NonEmpty(
                            /*this.left.root*/L1.root,
                            /*this.left.count*/ L1.count,
                            false,
                            /*this.left.left*/ L1.left,
                            /*this.left.right.left*/ LR.left),
                    new SetBag_NonEmpty(this.root,
                            this.count,
                            false,
                            /*this.left.right.right*/ LR.right,
                            this.right));
        } else if (!isRed
                && this.right instanceof SetBag_NonEmpty
                && ((SetBag_NonEmpty) this.right).left instanceof SetBag_NonEmpty
                && ((SetBag_NonEmpty) this.right).isRed
                && ((SetBag_NonEmpty) ((SetBag_NonEmpty) this.right).left).isRed) {

            SetBag_NonEmpty R1 = ((SetBag_NonEmpty) this.right);
            SetBag_NonEmpty RL = ((SetBag_NonEmpty) R1.left);

      //System.out.println("Case 3");
            // right.left.color is red
            return new SetBag_NonEmpty( /*this.right.left*/RL.root,
                    /*this.right.left*/ RL.count,
                    true,
                    new SetBag_NonEmpty(this.root,
                            this.count,
                            false,
                            this.left,
                            /*this.right.left*/ RL.left),
                    new SetBag_NonEmpty(/*this.right*/R1.root,
                            /*this.right*/ R1.count,
                            false,
                            /*this.right.left*/ RL.right,
                            /*this.right*/ R1.right));
        } else if (!isRed
                && this.right instanceof SetBag_NonEmpty
                && ((SetBag_NonEmpty) this.right).right instanceof SetBag_NonEmpty
                && ((SetBag_NonEmpty) this.right).isRed
                && ((SetBag_NonEmpty) ((SetBag_NonEmpty) this.right).right).isRed) {

            SetBag_NonEmpty R1 = ((SetBag_NonEmpty) this.right);
            SetBag_NonEmpty R2 = ((SetBag_NonEmpty) R1.right);

      //System.out.println("Case 4");
            // right.right.color is red
            return new SetBag_NonEmpty(/*this.right*/R1.root,
                    /*this.right*/ R1.count,
                    true,
                    new SetBag_NonEmpty(this.root,
                            this.count,
                            false,
                            this.left,
                            /*this.right.left*/ R1.left),
                    new SetBag_NonEmpty( /*this.right.right*/R2.root,
                            /*this.right.right*/ R2.count,
                            false,
                            /*this.right.right*/ R2.left,
                            /*this.right.right*/ R2.right));
        } else {
            //System.out.println("Case 5");
            return this;
        }
    }

    public Bag<D> addInner(D elt, int n) {
        if (elt.compareTo(this.root) == 0) {
            return new SetBag_NonEmpty(this.root, this.count + n, this.isRed, this.left, this.right);
        } else if (elt.compareTo(this.root) < 0) {
            return new SetBag_NonEmpty(this.root, this.count, this.isRed, this.left.addInner(elt, n), this.right).balance();
        } else {
            return new SetBag_NonEmpty(this.root, this.count, this.isRed, this.left, this.right.addInner(elt, n)).balance();
        }
    }

}
