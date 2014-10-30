
package polymorphicsetbags.Tests;

import polymorphicsetbags.Tests.Hoolean;
import java.util.Random;


public class BooleanGenerator implements Generator<Hoolean> {
    
    public Hoolean nextThing(int min, int max) {
        Random rnd = new Random();
        int bool = rnd.nextInt(1) + 1;
        if (bool == 1) {
            return new Hoolean(false);
        } else {
            return new Hoolean(true);
        }
    }

}
