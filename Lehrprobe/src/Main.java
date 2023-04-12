import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        // Create our main stack
        if (args.length != 2) {
            throw new IllegalArgumentException("You need to specify the port number and mode of operation");
        }

        int port = Integer.parseInt(args[0]);
        String mode = args[1];
        IStack stack = Objects.equals(mode, "parallel-save") ? new ParallelStack(1000) : new SerialStack(1000);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setReuseAddress(true);
            System.out.println("Server is listening on port " + port);

            while (true) {
                System.out.println("Wait for incoming connection");
                Socket socket = serverSocket.accept();
                System.out.println("New connection from " + socket.getInetAddress());
                if (Objects.equals(mode, "serial")) {
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    String valueString = reader.readLine().trim();
                    float value = Float.parseFloat(valueString);
                    stack.push(value);

                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);

                    Date now = new Date();
                    writer.printf("[%s]: %.2f", now, stack.sum());
                    System.out.printf("[%s]: IP: %s - Action: %.2f € - New balance: %.2f €%n", now, socket.getInetAddress(), value, stack.sum());

                    // Finish communication
                    writer.close();
                    socket.close();
                }
                else {
                    System.out.println("Start Thread");
                    WorkerThread thread = new WorkerThread(stack, socket);
                    thread.start();
                    System.out.println("Thread started");
                }
            }
        }
        catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}