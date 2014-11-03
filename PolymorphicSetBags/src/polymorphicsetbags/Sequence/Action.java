
package polymorphicsetbags.Sequence;


public interface Action<D extends Comparable> {
    
    public D apply (D h);
}
