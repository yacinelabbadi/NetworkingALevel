import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class NetworkingClient {
    public static void main(String[] args) throws Exception {

        // Portnumber we are going to use
        int portNumber = 4711;

        // Create client socket
        Socket client = new Socket(InetAddress.getLocalHost(), portNumber);
        System.out.println("Client socket has been created: " + client);

        // Create output stream for the client socket
        OutputStream clientOutput = client.getOutputStream();
        PrintWriter pw = new PrintWriter(clientOutput, true);

        // Create input stream for client socket
        InputStream clientInput = client.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(clientInput));
    }
}