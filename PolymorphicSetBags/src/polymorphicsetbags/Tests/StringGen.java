
package polymorphicsetbags.Tests;

import static polymorphicsetbags.Tests.TesterClass.rndInt;


public class StringGen implements Generator<String> {
     
    public String nextThing(int min, int max) {
        int rndInt = rndInt(min, max);
        StringBuilder stringBuffer = new StringBuilder("");
        for (int i = 0; i < rndInt; i++) {
            stringBuffer.append(Character.toChars(65 + rndInt(0, 26))[0]);
        }
        return stringBuffer.toString();
    }
    
}
