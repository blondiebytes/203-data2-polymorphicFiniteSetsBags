package polymorphicsetbags;

import java.util.Random;
import static polymorphicsetbags.SetBag_NonEmpty.empty;


//testerInt = new TesterClass<Int>(new IntGen());
//testerInt.runAll();

public class TesterClass<D extends Comparable> {
    Generator<D> gen;

    // Tests to Think About:
    // When you add a new thing, the cardinality should increase
    // When you add something that is already in the list, the cardinality 
    // should still increase
    // When you remove something that is duplicated in the list, the cardinality
    // should be one less (it should only remove one of the duplicated things)
    // Count should always be positive
    
    // A more precise property for union & cardinality:
    // (count (union u v) x) = (+ (count u x) (count v x))
    
    
    //TESTING MULTI-BAG:
    
    // HOW CAN WE TEST WITH OTHER DATA TYPES?!??!?!?!? -> aka not int
    
    static int checkTree_empty_isEmptyHuh = 0;
    static int checkTree_isEmptyHuh_cardinality = 0;
    static int checkTree_cardinality_remove = 0;
    static int checkTree_remove_equal_add = 0;
    static int checkTree_add_member = 0;
    static int checkTree_member_union = 0;
    static int checkTree_union_subset = 0;
    static int checkTree_subset_diff = 0;
    static int checkTree_diff_inter_empty_equal = 0;
    static int checkTree_equal_union_inter = 0;
    static int checkTree_inter_empty = 0;

