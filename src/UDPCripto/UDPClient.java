package UDPCripto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void main(String args[]) throws Exception {
        Criptografia cript = new Criptografia();
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[16];
        byte[] receiveData = new byte[16];
        while (true) {

            System.out.println("Apto a receber dados: ");
            //Lê entrada do usuário
            String sentence = inFromUser.readLine();

            sendData = cript.encrypt(sentence);
            for (int i = 0; i < sendData.length; i++) {
            }
            //Cria pacote udp
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            //envia ao servidor
            clientSocket.send(sendPacket);
            //Recebe resposta do servidor
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            byte[] receb = receiveData;
            System.out.print("exercicio criphografia:  ");
            for (int i = 0; i < receb.length; i++) {
                System.out.print(new Integer(receb[i]) + " ");
            }
            String decrip = cript.decrypt(receb);
            System.out.println("");
            System.out.println("apto a Receber do servidor!!:  " + decrip);
            //Fecha conexão:
            //clientSocket.close();
        }
    }
}