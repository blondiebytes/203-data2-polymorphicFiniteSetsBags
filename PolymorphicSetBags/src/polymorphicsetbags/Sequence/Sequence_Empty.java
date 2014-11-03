
package polymorphicsetbags.Sequence;


/**
 *
 * @author kathrynhodge
 */
public class Sequence_Empty<D extends Comparable> implements Sequence<D>{

    public void Sequence_Empty() {
   
    }
    
    public D here() {
        // add exception later
       return null; 
    }

    public boolean hasNext() {
       return false;
    }

    public Sequence<D> next() {
         // add exception later
        return this; 
    }
    
    
    
}
