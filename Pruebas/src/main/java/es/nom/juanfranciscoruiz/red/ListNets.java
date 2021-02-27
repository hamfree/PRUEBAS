/*
 * Copyright 2021 Juan Francisco Ruiz.
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

/**
 *
 * @author Juan Francisco Ruiz
 */
import static java.lang.System.out;
import java.net.*;
import java.util.*;

public class ListNets {

    public static void main(String args[]) throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets))
            displayInterfaceInformation(netint);
    }

    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        out.printf("Nombre para visualizar: %s\n", netint.getDisplayName());
        out.printf("Nombre: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            out.printf("Dirección de red: %s\n", inetAddress);
        }
        out.printf("\n");
     }
}  