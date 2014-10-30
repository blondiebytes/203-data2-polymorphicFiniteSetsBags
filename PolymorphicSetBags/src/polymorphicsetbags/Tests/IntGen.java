/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polymorphicsetbags.Tests;

import java.util.Random;

public class IntGen implements Generator<Integer> {
    
    public Integer nextThing(int min, int max) {
        Random rnd = new Random();
        return rnd.nextInt((max - min) + 1) + min;
    }
   
}
