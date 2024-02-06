package task4;

import java.util.Iterator;

public class HumanIterator implements Iterator<Human> {

    private Human[] humans;
    int index;

    public HumanIterator(Human[] humans) {
        this.humans = humans;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < humans.length;
    }

    @Override
    public Human next() {
        return humans[index++];
    }
}
