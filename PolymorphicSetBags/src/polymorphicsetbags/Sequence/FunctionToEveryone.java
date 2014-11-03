
package polymorphicsetbags.Sequence;


public class FunctionToEveryone<D extends Comparable> implements Sequence<D>{
    Action<D> F;
    Sequence<D> seq;
    
    public FunctionToEveryone(Action F, Sequence seq) {
        this.F = F;
        this.seq = seq;
    }
    
    public D here() {
        return this.F.apply(this.seq.here());
    }
    
    public boolean hasNext() {
        return this.seq.hasNext();
    }
    
    public Sequence<D> next() {
        return new FunctionToEveryone(F, this.seq.next());
    }
    
}
