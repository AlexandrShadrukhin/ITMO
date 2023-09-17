package Network;

import Collection.Collection;
import WorkModules.Answer;
import WorkModules.ReaderOfCommands;
import WorkModules.Task;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private DatagramSocket socket= new DatagramSocket(8000);
    private InetAddress address= InetAddress.getByName("localhost");
    private static final Logger logger = Logger.getLogger("logger");
    private byte[] buffer = new byte[4096];

    public Collection collection;

    public String path;
    private DatagramPacket packet= new DatagramPacket(buffer, buffer.length);
    ReaderOfCommands readerOfCommands= new ReaderOfCommands();

    public Server() throws SocketException, UnknownHostException {
    }

    public Server(Collection collection, String path) throws SocketException, UnknownHostException {
        this.collection = collection;
        this.path = path;
    }

    public void runServer() throws IOException, ClassNotFoundException {
        while (true){
            logger.log(Level.INFO, "Получение информации");
            socket.receive(packet);
            byte[] data= packet.getData();
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
            Task task= (Task) objectInputStream.readObject();
            System.out.println(task.describe.toString());
            processTask(task, packet.getAddress(), packet.getPort());
        }
    }

    public void processTask(Task task, InetAddress address, Integer port) throws IOException {
        logger.log(Level.INFO, "Выполнение команды");
        sendAnswer(readerOfCommands.startReadCommands(collection, path, task), address, port);

    }

    public void sendAnswer(Answer answer, InetAddress address, Integer port) throws IOException {
        logger.log(Level.INFO, "Отправка ответа");
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(answer);
        byte[] buffer= byteArrayOutputStream.toByteArray();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
    }
}
