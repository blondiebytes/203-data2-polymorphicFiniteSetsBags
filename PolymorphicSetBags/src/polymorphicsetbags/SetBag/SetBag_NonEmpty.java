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
        this.isRed = true;
    }

    public SetBag_NonEmpty(D root, Bag<D> left, Bag<D> right) {
        this.count = 1;
        this.root = root;
        this.left = left;
        this.isRed = true;
        this.right = right;
    }

    public SetBag_NonEmpty(D root, int count) {
        // Setting Properties
        this.isRed = true;
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
    
    public Bag<D> left() {
        return left;
    }
    
    public Bag<D> right() {
        return right;
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

//    public Bag add(D elt) {
//        return addN(elt, 1);
//    }
//
//    public Bag addN(D elt, int n) {
//        if (elt.compareTo(this.root) == 0) {
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
//    
    public Bag add(D elt) {
        return this.addN(elt, 1);
    }
    
    public Bag addN(D elt, int n) {
        return this.addInner(elt, n).blacken();
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

    // Balancing Methods
    public Bag<D> blacken() {
        return new SetBag_NonEmpty(this.root, this.count, false, this.left, this.right);
    }

    public boolean isRedHuh() {
        return isRed;
    }
 
    public SetBag_NonEmpty balance(){
        if(!this.isRedHuh()){
        try{   
            if(this.left.isRedHuh()&&this.left.left().isRedHuh()){
                SetBag_NonEmpty lef = (SetBag_NonEmpty)this.left;
                SetBag_NonEmpty lefgrand = (SetBag_NonEmpty)lef.left;
                return new SetBag_NonEmpty(lef.root,lef.count,true,
                                    lefgrand.blacken(),
                                    new SetBag_NonEmpty(this.root,this.count,false,
                                            lef.right,
                                            this.right));
            }
        }
        catch(Exception e){}
        try{if(this.right.isRedHuh()&&this.right.right().isRedHuh()){
                SetBag_NonEmpty rig = (SetBag_NonEmpty)this.right;
                SetBag_NonEmpty riggrand = (SetBag_NonEmpty)rig.right;
                return new SetBag_NonEmpty(rig.root,rig.count,true,
                                    new SetBag_NonEmpty(this.root,this.count,false,
                                            this.left,
                                            rig.left),
                                    riggrand.blacken());
            }
        }
        catch(Exception e){}
        try{if(this.left.isRedHuh()&&this.left.right().isRedHuh()){
                SetBag_NonEmpty lef = (SetBag_NonEmpty)this.left;
                SetBag_NonEmpty riggrand = (SetBag_NonEmpty)lef.right;
                return new SetBag_NonEmpty(riggrand.root,riggrand.count,true,
                                    new SetBag_NonEmpty(lef.root,lef.count,false,
                                            lef.left,
                                            riggrand.left),
                                    new SetBag_NonEmpty(this.root,this.count,false,
                                            riggrand.right,
                                            this.right));
            }
        }
        catch(Exception e){}
        try{if(this.right.isRedHuh()&&this.right.left().isRedHuh()){
                SetBag_NonEmpty rig = (SetBag_NonEmpty)this.right;
                SetBag_NonEmpty lefgrand = (SetBag_NonEmpty)rig.left;
                return new SetBag_NonEmpty(lefgrand.root,lefgrand.count,true,
                                    new SetBag_NonEmpty(this.root,this.count,false,
                                            this.left,
                                            lefgrand.left),
                                    new SetBag_NonEmpty(rig.root,rig.count,false,
                                            lefgrand.right,
                                            rig.right));
            }
        }
        catch(Exception e){}
        
        return this;
    }
        return this;
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
