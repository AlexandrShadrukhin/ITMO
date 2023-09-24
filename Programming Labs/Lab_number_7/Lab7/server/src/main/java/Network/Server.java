package Network;

import Collection.Collection;
import WorkModules.Answer;
import WorkModules.DatabaseHandler;
import WorkModules.ReaderOfCommands;
import WorkModules.Task;

import java.io.*;
import java.net.*;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private DatagramSocket socket = new DatagramSocket(8000);
    private InetAddress address = InetAddress.getByName("localhost");
    private static final Logger logger = Logger.getLogger("logger");
    private ExecutorService fixedThreadPoolTask = Executors.newFixedThreadPool(10);
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private ExecutorService fixedThreadPoolAnswer = Executors.newFixedThreadPool(10);
    private LinkedBlockingQueue<Task> blockingQueueTask = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Answer> blockingQueueAnswer = new LinkedBlockingQueue<>();


    private byte[] buffer = new byte[4096];

    public Collection collection;
    public DatabaseHandler databaseHandler= new DatabaseHandler();

    public String path;
    private DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
    ReaderOfCommands readerOfCommands = new ReaderOfCommands();

    public Server() throws SocketException, UnknownHostException, SQLException {
    }

    public Server(Collection collection, DatabaseHandler databaseHandler) throws SocketException, UnknownHostException, SQLException {
        this.collection = collection;
        this.databaseHandler= databaseHandler;
    }

    public void runServer() throws IOException, ClassNotFoundException {
        fixedThreadPoolTask.submit(() -> {
            try {
                while (true) {
                    logger.log(Level.INFO, "Получение информации");
                    socket.receive(packet);
                    byte[] data = packet.getData();
                    ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
                    Task task = (Task) objectInputStream.readObject();
                    blockingQueueTask.put(task);
                    processTask();
                }
            } catch (Exception e) {
            }
        });
        cachedThreadPool.submit(() -> {
            try {
                processTask();
            } catch (Exception e) {
            }
        });
        fixedThreadPoolAnswer.submit(() -> {
            try {
                while (true) {
                    Answer answer = blockingQueueAnswer.take();
                    sendAnswer(address, packet.getPort(), answer);
                }
            } catch (Exception e) {
            }
        });
    }

    public void processTask() throws IOException, InterruptedException, SQLException {
        Task task = blockingQueueTask.take();
        logger.log(Level.INFO, "Выполнение команды");
        blockingQueueAnswer.put(readerOfCommands.startReadCommands(collection, path, task, databaseHandler));
    }

    public void sendAnswer(InetAddress address, int port, Answer answer) throws IOException {
        logger.log(Level.INFO, "Отправка ответа");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(answer);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
        socket.send(packet);
    }
}
