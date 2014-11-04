/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags.Sequence;

public class Sequence_NonEmpty<D extends Comparable> implements Sequence<D>, Sequenced<D> {
    D here;
    int count;
    Sequence<D> next;
    
    public Sequence_NonEmpty(D here, int count, Sequence<D> next) {
        this.here = here;
        this.count = count;
        this.next = next;
    }
    
    public boolean hasNext() {
        return true;
    }
    
    public D here() {
        return this.here;
    }
    
    public Sequence<D> next() {
       if (count > 1) {
        return new Sequence_NonEmpty(here, count - 1, next);
       } else {
        return next;   
         } 
       }
    
    public Sequence<D> seq() {
        return this;
    }
    
    public String toStringSequence() {
        return " " + this.here + " ";
    }
    
}
