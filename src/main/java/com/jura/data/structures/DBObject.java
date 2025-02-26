package com.jura.data.structures;

public interface DBObject {
    void setId(int id);
    String getCreateString();
    String getUpdateString();
    String getDeleteString();
    String getSelectString();
}
