
package polymorphicsetbags.Tests;


public class Hoolean implements Comparable<Hoolean> {

    Boolean bool;
    
    public Hoolean(Boolean boolz) {
        bool = boolz;
    }
    
    public int compareTo(Hoolean bool) {
        if (this.bool == bool.bool) {
            return 0;
        } else if (!this.bool) {
            return -1;
        } else {
            return 1;
        }
        // -1 (this < bool) ; 0 (this == bool) ; 1 (this > bool)
        //  false < true;       false == false      true > false
        ///                      true == true
       
    }
    
    public String toString() {
        return "" + this.bool;
    }
}
  