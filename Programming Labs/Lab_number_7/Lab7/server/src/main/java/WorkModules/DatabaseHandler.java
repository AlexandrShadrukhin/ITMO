package WorkModules;

import Data.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Vector;

public class DatabaseHandler {
    String login;
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", "s367629", "ETeo63o0dZehIqIR");

    public DatabaseHandler() throws SQLException {
    }

    PreparedStatement REGISTRATION = connection.prepareStatement("insert into s367629.user (login, password) values(? ,  ?)");
    PreparedStatement CHECK_USER = connection.prepareStatement("select count(*) from s367629.user where (login=? and password=?)");
    PreparedStatement INSERT_WORKER = connection.prepareStatement("insert into s367629.workers (id, owner, worker_name, coordinate_x, coordinate_y, creation_date, salary, position, status, person_height, eye_color, hair_color, country, location_x, location_y, location_z, name) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    PreparedStatement SELECT_ALL_WORKERS = connection.prepareStatement("select * from s367629.workers;");


    public void registrate(String login, String password) throws SQLException {
        try {
            REGISTRATION.setString(1, login);
            REGISTRATION.setString(2, password);
            REGISTRATION.execute();
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean checkUser(String login, String password) throws SQLException {
        try {
            CHECK_USER.setString(1, login);
            CHECK_USER.setString(2, password);
            ResultSet result = CHECK_USER.executeQuery();
            while (result.next()) {
                return result.getInt("count") == 1;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }
    public void setLogin(String login) {    this.login = login;
    }
    public String getLogin() {    return login;
    }

    public void putWorker(Worker worker) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO workers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, worker.getId());
        preparedStatement.setString(2, worker.getOwner());
        preparedStatement.setString(3, worker.getName());
        preparedStatement.setFloat(4, (float) worker.getCoordinates().getX());
        preparedStatement.setFloat(5, (float) worker.getCoordinates().getY());
        if (worker.getCreationDate() != null) {
            preparedStatement.setString(6, String.valueOf(worker.getCreationDate()));
        } else {
            preparedStatement.setNull(6, Types.TIMESTAMP);
        }
        preparedStatement.setInt(7, worker.getSalary());

        if (worker.getEndDate() != null) {
            preparedStatement.setString(8, String.valueOf(worker.getEndDate()));
        } else {
            preparedStatement.setNull(8, Types.TIMESTAMP);
        }
        if (worker.getPosition() != null) {
            preparedStatement.setString(9, String.valueOf(worker.getPosition()));
        } else {
            preparedStatement.setNull(9, Types.VARCHAR);
        }
        if (worker.getStatus() != null) {
            preparedStatement.setString(10, String.valueOf(worker.getStatus()));
        } else {
            preparedStatement.setNull(10, Types.VARCHAR);
        }
        if (worker.getPerson() != null) {
            preparedStatement.setDouble(11, worker.getPerson().getHeight());
            if (worker.getPerson().getEyeColor() != null) {
                preparedStatement.setString(12, String.valueOf(worker.getPerson().getEyeColor()));
            } else {
                preparedStatement.setNull(12, Types.VARCHAR);
            }
            if (worker.getPerson().getHairColor() != null) {
                preparedStatement.setString(13, String.valueOf(worker.getPerson().getHairColor()));
            } else {
                preparedStatement.setNull(13, Types.VARCHAR);
            }
            if (worker.getPerson().getNationality() != null) {
                preparedStatement.setString(14, String.valueOf(worker.getPerson().getNationality()));
            } else {
                preparedStatement.setNull(14, Types.VARCHAR);
            }
            if (worker.getPerson().getLocation() != null) {
                preparedStatement.setFloat(15, worker.getPerson().getLocation().getX());
                preparedStatement.setFloat(16, worker.getPerson().getLocation().getY());
                preparedStatement.setDouble(17, worker.getPerson().getLocation().getZ());
                preparedStatement.setString(18, worker.getPerson().getLocation().getName());
            } else {
                preparedStatement.setNull(15, Types.FLOAT);
                preparedStatement.setNull(16, Types.FLOAT);
                preparedStatement.setNull(17, Types.DOUBLE);
                preparedStatement.setNull(18, Types.VARCHAR);
            }
        } else {
            preparedStatement.setNull(11, Types.DOUBLE);
            preparedStatement.setNull(12, Types.VARCHAR);
            preparedStatement.setNull(13, Types.VARCHAR);
            preparedStatement.setNull(14, Types.VARCHAR);
            preparedStatement.setNull(15, Types.FLOAT);
            preparedStatement.setNull(16, Types.FLOAT);
            preparedStatement.setNull(17, Types.DOUBLE);
            preparedStatement.setNull(18, Types.VARCHAR);
        }
        preparedStatement.execute();
    }
    public void putAllWorkers(Vector<Worker> list) throws SQLException {
        clearAllWorkers();
        for (Worker worker : list) {
            putWorker(worker);
        }
    }

    public void clearAllWorkers() throws SQLException {
        connection.prepareStatement("DELETE FROM workers").execute();
    }
    public Vector<Worker> getAllWorker() throws SQLException {
        Vector<Worker> list = new Vector<>();
        ResultSet resultSet = SELECT_ALL_WORKERS.executeQuery();
        while (resultSet.next()) {
            String creationDate = resultSet.getString("creation_date");
            if (resultSet.wasNull()) {
                creationDate = null;
            }
            Integer salary = resultSet.getInt("salary");
            if (resultSet.wasNull()) {
                salary = null;
            }
            String endDate = resultSet.getString("end_date");
            if (resultSet.wasNull()) {
                endDate = null;
            }
            String position = (resultSet.getString("position"));
            if (resultSet.wasNull()) {
                position = null;
            }
            Status status = Status.valueOf(resultSet.getString("status"));
            if (resultSet.wasNull()) {
                status = null;
            }
            Float personHeight = resultSet.getFloat("person_height");
            if (resultSet.wasNull()) {
                personHeight = null;
            }
            String eyeColor = (resultSet.getString("eye_color"));
            if (resultSet.wasNull()) {
                eyeColor = null;
            }
            String hairColor = (resultSet.getString("hair_color"));
            if (resultSet.wasNull()) {
                hairColor = null;
            }
            String country = (resultSet.getString("country"));
            if (resultSet.wasNull()) {
                country = null;
            }
            Integer locationX = resultSet.getInt("location_x");
            if (resultSet.wasNull()) {
                locationX = null;
            }
            Float locationY = resultSet.getFloat("location_y");
            if (resultSet.wasNull()) {
                locationY = null;
            }
            Float locationZ = resultSet.getFloat("location_z");
            if (resultSet.wasNull()) {
                locationZ = null;
            }
            String locationName = resultSet.getString("name");
            if (resultSet.wasNull()) {
                locationName = null;
            }
            list.add(new Worker(resultSet.getInt("id"),
                    resultSet.getString("owner"),
                    resultSet.getString("worker_name"),
                    new Coordinates(resultSet.getFloat("coordinate_x"),
                            resultSet.getFloat("coordinate_y")),
                    creationDate != null ? LocalDateTime.parse(creationDate) : null,
                    salary,
                    endDate != null ? ZonedDateTime.parse(endDate) : null,
                    position != null ? Position.valueOf(position) : null,
                    status,
                    personHeight != null || eyeColor != null || hairColor != null || country != null || locationX != null || locationY != null || locationZ != null || locationName != null ? new Person((double) personHeight, EyeColor.valueOf(eyeColor), HairColor.valueOf(hairColor), Country.valueOf(country), new Location(locationX, locationY, locationZ, locationName)) : null));
        }
        return list;
    }

}
