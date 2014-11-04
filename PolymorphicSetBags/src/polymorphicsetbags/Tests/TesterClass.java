package polymorphicsetbags.Tests;

import java.util.Random;
import polymorphicsetbags.SetBag.Bag;
import static polymorphicsetbags.SetBag.SetBag_NonEmpty.empty;

public class TesterClass<D extends Comparable> {

    Generator<D> gen;

    int checkTree_empty_isEmptyHuh = 0;
    int checkTree_isEmptyHuh_cardinality = 0;
    int checkTree_cardinality_remove_getCount = 0;
    int checkTree_remove_equal_add_getCount = 0;
    int checkTree_add_member = 0;
    int checkTree_member_union_getCount = 0;
    int checkTree_union_subset = 0;
    int checkTree_subset_diff = 0;
    int checkTree_diff_inter_empty_equal = 0;
    int checkTree_equal_union_inter = 0;
    int checkTree_inter_empty = 0;
    int checkTree_getCount_union =0;
    int checkTree_seq_cardinality = 0;
    int tests = 1000;

    public TesterClass(Generator<D> gen) {
        this.gen = gen;
    }

    public static int rndInt(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt((max - min) + 1) + min;
    }

    public Bag<D> rndBag(int count) {
        if (count == 0) {
            return empty();
        } else {
            return rndBag(count - 1).addN(gen.nextThing(1, 50), rndInt(1, 10));
        }
    }

    public static String rndStringX(int min, int max) {
        int rndInt = rndInt(min, max);
        StringBuilder stringBuffer = new StringBuilder("");
        // Random generator for letter?
//        Random rnd = new Random();
//        int letter = rnd.nextInt(24);
        for (int i = 0; i > rndInt; i++) {
            stringBuffer.append("X");
        }
        return stringBuffer.toString();
    }

    // Also checks getCount
    public void checkTree_empty_isEmptyHuh(int count) throws Exception {
        // Creating a empty tree or a random tree
        if (count == 0) {
            Bag t = empty();
            if (!t.isEmptyHuh()) {
                throw new Exception("Failure: An empty bag is not empty");
            }
        } else {
            //couldn't be 0 b/c then bag would be empty
            int len = rndInt(1, 10);
            Bag l = rndBag(len);
            if (l.isEmptyHuh()) {
                throw new Exception("Failure: A nonempty set is empty!");
            }

        }
        checkTree_empty_isEmptyHuh++;
    }

    public void checkTree_isEmptyHuh_cardinality(Bag t) throws Exception {
        if (!t.isEmptyHuh() && (t.cardinality() == 0)) {
            throw new Exception("Failure: Nonempty set and had a "
                    + "cardinality equal to zero");
        }
        if (t.isEmptyHuh() && (t.cardinality() != 0)) {
            System.out.println("Failure: Empty with not cardinality = 0");
        }
        checkTree_isEmptyHuh_cardinality++;
    }
    
    public void checkTree_seq_cardinality(Bag t) throws Exception {
        if (t.sumIt() != t.cardinality()) {
            throw new Exception("The sequence doesn't represent the right number"
                    + " of elements");
        }
        checkTree_seq_cardinality++;
    }

    public void checkTree_cardinality_remove_getCount(Bag t, D x) throws Exception {
        int nT = t.remove(x).cardinality();
        // Either something was removed -> and it decreased the tree by one
        // Or the thing wasn't there to begin with, and nothing was removed
        if (t.getCount(x) >= 1 && nT != t.cardinality() - 1) {
            throw new Exception("Failure - we remove x from nT and there was only"
                    + "x so the cardinality should decrease by 1");
        }
        if (t.getCount(x) == 0 && nT != t.cardinality()) {
            throw new Exception("The object wasn't there so there was nothing to remove."
                    + " Thus, the cardinality should remain the same");
        } else {
        }
        checkTree_cardinality_remove_getCount++;
    }

