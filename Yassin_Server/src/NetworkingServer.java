import java.net.*;

public class NetworkingServer {
    public static void main(String[] args) throws Exception {

        // default port number we are going to use
        int portNumber = 4711;

        // The if-statement for args > 0 is not necessary
        // since I won't be sending in command line arguments

        // Creating the server socket
        ServerSocket server = new ServerSocket(portNumber);
        System.out.println("Server socket has been created.");

        // Wait for data from client and respond
        while (true) {
            // Listens and waits until a connection is made
            System.out.println("Waiting for a request...");
            Socket client = server.accept();
        }
    }
}