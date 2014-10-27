
package polymorphicsetbags;

import static polymorphicsetbags.SetBag_NonEmpty.empty;


public class RunTests {

    
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
        
        }
    
}
