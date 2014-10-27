
package polymorphicsetbags;

import static polymorphicsetbags.TesterClass.rndInt;


public class StringGen implements Generator<String> {
     
    public String nextThing(int min, int max) {
        int rndInt = rndInt(min, max);
        StringBuilder stringBuffer = new StringBuilder("");
        // Random generator for letter?
//        Random rnd = new Random();
//        int letter = rnd.nextInt(24);
        for (int i = 0; i > rndInt; i++) {
            stringBuffer.append("X");
        }
        return stringBuffer.toString();
    }
    
}
