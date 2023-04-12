import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * Thread with access to the stack
 */
public class WorkerRunnable implements Runnable {
    /// Stack to add the booking to
    protected final IStack stack;

    /// Communication socket
    protected final Socket socket;

    /**
     * Create a new worker thread
     * @param stack Stack to add the booking to
     * @param socket Communication socket
     */
    public WorkerRunnable(IStack stack, Socket socket) {
        this.stack = stack;
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Run new Thread " + Thread.currentThread().getId());
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String valueString = reader.readLine().trim();
            float value = Float.parseFloat(valueString);
            stack.push(value);

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            Date now = new Date();
            writer.printf("[%s]: %.2f", now, stack.sum());

            // Finish communication
            writer.close();
            socket.close();
        }
        catch (Exception ignored) {

        }
        System.out.println("Finish Thread " + Thread.currentThread().getId());
    }
}
