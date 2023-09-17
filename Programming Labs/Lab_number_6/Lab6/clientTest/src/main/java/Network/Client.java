package Network;

import WorkModules.Answer;
import WorkModules.Printer;
import WorkModules.Task;

import java.io.*;
import java.net.*;
import java.nio.channels.SocketChannel;

public class Client {
    private DatagramSocket socket= new DatagramSocket();
    private InetAddress address= InetAddress.getByName("localhost");
    private byte[] buffer = new byte[4096];
    private DatagramPacket packet= new DatagramPacket(buffer, buffer.length);

    private Printer printer= new Printer();

    public Client() throws SocketException, UnknownHostException {
    }


    public void sendTask(Task task) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream= new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(task);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        DatagramPacket packet= new DatagramPacket(buffer, buffer.length, address, 8000);
        socket.send(packet);
        getAnswer();
    }

    public void getAnswer() throws IOException, ClassNotFoundException {
        socket.receive(packet);
        byte[] data = packet.getData();
        ObjectInputStream objectInputStream= new ObjectInputStream(new ByteArrayInputStream(data));
        Answer answer= (Answer) objectInputStream.readObject();
        printer.printAnswer(answer);
    }

}
