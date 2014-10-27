/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags;


public interface Generator<D extends Comparable> {
   D nextThing(int min, int max);
}
