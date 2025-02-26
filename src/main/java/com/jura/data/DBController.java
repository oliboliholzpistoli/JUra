package com.jura.data;

import com.jura.data.structures.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public List<Recipe> loadRecipes(){
        ArrayList<Recipe> recipes = new ArrayList<>();
        try (var con = DriverManager.getConnection(DB_URL);
            Statement stm = con.createStatement()) {

            //Get ingredient category list
            ResultSet ingredientCatRS = stm.executeQuery("SELECT * FROM IngredientCategory;");
            HashMap<Integer, IngredientCategory> ingredientCategories = new HashMap<>();
            while (ingredientCatRS.next()){
                ingredientCategories.put(Integer.parseInt(ingredientCatRS.getString("id")),new IngredientCategory(Integer.parseInt(ingredientCatRS.getString("id")),ingredientCatRS.getString("name")));
            }

            //Get ingredient list
            ResultSet ingredientRS = stm.executeQuery("SELECT * FROM Ingredient;");
            HashMap<Integer,Ingredient> ingredients = new HashMap<>();
            while (ingredientRS.next()){

            }

            ResultSet recipeRS = stm.executeQuery("SELECT * FROM Recipe;");

            while (recipeRS.next()){
                ResultSet stepRS = stm.executeQuery("SELECT * FROM Step WHERE RecipeID = "+ recipeRS.getString("id") +";");
                ArrayList<Step> steps = new ArrayList<>();
                //Get steps per recipe
                while (stepRS.next()){
                    steps.add(new Step(Integer.parseInt(stepRS.getString("id")),stepRS.getString("description"),Integer.parseInt(stepRS.getString("duration"))));
                }
                Recipe recipe = new Recipe(Integer.parseInt(recipeRS.getString("id")),
                        recipeRS.getString("title"),
                        Integer.parseInt(recipeRS.getString("duration")),
                        Integer.parseInt(recipeRS.getString("servings")),
                        steps);
                //Get ingredients
                ResultSet ingredientsRS = stm.executeQuery("SELECT * FROM Ingredient;");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void createObjectVoid(DBObject dbObject){
        try (var con = DriverManager.getConnection(DB_URL);
             Statement stm = con.createStatement()) {
            stm.executeUpdate(dbObject.getCreateString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public DBObject createObject(DBObject dbObject){
        ResultSet generatedKeys;
        try (var con = DriverManager.getConnection(DB_URL)){
             PreparedStatement stm = con.prepareStatement(dbObject.getCreateString(),Statement.RETURN_GENERATED_KEYS);
             stm.executeUpdate();
             generatedKeys = stm.getGeneratedKeys();
             generatedKeys.next();
             dbObject.setId(generatedKeys.getInt(1));
             return dbObject;
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
