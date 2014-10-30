
package polymorphicsetbags.Sequence;


/**
 *
 * @author kathrynhodge
 */
public class Sequence_Empty<D> implements Sequence<D>{

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
    
    
    
}