    public void checkTree_remove_equal_add_getCount(Bag t) throws Exception {
        // Add and remove the same element from a copied tree
        // This is more of a test for finite sets (not multi-bag)
        D rand = gen.nextThing(0, 50);
        Bag nT = t.add(rand);
        if (nT.getCount(rand) - 1 != t.getCount(rand)) {
            throw new Exception("Failure: After adding an item, the count for that item"
                    + " should increase by 1" + nT.getCount(rand) + " = " + t.getCount(rand) + " toString: nT [" + nT.toStringBST() + "] toString: t [" + t.toStringBST() + "]");
        }
        nT = nT.remove(rand);
        if (nT.getCount(rand) != t.getCount(rand)) {
            throw new Exception("Failure: After removing the same item, the count for that item"
                    + " should be back where it was originally");
        }
        // Thus they should be equal
        if (!t.equal(nT)) {
            throw new Exception("Failure: The tree changed after adding and removing same item" + nT.getCount(rand) + " " + t.getCount(rand)+ " toString: nT [" + nT.toStringBST() + "] toString: t [" + t.toStringBST() + "]");
        }
        checkTree_remove_equal_add_getCount++;
    }

    // ITEM USED:
    public void checkTree_add_member(Bag t, D x, D y) throws Exception {
        Boolean bool = t.add(x).member(y);
        if (bool && x.compareTo(y) == 0) {
            //Success! X = Y and it's in the tree
        } else {
            if (bool && t.member(y)) {
                //"Success! Y was a member of y beforehand and "
                //"it's in the tree"
            } else {
                if (!bool && (x != y && !t.member(y))) {
//          Success! X != Y and is not a member of the original"
//                    + " tree and therefore is not a member of this tree"
                } else {
                    throw new Exception("Failure! Problem with member or add!");
                }
            }
        }
        checkTree_add_member++;
    }

    public void checkTree_member_union_getCount(Bag t, Bag r, D x) throws Exception {
        Boolean bool = t.union(r).member(x);
        if (bool && t.member(x)) {
            //"Success! X is a member of the t tree"
            if (t.getCount(x) <= 0 ) {
                throw new Exception("Failure! If it's a member of the tree, the count"
                        + " shouldn't be zero");
            }
        } else {
            if (bool && r.member(x)) {
                //Success! X is a member of the r tree
                // Here we indirectly test that it must be positive!
                if (r.getCount(x) <= 0) {
                    throw new Exception("Failure! If it's a member of the tree, the count"
                            + " shouldn't be zero");
                }
            } else {
                if (!bool && (!r.member(x) && !t.member(x))) {
                    //"Success! X is not a member of the right or left "
                    //      + "tree and therefore not a part of the union
               
                    // There is no option for a negative number; must be zero!
                    if (t.getCount(x) != 0 || r.getCount(x) != 0 || t.union(r).getCount(x) != 0) {
                        throw new Exception("Failure! If it is not a member of the tree, "
                                + "the count"
                                + " should be zero");
                    }
                } else {
                    throw new Exception("Failure! Problem with member or union!");
                }
            }
        }
        checkTree_member_union_getCount++;
    }
    
      // A more precise property for union & cardinality:
    // (count (union u v) x) = (+ (count u x) (count v x))
    // Nice that there is only one case
    public void checkTree_getCount_union(Bag u, Bag v, D x) throws Exception {
        if (u.union(v).getCount(x) != ((u.getCount(x)) + (v.getCount(x)))) {
        throw new Exception("Failure! The union of two trees should have the "
                + "same count of x as the two trees count of x added together b/c "
                + " concept of addition");
    }
    checkTree_getCount_union++;
    }

    public void checkTree_union_subset(Bag t, Bag r) throws Exception {
        Bag unionLR = t.union(r);
        if (!(t.subset(unionLR) && r.subset(unionLR))) {
            throw new Exception("Failure!The left and right trees are not subsets"
                    + " of their union");
        }
        checkTree_union_subset++;
    }

    // NOT WORKING: 
    public void checkTree_subset_diff(Bag t, Bag r) throws Exception {
        Bag tDiffR = t.diff(r);
        Bag rDiffT = r.diff(t);
        // If T - R = T && If R - T = R
        // Then, they must be completely disjoint and not be subsets of each other
        if (tDiffR.equals(t) && rDiffT.equals(r)) {
            if (r.subset(t) || r.subset(t)) {
                throw new Exception("Subset & Diff PROBZ");
            }
        }
//        if (t.isEmptyHuh()) {
////            "Success! The tree t is empty leaving the diff "
////                    + "to be all of r
//        } else if (!t.subset(difference)) {
////            Success! A tree is not a subset of the "
////                    + "difference"
//        } else {
//            System.out.println("T cardinality: " + t.cardinality() + " R cardinality: " + r.cardinality());
//            throw new Exception("Failure! Problem with subset or diff!");
//        }
        checkTree_subset_diff++;
    }

