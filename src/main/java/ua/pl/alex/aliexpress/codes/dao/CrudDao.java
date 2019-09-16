package ua.pl.alex.aliexpress.codes.dao;



import ua.pl.alex.aliexpress.codes.Entity.Entity;
import ua.pl.alex.aliexpress.codes.datasource.DataSourceHikariImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Admin on 01.04.2017.
 */
public abstract class CrudDao<T extends Entity<Integer>> implements Dao<Integer, T> {
    private Class<T> type;
    private DataSourceHikariImpl dataSource;
    private static final String SELECT_ALL="Select * from %s";
    private static final String FIND_BY_ID="Select * from %s where id=%d";
    private static final String DELETE_BY_ID="DELETE * from %s where id=%d";

    public CrudDao(Class<T> type) {
        this.type = type;
        this.dataSource= DataSourceHikariImpl.getInstance();
    }

    @Override
    public List getAll() {
        String sql=String.format(SELECT_ALL, "coupon");
        List result=null;
        try (Connection connection=dataSource.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(sql);
             ResultSet resultSet=preparedStatement.executeQuery()){
            result=readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException ex){}
        return result;
    }

    @Override
    public T getById(Integer id) {
        String sql=String.format(FIND_BY_ID, type.getSimpleName(),  id);

        List result=null;
        try (Connection connection=dataSource.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(sql);
             ResultSet resultSet=preparedStatement.executeQuery()){
            result=readAll(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (T)result.get(0);
    }

    @Override
    public void save(T entity) {
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = createInsertStatement(connection, entity)){
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                entity.setId(rs.getInt(1));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer key) {
        String sql=String.format(DELETE_BY_ID, type.getSimpleName(), key);
        try (Connection connection=dataSource.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T entity) {
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = createUpdateStatement(connection, entity)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected abstract PreparedStatement createUpdateStatement(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement createInsertStatement(Connection connection, T entity) throws SQLException;

    protected  abstract List<T> readAll(ResultSet resultSet) throws SQLException;
}
