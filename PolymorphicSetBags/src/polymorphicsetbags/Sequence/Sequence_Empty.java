
package polymorphicsetbags.Sequence;


public class Sequence_Empty<D extends Comparable> implements Sequence<D>{

    public void Sequence_Empty() {
   
    }
    
    public D here() {
       return null; 
    }

    public boolean hasNext() {
       return false;
    }

    public Sequence<D> next() {
        return this; 
    }
    
    public String toStringSequence() {
        return " ";
    }
    
    
    
    
    
}