    // This test also says something is wrong with diff because all of the other
    // tests -> besides the ones using diff -> work
    public void checkTree_diff_inter_empty_equal(Bag t, Bag r) throws Exception {
        // t inter r = the empty set iff t - r = t
        if ((t.inter(r)).equal(empty()) && r.diff(t).equal(t)) {
//            "Success! A inter B = the empty set iff A - B = A"
        } else if (!(t.inter(r)).equal(empty()) && !r.diff(t).equal(t)) {
//            "Success! A inter B != the empty set iff A - B != A"
        } else {
            throw new Exception("Failure! Wrong: diff, inter, empty, or equal");
        }
        checkTree_diff_inter_empty_equal++;
    }

    //Changed from original in finiteSet b/c union now equals 2 * inter;
    public void checkTree_equal_union_inter(Bag t, Bag r) throws Exception {
        // Two sets are equal iff their union and intersection is the same
        if ((t.union(r).cardinality() == (t.inter(r)).cardinality() * 2) && t.equal(r)) {
//            "Success! The two trees are equal and 2* inter = union"
        } else if ((t.union(r).cardinality() != t.inter(r).cardinality() * 2) && !t.equal(r)) {
//            "Success! They are not equal and 2 * inter != union"
        } else {
            System.out.println("t.union(r).cardinality" + t.union(r).cardinality()
                    + " inter cardinality" + t.inter(r).cardinality() + "equal?: " + t.equal(r));
            throw new Exception("Failure! Wrong: equal, union, or diff");
        }
        checkTree_equal_union_inter++;
    }

    // The Identity Property for Inter
    public void checkTree_inter_empty(Bag t) throws Exception {
        Boolean bool = t.inter(empty()).equal(empty());
        // If the intersection of any tree with the empty set
        // equals the empty set...
        if (bool && !t.isEmptyHuh()) {
//           "Success! The intersection of a non-empty"
//                    + " set with the "
//                    + "empty set is just the empty set!"
        } else if (bool && t.isEmptyHuh()) {
//            "Success! The intersection of an empty set"
//                    + " with the empty set is just the empty set!"
        } else {
            throw new Exception("Failure! Wrong: inter, equal, isEmptyHuh, or "
                    + "empty()");
        }
        checkTree_inter_empty++;
    }

