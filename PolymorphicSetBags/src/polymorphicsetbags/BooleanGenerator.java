
package polymorphicsetbags;

import java.util.Random;


public class BooleanGenerator implements Generator<boolean> {
    
    public boolean nextThing(int min, int max) {
        Random rnd = new Random();
        int bool = rnd.nextInt(1);
        return bool != 0;
    }

}
