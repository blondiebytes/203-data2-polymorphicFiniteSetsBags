/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags.Sequence;

public class Sequence_NonEmpty<D extends Comparable> implements Sequence<D>, Sequenced<D> {
    D here;
    int count;
    Sequence_NonEmpty<D> next;
    
    public Sequence_NonEmpty(D here, int count, Sequence_NonEmpty<D> next) {
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
       if (count != 0) {
        return new Sequence_NonEmpty(here, count - 1, next);
            } else if (next.hasNext()) {
                 return new Sequence_NonEmpty(next.here(), next.count - 1, next.next);
            } else {
                return new Sequence_Empty();
            }
       }
    
    public Sequence<D> seq() {
        return this;
    }
    
}
