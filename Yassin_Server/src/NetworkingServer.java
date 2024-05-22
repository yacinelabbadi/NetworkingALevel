import java.io.*;
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
            String clientMessage = br.readLine();
            String response = responseMessage(clientMessage);

            // Send response to client
            OutputStream clientOutput = client.getOutputStream();
            PrintWriter pw = new PrintWriter(clientOutput, true);
            pw.println(response);

            // Exit and close if client send message "bye"
            if (clientMessage != null && clientMessage.equalsIgnoreCase("bye")) {
                br.close();
                pw.close();
                server.close();
                client.close();
                break;
            }
        }
    }

    // Method for making calculating and making a response to the clients message
    // Uses indexOf to find the operation sign position and takes the numbers before and after it
    // and uses corresponding operation to calculate the answer and make a response
    public static String responseMessage(String clientMessage) {
        int answer;
        int firstNumber;
        int secondNumber;
        String response = "";

        try {
            int operationPosition;
            if (clientMessage != null) {
                clientMessage.replaceAll(" ", "");
                if (clientMessage.contains("+")) {
                    operationPosition = clientMessage.indexOf("+");
                    firstNumber = Integer.parseInt(clientMessage.substring(0, operationPosition));
                    secondNumber = Integer.parseInt(clientMessage.substring(operationPosition));
                    answer = firstNumber + secondNumber;

                    response = "Summan av " + clientMessage + " är " + answer;
                } else if (clientMessage.contains("-")) {
                    operationPosition = clientMessage.indexOf("-");
                    firstNumber = Integer.parseInt(clientMessage.substring(0, operationPosition));
                    secondNumber = Integer.parseInt(clientMessage.substring(operationPosition));
                    answer = firstNumber - secondNumber;

                    response = "Differensen av " + clientMessage + " är " + answer;
                } else if (clientMessage.contains("*")) {
                    operationPosition = clientMessage.indexOf("*");
                    firstNumber = Integer.parseInt(clientMessage.substring(0, operationPosition));
                    secondNumber = Integer.parseInt(clientMessage.substring(operationPosition));
                    answer = firstNumber * secondNumber;

                    response = "Produkten av " + clientMessage + " är " + answer;
                } else if (clientMessage.contains("/")) {
                    operationPosition = clientMessage.indexOf("/");
                    firstNumber = Integer.parseInt(clientMessage.substring(0, operationPosition));
                    secondNumber = Integer.parseInt(clientMessage.substring(operationPosition));
                    answer = firstNumber / secondNumber;

                    response = "Kvoten av " + clientMessage + " är " + answer;
                } else {
                    response = "Instructions were not followed, please try again.";
                }
            }
        } catch (Exception e) {
            response = "Instructions were not followed, please try again";
        }
        return response;
    }
}