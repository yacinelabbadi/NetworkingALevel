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

        // Create BufferedReader for user input (Is it possible to use Scanner instead?)
        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

        String message;
        String response;

        // While loop for user sending and receiving messages to/from server
        while (true) {
            System.out.println("Write a simple math problem only using numbers and operation sings" +
                    ", like '1+1', or write bye to exit the program");

            // Reads in user message and sends to server
            message = userIn.readLine().trim();
            pw.println(message);

            // Reads in message from server and prints it out
            response = br.readLine();
            System.out.println("Message from server: " + response);

            // Exits if user writes "bye"
            if (message.equalsIgnoreCase("bye")) {
                br.close();
                pw.close();
                userIn.close();
                client.close();
                break;
            }
        }
    }
}