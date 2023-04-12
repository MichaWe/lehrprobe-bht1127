/**
 * Stack that uses no threading mechanisms
 * Based on demo of <a href="http://www.angelikalanger.com/Articles/EffectiveJava/12.MT-Basics/12.MT-Basics.html">Angelika Langer</a>
 */
public class SerialStack implements IStack {
    /// Stored data
    private final float[] array;

    /// Current top element on the stack
    private int cnt = 0;

    /**
     * Init stack with the given maximum size
     * @param size Size
     */
    public SerialStack(int size) {
        array = new float[size];
    }

    public void push (float elm) {
        if (cnt < array.length) {
            array[cnt] = elm;
            try {
                Thread.sleep(5000);
            }
            catch (InterruptedException ignored) { }
            cnt++;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public float pop () {
        if (cnt > 0) return(array[--cnt]);
        else throw new IndexOutOfBoundsException();
    }

    public float peek() {
        if (cnt > 0) return(array[cnt-1]);
        else throw new IndexOutOfBoundsException();
    }

    @Override
    public float sum() {
        float result = 0;
        for (int index = 0; index < cnt; index++) {
            result += array[index];
        }
        return result;
    }

    public int size() {
        return cnt;
    }

    public int capacity() {
        return (array.length);
    }
}
