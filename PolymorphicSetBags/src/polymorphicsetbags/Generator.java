
package polymorphicsetbags;


public interface Generator<D extends Comparable> {
   D nextThing(int min, int max);
}
