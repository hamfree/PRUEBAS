/*
 * Copyright 2023 juanf.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.nom.juanfranciscoruiz.red;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author juanf
 */
public class SocketServer {

    public static final int PORT = 1527;

    public static void main(String[] args) throws IOException {

        ServerSocket s = new ServerSocket(PORT); //create server socket object
        System.out.println("Started: " + s);

        try {
            Socket socket = s.accept(); //accept a socket connection using the Server Socket created previously
            try {
                System.out.println("Connection accepted: " + socket);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //create an input variable for the socket- accepts input from the socket

                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);   // create an output variable for the socket- writes output to the socket

                //this loop accepts input from the socket, prints out what it receives anc echoes it back into the socket until it recives the word "END" indicating the end of transmission
                while (true) {
                    String str = in.readLine(); //read line from socket
                    if (str.equals("END")) {
                        break;
                    }
                    System.out.println("Echoing: " + str);  //print line to console
                    out.println("You said: " + str); //echo line read back into socket
                }

            } catch (SocketException e) {
                System.out.println("Client closed connection");
            } finally {               //close the present connection
                System.out.println("closing...");
                socket.close();
            }

        } catch (SocketException e) {
            System.out.println("Server socket closed unexpectedly");
        } finally {               //close the server socket. i.e. stop listening for connections
            s.close();          //without this line, the server would continue waiting for another connection
        }
    }
}
