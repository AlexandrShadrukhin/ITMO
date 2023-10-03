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
    private final ExecutorService readExecutor;
    private final ExecutorService taskExecutor;
    private final ExecutorService answerExecutor;
    private final LinkedBlockingQueue<Task> taskQueue;
    private final LinkedBlockingQueue<Answer> answerQueue;

    private final byte[] buffer = new byte[4096];

    private final Collection collection;
    private final DatabaseHandler databaseHandler;
    private final String path;

    private final ReaderOfCommands readerOfCommands = new ReaderOfCommands();

    private static final Logger logger = Logger.getLogger("logger");

    public Server(Collection collection, DatabaseHandler databaseHandler) throws SocketException, UnknownHostException, SQLException {
        this.socket = new DatagramSocket(8000);
        this.address = InetAddress.getByName("localhost");
        this.collection = collection;
        this.databaseHandler = databaseHandler;
        this.readExecutor = Executors.newFixedThreadPool(8);
        this.taskExecutor = Executors.newCachedThreadPool();
        this.answerExecutor = Executors.newFixedThreadPool(8);
        this.taskQueue = new LinkedBlockingQueue<>();
        this.answerQueue = new LinkedBlockingQueue<>();
        this.path = "";
    }

    public void runServer() throws IOException, ClassNotFoundException {
        while (true) {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            logger.log(Level.INFO, "Получение информации");
            socket.receive(packet);
            byte[] data = packet.getData();
            Task task;
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data))) {
                task = (Task) objectInputStream.readObject();
            }
            if (task.describe.contains("registration")){
                databaseHandler.setLogin(task.login);
            } if (task.describe.contains("auth")){
                databaseHandler.setLogin(task.login);
            }
            taskQueue.add(task);
            readExecutor.submit(() -> processTask(packet));
        }
    }

    private void processTask(DatagramPacket packet) {
        try {
            Task task = taskQueue.take();
            logger.log(Level.INFO, "Выполнение команды");
            taskExecutor.submit(() -> {
                Answer answer = null;
                try {
                    answer = readerOfCommands.startReadCommands(collection, path, task, databaseHandler);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                answerQueue.offer(answer);
                answerExecutor.submit(() -> sendAnswer(packet));
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendAnswer(DatagramPacket packet) {
        try {
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
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, "Ошибка при отправке ответа", e);
        }
    }
}
