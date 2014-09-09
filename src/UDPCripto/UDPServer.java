package UDPCripto;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    public static void main(String args[]) throws Exception {
        //Cria um servidor UDP na porta 9876
        DatagramSocket serverSocket = new DatagramSocket(9876);
        //Sockets apenas enviam bytes
        Criptografia criph = new Criptografia();
        byte[] receiveData = new byte[16];
        byte[] sendData = new byte[16];
        while (true) {
            System.out.println("Servidor UDP recebendo dados...");
            //Recebe as mensagens dos clientes
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            //System.out.println("");
            byte[] receb = receiveData;
            for (int i = 0; i < receb.length; i++) {
            }
            String decrip = criph.decrypt(receb);
            System.out.println("");
            System.out.println("Recebido: "+ decrip);
            //Responde ao mesmo IP e Porta do pacote recebido.
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String capitalizedSentence = decrip.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            sendData = criph.encrypt(decrip);
            
            System.out.println("");
            for (int i = 0; i < sendData.length; i++) {
                System.out.print(new Integer(sendData[i]) + " ");
            }
            System.out.println("");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
            for (int i = 0; i < receiveData.length; i++) {
                receiveData[i] = 0;
            }
        }
    }
}