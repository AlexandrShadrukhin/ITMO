package Network;

import Collection.Collection;
import WorkModules.Answer;
import WorkModules.DatabaseHandler;
import WorkModules.ReaderOfCommands;
import WorkModules.Task;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private final DatagramSocket socket;
    private final InetAddress address;
    private final ExecutorService taskExecutor;
    private final ExecutorService answerExecutor;
    private final LinkedBlockingQueue<Task> taskQueue;
    private final LinkedBlockingQueue<Answer> answerQueue;

    private final byte[] buffer = new byte[4096];

    private final Collection collection;
    private final DatabaseHandler databaseHandler;
    private final String path;
    private final DatagramPacket packet;

    private final ReaderOfCommands readerOfCommands = new ReaderOfCommands();

    private static final Logger logger = Logger.getLogger("logger");

    public Server(Collection collection, DatabaseHandler databaseHandler) throws SocketException, UnknownHostException, SQLException {
        this.socket = new DatagramSocket(8000);
        this.address = InetAddress.getByName("localhost");
        this.collection = collection;
        this.databaseHandler = databaseHandler;
        this.taskExecutor = Executors.newFixedThreadPool(8);
        this.answerExecutor = Executors.newFixedThreadPool(8);
        this.taskQueue = new LinkedBlockingQueue<>();
        this.answerQueue = new LinkedBlockingQueue<>();
        this.path = "";
        this.packet = new DatagramPacket(buffer, buffer.length);
    }

    public void runServer() throws IOException, ClassNotFoundException {
        taskExecutor.submit(this::receiveTask);
        taskExecutor.submit(this::processTask);
        answerExecutor.submit(this::sendAnswer);
    }

    private void receiveTask() {
        try {
            while (true) {
                logger.log(Level.INFO, "Получение информации");
                socket.receive(packet);
                byte[] data = packet.getData();
                try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
                    Task task = (Task) objectInputStream.readObject();
                    if (task.describe.contains("registration") || task.describe.contains("auth")){    databaseHandler.setLogin(task.login);
                        taskQueue.put(task);}
                    else {    taskQueue.put(task);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Ошибка при получении информации", e);
        }
    }

    private void processTask() {
        try {
            while (true) {
                Task task = taskQueue.take();
                logger.log(Level.INFO, "Выполнение команды");
                Answer answer = readerOfCommands.startReadCommands(collection, path, task, databaseHandler);
                answerQueue.put(answer);
            }
        } catch (InterruptedException | SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при выполнении команды", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendAnswer() {
        try {
            while (true) {
                Answer answer = answerQueue.take();
                logger.log(Level.INFO, "Отправка ответа");
                try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                     ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
                    objectOutputStream.writeObject(answer);
                    byte[] buffer = byteArrayOutputStream.toByteArray();
                    DatagramPacket answerPacket = new DatagramPacket(buffer, buffer.length, address, packet.getPort());
                    socket.send(answerPacket);
                }
                logger.log(Level.INFO, "Ожидание новой команды");
            }
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, "Ошибка при отправке ответа", e);
        }
    }
}
