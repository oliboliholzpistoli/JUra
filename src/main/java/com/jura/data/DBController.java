package com.jura.data;

import com.jura.data.structures.DBObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBController {
    private static final String TABLES_PATH = "src/main/resources/tables.json";
    private static String DB_URL;

    public DBController() {
        DB_URL = "jdbc:h2:~/JUra";
    }

    public DBController(String dbUrl) {
        DB_URL = dbUrl;
    }

    //Create tables if they don't exist already
    public void initialize(){
        //Creates a DB in the User directory
        FileReader fr;
        try (var con = DriverManager.getConnection(DB_URL);
             Statement stm = con.createStatement()){

            //Read JSON File for table queries
            fr = new FileReader(TABLES_PATH);
            JSONTokener tk = new JSONTokener(fr);
            JSONArray tables = new JSONArray(tk);

            //Get existing tables from database
            ResultSet rs = stm.executeQuery("SHOW TABLES");

            //Read existing tables into local variable to loop through it multiple times
            ArrayList<String> existingTables = new ArrayList<>();
            while (rs.next()){
                existingTables.add(rs.getString("TABLE_NAME"));
            }
            //Create non-existent tables in the database
            for (int i = 0;i < tables.length();i++){
                JSONObject table = tables.getJSONObject(i);
                String key = table.keys().next();
                String val = table.getString(key);
                boolean exists = false;
                for (String existingTable : existingTables){
                    if (existingTable.equalsIgnoreCase(key)){
                        exists = true;
                        break;
                    }
                }
                if (!exists){
                    stm.executeUpdate(val);
                }
            }

        } catch (FileNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createObject(DBObject dbObject){
        try (var con = DriverManager.getConnection(DB_URL);
             Statement stm = con.createStatement()) {
            stm.executeUpdate(dbObject.getCreateString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateObject(DBObject dbObject){
        try (var con = DriverManager.getConnection(DB_URL);
             Statement stm = con.createStatement()) {
            stm.executeUpdate(dbObject.getCreateString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteObject(DBObject dbObject){
        try (var con = DriverManager.getConnection(DB_URL);
             Statement stm = con.createStatement()) {
            stm.executeUpdate(dbObject.getCreateString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void wipe(){
        try (var con = DriverManager.getConnection(DB_URL);
             Statement stm = con.createStatement()) {
            stm.executeUpdate("DROP ALL OBJECTS DELETE FILES;\n" +
                    "SHUTDOWN;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
