package DataBase;

import Creatures.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLDriver {

    public Map<String, List<Object>> requestToDataBase(String query, Boolean isGet){
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                "root", "root"); Statement statement = connection.createStatement()) {
            if (isGet) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    return resultSetToArrayList(resultSet);
                }
            }
            else {
                statement.execute(query);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Map<String, List<Object>> resultSetToArrayList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        Map<String, List<Object>> map = new HashMap<>(columns);
        for (int i = 1; i <= columns; ++i) {
            map.put(md.getColumnName(i), new ArrayList<>());
        }
        while (rs.next()) {
            for (int i = 1; i <= columns; ++i) {
                map.get(md.getColumnName(i)).add(rs.getObject(i));
            }
        }
        if (map.get("chatID").size() == 0){
            return null;
        }
        return map;
    }

    public void setNewInformation(Map<String, String> arrayInformation){
        String chatID = arrayInformation.get("chatID");
        String query = "";
        if (haveThisPlayer(chatID)){
            arrayInformation.remove("chatID");
            query = String.format("UPDATE userdb SET %s WHERE chatID = %s", generateSetForReque(arrayInformation), chatID);
        }
        else {
            query = String.format("INSERT INTO userdb SET %s", generateSetForReque(arrayInformation));
        }
        requestToDataBase(query, false);
    }

    public Map<String, List<Object>> getInformation(String chatID){
        String query = "select * from userdb where chatID = " + chatID;
        return requestToDataBase(query, true);
    }

    public boolean haveThisPlayer(String chatID){
        return !(requestToDataBase(String.format("select * from userdb where chatID = %s", chatID),
                true).get("chatID").size() == 0);
    }

    private String generateSetForReque(Map<String, String> arrayInformation){
        StringBuilder resultSetForm = new StringBuilder();
        for (String key:
                arrayInformation.keySet()) {
            resultSetForm.append(String.format("%s = '%s', ", key, arrayInformation.get(key)));
        }
        return resultSetForm.toString().substring(0, resultSetForm.length()-2);
    }
}
