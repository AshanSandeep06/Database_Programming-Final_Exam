package util;

import db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author : Ashan Sandeep
 * @since : 0.1.0
 **/

public class CrudUtil {
    public static <T> T execute(String sql,Object... params) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for(int i=0; i< params.length; i++){
            stm.setObject((i+1),params[i]);
        }

        if(sql.startsWith("SELECT")){
            return (T) stm.executeQuery();
        }else{
            return (T) (Boolean)(stm.executeUpdate()>0);
        }
    }
}
