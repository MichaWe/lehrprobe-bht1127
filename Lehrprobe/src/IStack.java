public interface IStack {
    /**
     * Add an element to the stack
     * @param elm Element to push onto the stack
     * @throws IndexOutOfBoundsException Exception thrown if the capacity of the stack is exceeded
     */
    void push (float elm);

    /**
     * Remove and return the top element of the stack without dropping it
     * @return Top element of the stack
     * @throws IndexOutOfBoundsException Exception thrown if the stack is empty
     */
    float pop();

    /**
     * Return the top element of the stack without dropping it
     * @return Top element of the stack
     * @throws IndexOutOfBoundsException Exception thrown if the stack is empty
     */
    float peek();

    /**
     * Return the sum of items on the stack
     * @return Summed items
     */
    float sum();

    /**
     * Return the current number of items on the stack
     * @return Current number of items on the stack
     */
    int size();

    /**
     * Return the maximum number of items oin the stack
     * @return Stack capacity
     */
    int capacity();
}
