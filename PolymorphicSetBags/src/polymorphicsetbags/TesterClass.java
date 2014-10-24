
package polymorphicsetbags;

import java.util.Random;
import static polymorphicsetbags.SetBag_NonEmpty.empty;

public class TesterClass {
    
    // Tests to Think About:
    
    // When you add a new thing, the cardinality should increase
    // When you add something that is already in the list, the cardinality 
    // should still increase
    // When you remove something that is duplicated in the list, the cardinality
    // should be one less (it should only remove one of the duplicated things)
    
    // Count should always be positive
    
    //TESTING MULTI-BAG:
    
     public static int rndInt(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt((max-min) + 1) + min;
    }
    
    public static Bag rndBag(int count) {
        if (count == 0) {
            return empty();
        } else {
            return rndBag(count-1).addN(rndInt(0,50), rndInt(0,10));
        }
    }
    
    public static void checkTree_empty_isEmptyHuh (int count) throws Exception {
        // Creating a empty tree or a random tree
        if (count == 0) {
            Bag t = empty();
            if (!t.isEmptyHuh()) {
                throw new Exception("Failure: An empty bag is not empty");
            }
        } else {
            int len = rndInt(1, 10);
            Bag l = rndBag(len);
            if (l.isEmptyHuh()) {
                throw new Exception("Failure: A nonempty set is empty!");
            }
    }
    }
    
    public static void checkTree_isEmptyHuh_cardinality(Bag t) throws Exception{
        if (!t.isEmptyHuh() && (t.cardinality() == 0)) {
            throw new Exception("Failure: Nonempty set and had a "
                    + "cardinality equal to zero");
        }
        if (t.isEmptyHuh() && (t.cardinality() != 0)) {
                System.out.println("Failure: Empty with not cardinality = 0");
        }
    }
    
    public static void checkTree_cardinality_remove(Bag t, int x ) throws Exception {
        int nT = t.remove(x).cardinality();
        // Either something was removed -> and it decreased the tree by one
        // Or the thing wasn't there to begin with, and nothing was removed
        if (nT == (t.cardinality() - 1) || nT != t.cardinality())  {
            throw new Exception("Failure - the remove and/or cardinality"
                    + " function failed :( ");
        }
    }
         
         public static void checkTree_remove_equal_add(Bag t) throws Exception{
             // Add and remove the same element from a copied tree
             int rand = rndInt(51,100);
             Bag nT = t.add(rand);
             nT = nT.remove(rand);
             // If the tree we messed with is the same as the original tree
             // then we are correct!
             if (!t.equal(nT)) {
                 throw new Exception("Failure: The tree changed after adding and removing same item");
             }
         }

     
    public static void checkTree_add_member (Bag t, int x, int y) throws Exception {
        Boolean bool = t.add(x).member(y);
        if ( bool && x == y) {
           //Success! X = Y and it's in the tree
        } else { if (bool && t.member(y)) {
           //"Success! Y was a member of y beforehand and "
                   //"it's in the tree"
        } else { if (!bool && (x != y && !t.member(y))) {
//          Success! X != Y and is not a member of the original"
//                    + " tree and therefore is not a member of this tree"
        } else { 
            throw new Exception("Failure! Problem with member or add!");
        }
        }
    }
    }
    
    public static void checkTree_member_union(Bag t, Bag r, int x) throws Exception{
        Boolean bool = t.union(r).member(x);
        if ( bool && t.member(x)) {
            //"Success! X is a member of the t tree"
        } else { if (bool && r.member(x)) {
            //Success! X is a member of the r tree
        } else { if (!bool && (!r.member(x) && !t.member(x))) {
            //"Success! X is not a member of the right or left "
              //      + "tree and therefore not a part of the union
        } else { 
            throw new Exception("Failure! Problem with member or union!");
        }
        }
        }
    }
    
    public static void checkTree_union_subset (Bag t, Bag r) throws Exception {
        Bag unionLR = t.union(r);
        if (!(t.subset(unionLR) && r.subset(unionLR))) {
           throw new Exception("Failure!The left and right trees are not subsets"
                    + " of their union");
        } 
    }
    
    
    // 
    public static void checkTree_subset_diff (Bag t, Bag r) throws Exception{
        // If we take R - T = D; then T is either the empty set or it is not
        // a subset of it's difference
        Bag difference = t.diff(r);
        if (t.isEmptyHuh()) {
//            "Success! The tree t is empty leaving the diff "
//                    + "to be all of r
        } else if (!t.subset(difference)) {
//            Success! A tree is not a subset of the "
//                    + "difference"
        }
            else {
           throw new Exception("Failure! Problem with subset or diff!");
        }
    }
   
    // This test also says something is wrong with diff because all of the other
    // tests -> besides the ones using diff -> work
    public static void checkTree_diff_inter_empty_equal (Bag t, Bag r) throws Exception{
        // t inter r = the empty set iff t - r = t
        if ((t.inter(r)).equal(empty()) && r.diff(t).equal(t)) {
//            "Success! A inter B = the empty set iff A - B = A"
        } else if (!(t.inter(r)).equal(empty()) && !r.diff(t).equal(t)) {
//            "Success! A inter B != the empty set iff A - B != A"
    } else 
            throw new Exception("Failure! Wrong: diff, inter, empty, or equal");
    }
    

    public static void checkTree_equal_union_inter (Bag t, Bag r) throws Exception{ 
        // Two sets are equal iff their union and intersection is the same
        if ((t.union(r).equal(t.inter(r))) && t.equal(r)) {
//            "Success! The two trees are equal and have "
//                    + "the same intersection and union"
        } else if ((t.union(r) != t.inter(r)) && !t.equal(r)) {
//            "Success! They are not equal and their"
//                    + " intersection and union are different"
        } else {
            throw new Exception("Failure! Wrong: equal, union, or diff");
        }
    }
    
    // The Identity Property for Inter
    public static void checkTree_inter_empty(Bag t) throws Exception{
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

    }
    
    // TESTING IF BALANCED
    
    //TESITNG SEQUENCE
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
        System.out.println("Union & equal: bag U bag = bag | false = " + bag.union(bag).equal(bag));
        System.out.println("Union & equal: bag U bag = bag | cardinality should be doubled = 2x " + "U:" + 
                       bag.union(bag).cardinality() + "bag " + bag.cardinality());
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
        System.out.println("Difference: Bag (w/o 5) - bag = empty() | true = " + bag.diff(bag.removeN(5,3)).equal(empty()));
        System.out.println("Difference: Bag - bag(w/o 5) = bag | true = " + bag.removeN(5, 3).diff(bag).equal(empty().addN(5, 3)));

        System.out.println();
  
               

        
    }
    
}
