package com.jura.data.structures;

public interface DBObject {
    String getCreateString();
    String getUpdateString();
    String getDeleteString();
    String getSelectString();
}
