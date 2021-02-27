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

public class ListNIFs 
{
    public static void main(String args[]) throws SocketException {
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        
        for (NetworkInterface netIf : Collections.list(nets)) {
            out.printf("Nombre para mostrar: %s\n", netIf.getDisplayName());
            out.printf("Nombre: %s\n", netIf.getName());
            displaySubInterfaces(netIf);
            out.printf("\n");
        }
    }

    static void displaySubInterfaces(NetworkInterface netIf) throws SocketException {
        Enumeration<NetworkInterface> subIfs = netIf.getSubInterfaces();
        
        for (NetworkInterface subIf : Collections.list(subIfs)) {
            out.printf("\tNombre para mostrar de la subinterfaz: %s\n", subIf.getDisplayName());
            out.printf("\tNombre de la subinterfaz: %s\n", subIf.getName());
        }
     }
}  