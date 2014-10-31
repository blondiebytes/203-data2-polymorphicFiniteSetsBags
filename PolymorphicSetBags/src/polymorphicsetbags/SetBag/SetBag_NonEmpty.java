package polymorphicsetbags.SetBag;

// REQUIREMENTS: 
import polymorphicsetbags.FakeBag.FakeBag;
import polymorphicsetbags.FakeBag.FakeSetBag_3;
import polymorphicsetbags.FakeBag.FakeSetBag_L;
import polymorphicsetbags.Sequence.Sequence;
import polymorphicsetbags.Sequence.Sequence_Empty;

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

//    public Bag add(D elt) {
//        if (elt.equals(this.root)) {
//            return new SetBag_NonEmpty(this.root, this.count + 1, this.left, this.right);
//        } else {
//            if (elt.compareTo(this.root) < 0) {
//                return new SetBag_NonEmpty(this.root, this.count, this.left.add(elt), this.right);
//            } // If the root is less than the element, then add it to the 
//            // right tree
//            else {
//                return new SetBag_NonEmpty(this.root, this.count, this.left, this.right.add(elt));
//            }
//        }
//    }
//
//    public Bag addN(D elt, int n) {
//        if (elt.equals(this.root)) {
//            int max = Math.max(0, this.count + n);
//            return new SetBag_NonEmpty(this.root, max, this.left, this.right);
//        } else {
//            if (elt.compareTo(this.root) < 0) {
//                return new SetBag_NonEmpty(this.root, this.count, this.left.addN(elt, n), this.right);
//            } else {
//                return new SetBag_NonEmpty(this.root, this.count, this.left, this.right.addN(elt, n));
//            }
//        }
//    }

    public Bag<D> smartInsert(D key) {
        return smartInsertN(key, 1);
    }

    public Bag<D> smartInsertN(D key, int value) {
         return smartInsertStep1(key, value).smartInsertStep2();
    }

    public FakeBag<D> smartInsertStep1(D key, int value) {
        if (this.root.compareTo(key) == 0) {
            return fake2(this.left, this.root, this.getCount(root) + value, this.right);
        } else if (key.compareTo(this.root) > 0) {
            return fake2(this.left.smartInsertStep1(key, value),
                    this.root, this.getCount(root), this.right);
        } else {
            return fake2(this.left, this.root, this.getCount(root), this.right.smartInsertStep1(key, value));
        }
    }

    public Bag<D> smartInsertStep2() {
        return this;
    }
    
    public FakeBag<D> fake1() {
        return new SetBag_1(this);
    }
    
    public FakeBag<D> fake2(FakeBag<D> left, D key, int value, FakeBag<D> right) {
//       
//      [((fake:L k1 v1) k2 v2 t1)
//       (fake:3 (T:0) k1 v1 (T:0) k2 v2 t1)]
        if (left instanceof FakeSetBag_L) {
            FakeSetBag_L<D> tl = (FakeSetBag_L) left;
            //Somehow cast this.left as FakeSetBag_L
            return new FakeSetBag_3<D>(empty(), tl.key,
                    tl.value, empty(),
                    key, value, (Bag<D>) right);
        } //       [((fake:3 t1 k1 v1 t2 k2 v2 t3) k3 v3 (T:1 t4))
        //       (T:2 (T:2 t1 k1 v1 t2) k2 v2 (T:2 t3 k3 v3 t4))]
        else if ((left instanceof FakeSetBag_3) && (right instanceof SetBag_1)) {
            FakeSetBag_3<D> tl = (FakeSetBag_3) left;
            SetBag_1<D> tr = (SetBag_1) right;
            // Somehow cast this.right as SetBag_1
            return new SetBag_NonEmpty(
                    tl.keyTwo,
                    tl.valueTwo,
                    new SetBag_NonEmpty(tl.keyOne, tl.valueOne,
                            tl.leftTree, tl.middleTree),
                    new SetBag_NonEmpty(
                            key, value, tl.rightTree,
                            tr.next)
            );
//      [((fake:3 t1 k1 v1 t2 k2 v2 t3) k3 v3 (? T:2? t4))
//       (fake:3 (T:2 t1 k1 v1 t2) k2 v2 (T:1 t3) k3 v3 t4)]
        } else if ((left instanceof FakeSetBag_3)
                && (right instanceof SetBag_NonEmpty)) {
            FakeSetBag_3<D> tl = (FakeSetBag_3) left;
            SetBag_NonEmpty<D> tr = (SetBag_NonEmpty) right;

            return new FakeSetBag_3(new SetBag_NonEmpty(
                    tl.keyOne, tl.valueOne, tl.leftTree, tl.middleTree),
                    tl.keyTwo, tl.valueTwo, new SetBag_1(tl.rightTree),
                    key, value, tr);
        }
        //      [(t1 k1 v1 (fake:L k2 v2))
        //       (fake:3 t1 k1 v1 (T:0) k2 v2 (T:0))]
        //      Cast this.right as FakeSetBag_L
        else if (right instanceof FakeSetBag_L) {
            FakeSetBag_L<D> tr = (FakeSetBag_L) right;
            return new FakeSetBag_3((Bag<D>)left, key,
                    value, empty(),
                    tr.key,
                    tr.value, empty());
        } 
        //      [((T:1 t1) k1 v1 (fake:3 t2 k2 v2 t3 k3 v3 t4))
        //       (T:2 (T:2 t1 k1 v1 t2) k2 v2 (T:2 t3 k3 v3 t4))]
        else if ((left instanceof SetBag_1) && right instanceof FakeSetBag_3) {
               SetBag_1<D> tl = (SetBag_1) left;
               FakeSetBag_3 tr = (FakeSetBag_3) right;
            return new SetBag_NonEmpty( tr.keyOne, tr.valueOne, 
                    new SetBag_NonEmpty(key, value, tl.next, tr.leftTree), 
                    new SetBag_NonEmpty(tr.keyTwo, tr.valueTwo, tr.middleTree, tr.rightTree));
        } 
        //      [((? T:2? t1) k1 v1 (fake:3 t2 k2 v2 t3 k3 v3 t4))
        //       (fake:3 L K V (T:1 t2) k2 v2 (T:2 t3 k3 v3 t4))]
        else if (left instanceof SetBag_NonEmpty && right instanceof FakeSetBag_3) {
            SetBag_NonEmpty<D> tl = (SetBag_NonEmpty) left;
            FakeSetBag_3 tr = (FakeSetBag_3) right;
            return new FakeSetBag_3(tl, key, value,
                    new SetBag_1(tr.leftTree), tr.keyOne,
                    tr.valueOne, new SetBag_NonEmpty(tr.keyTwo, tr.valueTwo,
                            tr.middleTree, tr.rightTree));
        } //      [(t1 k1 v1 t2)
        //       (T:2 t1 k1 v1 t2)]))
        else {
            return new SetBag_NonEmpty(key, value, (Bag<D>)left, (Bag<D>)right);
        }
    }

    public Bag union(Bag u) {
        return u.union(left.union(right)).smartInsertN(root, this.getCount(root));
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
