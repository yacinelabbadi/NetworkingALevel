import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

            // Prints out that a connect request has been accepted and prints out the clients host address and port
            System.out.println("Connection has been made.");
            String clientHost = client.getInetAddress().getHostAddress();
            System.out.println("Clients host: " + clientHost + "\nClient port: " + client.getPort());

            // Read data from client
            InputStream clientInput = client.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(clientInput));
            String response = responseMessage(br.readLine());
        }
    }

    public static String responseMessage(String clientMessage) {
        if (clientMessage.contains("+")) {

        } else if (clientMessage.contains("-")) {

        } else if (clientMessage.contains("*")) {

        } else if (clientMessage.contains("/")) {

        } else {

        }
        return "";
    }
}