    public static int rndInt(int min,int max) {
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

    // ITEM USED:
    public void checkTree_cardinality_remove(Bag t, D x) throws Exception {
        int nT = t.remove(x).cardinality();
        // Either something was removed -> and it decreased the tree by one
        // Or the thing wasn't there to begin with, and nothing was removed
        if (nT == (t.cardinality() - 1) || nT == t.cardinality()) {

        } else {
            throw new Exception("Failure - the remove and/or cardinality"
                    + " function failed :( ");
        }
        checkTree_cardinality_remove++;
    }

    public void checkTree_remove_equal_add(Bag t) throws Exception {
        // Add and remove the same element from a copied tree that 
        // does not have the element already
        // This is more of a test for finite sets (not multi-bag)
        D rand = gen.nextThing(51, 100);
        Bag nT = t.add(rand);
        nT = nT.remove(rand);
             // If the tree we messed with is the same as the original tree
        // then we are correct!
        if (!t.equal(nT)) {
            throw new Exception("Failure: The tree changed after adding and removing same item");
        }
        checkTree_remove_equal_add++;
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

    // ITEM ADDED
    public void checkTree_member_union(Bag t, Bag r, D x) throws Exception {
        Boolean bool = t.union(r).member(x);
        if (bool && t.member(x)) {
            //"Success! X is a member of the t tree"
        } else {
            if (bool && r.member(x)) {
                //Success! X is a member of the r tree
            } else {
                if (!bool && (!r.member(x) && !t.member(x))) {
            //"Success! X is not a member of the right or left "
                    //      + "tree and therefore not a part of the union
                } else {
                    throw new Exception("Failure! Problem with member or union!");
                }
            }
        }
        checkTree_member_union++;
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
        // If we take R - T = D; then T is either the empty set or it is not
        // a subset of it's difference
        Bag difference = t.diff(r);
        if (t.isEmptyHuh()) {
//            "Success! The tree t is empty leaving the diff "
//                    + "to be all of r
        } else if (!t.subset(difference)) {
//            Success! A tree is not a subset of the "
//                    + "difference"
        } else {
            System.out.println("T cardinality: " + t.cardinality() + " R cardinality: " + r.cardinality());
            throw new Exception("Failure! Problem with subset or diff!");
        }
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

    // TESTING IF BALANCED
    //TESITNG SEQUENCE
    public static void main(String[] args) throws Exception {
        // Hard-coded tests
        Bag<Integer> bag = new SetBag_NonEmpty(5, 3, new SetBag_NonEmpty(3, 1, empty(), empty()), new SetBag_NonEmpty(7, 2, empty(), empty()));
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
        System.out.println("Union & equal: bag U empty() -> bag.cardinality " + bag.cardinality() + " = union.cardinality " + bag.union(empty()).cardinality());
        System.out.println("Union & equal: bag U bag = bag | false = " + bag.union(bag).equal(bag));
        System.out.println("Union & equal: bag U bag = bag | cardinality should be doubled = 2x " + "U:"
                + bag.union(bag).cardinality() + "bag " + bag.cardinality());
        System.out.println("Union & equal: bag U bag = bag | Should have 6 fives = " + bag.union(bag).getCount(5));
        System.out.println("Union & equal: bag U bag = bag | Should have 2 threes = " + bag.union(bag).getCount(3));
        System.out.println("Union & equal: bag U bag = bag | Should have 4 sevens = " + bag.union(bag).getCount(7));

        System.out.println("Inter & equal: bag inter bag = bag | true = " + bag.inter(bag).equal(bag));
        System.out.println("Inter & equal: bag(without 5) inter bag = bag(without 5) | true = " + bag.remove(5).inter(bag).equal(bag.remove(5)));
        System.out.println("AddN & Cardinality 6 + 3 = " + bag.addN(10, 3).cardinality());
        System.out.println("RemoveN & Cardinality 6 - 3 = " + bag.removeN(5, 3).cardinality());
        System.out.println("RemoveN & Cardinality 6 - 2 = " + bag.removeN(5, 2).cardinality());
        System.out.println("RemoveN & Cardinality 6 - 1 = " + bag.removeN(5, 1).cardinality());
        System.out.println("RemoveN & Cardinality 6 - 1 = " + bag.removeN(3, 2).cardinality());
        System.out.println("RemoveN & Cardinality 6 - 1 = " + bag.removeN(3, 1).cardinality());
        // Because 8 isn't in the tree
        System.out.println("RemoveN & Cardinality 6 - 0 = " + bag.removeN(8, 2).cardinality());
        System.out.println("AddN & Cardinality 6 + 3 = " + bag.addN(5, 3).cardinality());
        System.out.println("AddN & Cardinality 6 + 2 = " + bag.addN(5, 2).cardinality());
        System.out.println("AddN & Cardinality 6 + 1 = " + bag.addN(5, 1).cardinality());
        System.out.println("AddN & Cardinality 6 + 2 = " + bag.addN(8, 2).cardinality());
        System.out.println("AddN & Cardinality 6 + 1 = " + bag.addN(3, 1).cardinality());
        System.out.println("Difference: Bag - bag = bag | false = " + bag.diff(bag).equal(bag));
        System.out.println("Difference: Bag - bag = empty() | true = " + bag.diff(bag).equal(empty()));
        System.out.println("Difference: Bag (w/o 5) - bag = empty() | true = " + bag.diff(bag.removeN(5, 3)).equal(empty()));
        System.out.println("Difference: Bag - bag(w/o 5) = bag | true = " + bag.removeN(5, 3).diff(bag).equal(empty().addN(5, 3)));

        System.out.println();
        
        // With data other than ints
        Bag<String> s1 = new SetBag_NonEmpty("aa", 1, empty(), empty());
        Bag<String> s2 = new SetBag_NonEmpty("a", 1, empty(), s1);
        Bag<String> s3 = new SetBag_NonEmpty("aaaaa", 1);
        Bag<String> s4 = new SetBag_NonEmpty("aaaa", 1, s1, s3);
        Bag<String> s5 = new SetBag_NonEmpty("aaa", 1, s2, s4);
        System.out.println("=== Member (String) tests ===");
        System.out.println(s1.member("aa") + " should be " + true);
        System.out.println(s1.member("a") + " should be " + false);
        System.out.println(s2.member("aaa") + " should be " + false);
        System.out.println(s2.member("a") + " should be " + true);
        System.out.println(s3.member("aaaaa") + " should be " + true);
        System.out.println(s4.member("aaaa") + " should be " + true);
        System.out.println(s5.member("aaaa") + " should be " + true);
        System.out.println(s5.member("aaaa") + " should be " + true);
        System.out.println(s5.member("aaaaa") + " should be " + true);
        System.out.println(s5.member("aaa") + " should be " + true);
        System.out.println(s5.member("aa") + " should be " + true);
        System.out.println(s5.member("a") + " should be " + true);

        System.out.println();

        // Random Tests
        // =================
        
        
        // "Testing for Empty() & IsEmptyHuh?: "
        System.out.println();
        for (int i = 0; i < 50; i++) {
            int randomInt = rndInt(0, 1);
            checkTree_empty_isEmptyHuh(randomInt);
        }
        System.out.println("Testing (Int) for Empty() & IsEmptyHuh?: " + checkTree_empty_isEmptyHuh + " times.");

        checkTree_empty_isEmptyHuh = 0;
        
        
        // How do I change my tests to accept type D? It errors when I just have
        // D -> it's not recognizing it. 
        System.out.println();
        for (int i = 0; i < 50; i++) {
            String randomString = rndStringX(0, 1);
            checkTree_empty_isEmptyHuh(randomString);
        }
        System.out.println("Testing (String) for Empty() & IsEmptyHuh?: " + checkTree_empty_isEmptyHuh + " times.");
        
        
        
        
        System.out.println();
        // Testing for Cardinality & IsEmptyHuh
        for (int i = 0; i < 50; i++) {
            int len = rndInt(0, 10);
            Bag l = rndBag(len);
            checkTree_isEmptyHuh_cardinality(l);
        }
        System.out.println("Testing: IsEmptyHuh? & Cardinality: " + checkTree_isEmptyHuh_cardinality + " times");
        System.out.println();

        
        
        // Testing Cardinality & Remove
        for (int i = 0; i < 50; i++) {
            int elt = rndInt(0, 10);
            int len = rndInt(0, 10);
            Bag l = rndBag(len);
            checkTree_cardinality_remove(l, elt);
        }
        System.out.println("Testing: Cardinality & Remove: " + checkTree_cardinality_remove + " times");
           
        
        
        // Testing: Remove (EQUAL) &  Add"
        System.out.println();

        for (int i = 0; i < 50; i++) {
            int len = rndInt(0, 10);
            Bag l = rndBag(len);
            checkTree_remove_equal_add(l);
        }
        System.out.println("Testing: Remove (EQUAL) &  Add: " + checkTree_remove_equal_add + " times");
    
    
        System.out.println();
        // Testing for Add & Member 
        // member (add t x) y = true <-> x = y \/ member t y = true
        for (int i = 0; i < 50; i++) {
            int elt = rndInt(0, 10);
            int elt2 = rndInt(0, 10);
            int len = rndInt(0, 10);
            Bag l = rndBag(len);
            checkTree_add_member(l, elt, elt2);
        }
        System.out.println("Testing: Add & Member: " + checkTree_add_member + " times");
    
        
        
        System.out.println();
        // Testing for Union & Member
        // member (union s s') x = true <-> member s x = true \/ member s' x = true
        for (int i = 0; i < 50; i++) {
            int elt = rndInt(0, 10);
            int len = rndInt(0, 10);
            int len2 = rndInt(0, 10);
            Bag l = rndBag(len);
            Bag r = rndBag(len2);
            checkTree_member_union(l, r, elt);
        }
        System.out.println("Testing: Member & Union: " + checkTree_member_union + " times");

        
        
        System.out.println();

        // Testing Union & Subset 
        for (int i = 0; i < 50; i++) {
            int len = rndInt(0, 10);
            int len2 = rndInt(0, 10);
            Bag l = rndBag(len);
            Bag r = rndBag(len2);
            checkTree_union_subset(l, r);
        }
        System.out.println("Testing: Union & Subset: " + checkTree_union_subset + " times");

        
        
        System.out.println();

        // Testing Subset & Diff 
        for (int i = 0; i < 50; i++) {
            int len = rndInt(0, 10);
            int len2 = rndInt(0, 10);
            Bag l = rndBag(len);
            Bag r = rndBag(len2);
            checkTree_subset_diff(l, r);
        }
       System.out.println("Testing: Subset & Diff: " + checkTree_subset_diff + " times");

        
        

        System.out.println();

        // Testing: Diff (EMPTY & Inter) & Equal
        for (int i = 0; i < 50; i++) {
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
        for (int i = 0; i < 50; i++) {
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
        for (int i = 0; i < 50; i++) {
            int len = rndInt(0, 10);
            Bag l = rndBag(len);
            checkTree_inter_empty(l);
        }
        System.out.println("Testing Inter & Empty(): " + checkTree_inter_empty + " times");

    
    
    
    
    
    
    }

}
