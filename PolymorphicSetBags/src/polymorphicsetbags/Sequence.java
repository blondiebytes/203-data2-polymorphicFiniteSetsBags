
package polymorphicsetbags;

import java.util.Iterator;


public interface Sequence<D> {
 
    public D here();
    
    public boolean hasNext();
    
    public Sequence<D> next();
   
    
}