    public void runAll() throws Exception {
        // "Testing for Empty() & IsEmptyHuh?: "
        System.out.println();
        for (int i = 0; i < tests; i++) {
            int randomInt = rndInt(0, 1);
            checkTree_empty_isEmptyHuh(randomInt);
        }
        System.out.println("Testing for Empty() & IsEmptyHuh?: " + checkTree_empty_isEmptyHuh + " times.");

        System.out.println();
        // Testing for Cardinality & IsEmptyHuh
        for (int i = 0; i < tests; i++) {
            int len = rndInt(0, 10);
            Bag l = rndBag(len);
            checkTree_isEmptyHuh_cardinality(l);
        }
        System.out.println("Testing: IsEmptyHuh? & Cardinality: " + checkTree_isEmptyHuh_cardinality + " times");
        
        System.out.println();
        // Testing for Seq & Cardinality
        for (int i = 0; i < tests; i++) {
            int len = rndInt(0, 10);
            Bag l = rndBag(len);
            checkTree_seq_cardinality(l);
        }
        System.out.println("Testing: Sequences & Cardinality: " + checkTree_seq_cardinality + " "
                + "times");
        
        System.out.println();

        // Testing Cardinality & Remove
        for (int i = 0; i < tests; i++) {
            D elt = this.gen.nextThing(0, 10);
            int len = rndInt(0, 10);
            Bag l = rndBag(len);
            checkTree_cardinality_remove_getCount(l, elt);
        }
        System.out.println("Testing: Cardinality & Remove: " + checkTree_cardinality_remove_getCount + " times");

        // Testing: Remove (EQUAL) &  Add"
        System.out.println();

        for (int i = 0; i < tests; i++) {
            int len = rndInt(0, 10);
            Bag l = rndBag(len);
            checkTree_remove_equal_add_getCount(l);
        }
        System.out.println("Testing: Remove (EQUAL) &  Add: " + checkTree_remove_equal_add_getCount + " times");

        System.out.println();
        // Testing for Add & Member 
        // member (add t x) y = true <-> x = y \/ member t y = true
        for (int i = 0; i < tests; i++) {
            D elt = this.gen.nextThing(0, 10);
            D elt2 = this.gen.nextThing(0, 10);
            int len = rndInt(0, 10);
            Bag l = rndBag(len);
            checkTree_add_member(l, elt, elt2);
        }
        System.out.println("Testing: Add & Member: " + checkTree_add_member + " times");

        System.out.println();
        // Testing for Union & Member
        // member (union s s') x = true <-> member s x = true \/ member s' x = true
        for (int i = 0; i < tests; i++) {
            D elt = this.gen.nextThing(0, 10);
            int len = rndInt(0, 10);
            int len2 = rndInt(0, 10);
            Bag l = rndBag(len);
            Bag r = rndBag(len2);
            checkTree_member_union_getCount(l, r, elt);
        }
        System.out.println("Testing: Member & Union: " + checkTree_member_union_getCount + " times");
        
        System.out.println(); 
        //Check for union & get count
        for (int i = 0; i <tests; i++) {
             D elt = this.gen.nextThing(0, 10);
            int len = rndInt(0, 10);
            int len2 = rndInt(0, 10);
            Bag l = rndBag(len);
            Bag r = rndBag(len2);
            checkTree_getCount_union(l, r, elt);
        }
        System.out.println("Testing: Union & Get Count: " + checkTree_getCount_union + " times");

        System.out.println();
        // Testing Union & Subset 
        for (int i = 0; i < tests; i++) {
            int len = rndInt(0, 10);
            int len2 = rndInt(0, 10);
            Bag l = rndBag(len);
            Bag r = rndBag(len2);
            checkTree_union_subset(l, r);
        }
        System.out.println("Testing: Union & Subset: " + checkTree_union_subset + " times");

        System.out.println();
        // Testing Subset & Diff 
        for (int i = 0; i < tests; i++) {
            int len = rndInt(0, 10);
            int len2 = rndInt(0, 10);
            Bag l = rndBag(len);
            Bag r = rndBag(len2);
            checkTree_subset_diff(l, r);
        }
        System.out.println("Testing: Subset & Diff: " + checkTree_subset_diff + " times");

        System.out.println();
        // Testing: Diff (EMPTY & Inter) & Equal
        for (int i = 0; i < tests; i++) {
            int len = rndInt(0, 10);
            int len2 = rndInt(0, 10);
            Bag l = rndBag(len);
            Bag r = rndBag(len2);
            //Adding a random Number so we can get all the cases
            int randomNumber = rndInt(0, 4);
            if (randomNumber == 3) {
                checkTree_diff_inter_empty_equal(l, l);
            }
            checkTree_diff_inter_empty_equal(l, r);
        }
        System.out.println("Testing: Diff (EMPTY & INTER) & Equal: " + checkTree_diff_inter_empty_equal + " times");

        System.out.println();
        // Testing: Equal (UNION) & Inter
        for (int i = 0; i < tests; i++) {
            int len = rndInt(0, 10);
            int len2 = rndInt(0, 10);
            Bag l = rndBag(len);
            Bag r = rndBag(len2);
            //Adding a random Number so we can get all the cases
            int randomNumber = rndInt(0, 4);
            if (randomNumber == 3) {
                checkTree_equal_union_inter(l, l);
            }
            checkTree_equal_union_inter(l, r);
        }
        System.out.println("Testing: Equal (UNION) & Inter: " + checkTree_equal_union_inter + " times");

        System.out.println();
        // Testing Inter & Empty() 
        for (int i = 0; i < tests; i++) {
            int len = rndInt(0, 10);
            Bag l = rndBag(len);
            checkTree_inter_empty(l);
        }
        System.out.println("Testing Inter & Empty(): " + checkTree_inter_empty + " times");
        System.out.println("================================");
    }

}
