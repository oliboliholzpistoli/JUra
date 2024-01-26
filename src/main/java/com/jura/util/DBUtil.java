package com.jura.util;

import java.util.List;

public class DBUtil {
    public static String generateCreateString(String table,List<String> fields,List<String> values){
        if (fields.size() == values.size()) {
            StringBuilder out = new StringBuilder("INSERT INTO " + table + "(");
            for (String field : fields) {
                out.append(field).append(",");
            }
            //Trim trailing comma
            out.deleteCharAt(out.length() - 1);
            out.append(") VALUES (");
            for (String value : values) {
                out.append(value).append(",");
            }
            out.deleteCharAt(out.length() - 1);
            out.append(");");
            return out.toString();
        } else {
            return "";
        }
    }

    public static String generateUpdateString(String table, List<String> identifierDefinitions, List<String> identifierValues,List<String> fields,List<String> values) {
        if (fields.size() == values.size() && identifierDefinitions.size() == identifierValues.size()) {
            StringBuilder out = new StringBuilder("UPDATE " + table + " SET ");
            for (int i = 0; i<fields.size();i++){
                out.append(fields.get(i)).append("=").append(values.get(i)).append(",");
            }
            //Trim trailing comma
            out.deleteCharAt(out.length() - 1);
            out.append("WHERE ");
            for (int i = 0; i<identifierDefinitions.size();i++) {
                out.append(identifierDefinitions.get(i)).append("=").append(identifierValues.get(i)).append("AND");
            }
            //Trim trailing AND
            out.delete(out.length()-3,out.length());
            out.append(";");
            return out.toString();
        } else {
            return "";
        }
    }

    public static String generateDeleteString(String table, List<String> identifierDefinitions, List<String> identifierValues){
        if (identifierDefinitions.size()==identifierValues.size()){
            StringBuilder out = new StringBuilder("DELETE FROM " + table + " WHERE ");
            for (int i = 0; i < identifierDefinitions.size();i++){
                out.append(identifierDefinitions.get(i)).append(" = ").append(identifierValues.get(i)).append(" AND ");
            }
            //Trim trailing AND
            out.delete(out.length()-5,out.length());
            out.append(";");
            return out.toString();
        }
        return "";
    }
}
