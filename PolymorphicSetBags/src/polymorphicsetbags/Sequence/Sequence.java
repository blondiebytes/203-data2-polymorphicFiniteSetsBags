
package polymorphicsetbags.Sequence;

public interface Sequence<D extends Comparable> {
 
    public D here();
    
    public boolean hasNext();
    
    public Sequence<D> next();
   
    /**
 * Returns an Image object that can then be painted on the screen. 
 * The url argument must specify an absolute URL. The name
 * argument is a specifier that is relative to the url argument. 
 * <p>
 * This method always returns immediately, whether or not the 
 * image exists. When this applet attempts to draw the image on
 * the screen, the data will be loaded. The graphics primitives 
 * that draw the image will incrementally paint on the screen. 
 *
 * @return      the image at the specified URL
 * @see         Sequenced
 */
    public String toStringSequence();
    
}
