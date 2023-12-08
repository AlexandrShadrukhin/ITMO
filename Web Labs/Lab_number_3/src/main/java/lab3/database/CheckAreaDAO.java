package lab3.database;

import lab3.ResultBean;

import java.sql.SQLException;
import java.util.Collection;

public interface CheckAreaDAO {
    void addNewResult(ResultBean result) throws SQLException;
    void updateResult(Long bus_id, ResultBean result) throws SQLException;
    ResultBean getResultById(Long result_id) throws SQLException;
    Collection<ResultBean> getAllResults() throws SQLException;
    void deleteResult(ResultBean result) throws SQLException;
    void clearResults() throws SQLException;
}
