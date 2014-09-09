package UDPCripto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void main(String args[]) throws Exception {
        Criptografia cripto = new Criptografia();
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[16];
        byte[] receiveData = new byte[16];
        while (true) {

            System.out.println("Cliente preparado para enviar: ");
            //Lê entrada do usuário
            String sentence = inFromUser.readLine();

            sendData = cripto.encrypt(sentence);
            for (int i = 0; i < sendData.length; i++) {
            }
            //Cria pacote udp
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            //envia ao servidor
            clientSocket.send(sendPacket);
            //Recebe resposta do servidor
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            byte[] recip = receiveData;
            System.out.print("Cipher:  ");
            for (int i = 0; i < recip.length; i++) {
                System.out.print(new Integer(recip[i]) + " ");
            }
            String decrip = cripto.decrypt(recip);
            System.out.println("");
            System.out.println("Recebido do Servidor UDP:" + decrip);
            //Fecha conexão:
            clientSocket.close();
        }
    }
}