
package polymorphicsetbags.Sequence;

public interface Sequence<D extends Comparable> {
 
    public D here();
    
    public boolean hasNext();
    
    public Sequence<D> next();
   
    public String toStringSequence();
    
}
