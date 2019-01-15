package com.leting.util.datasource;

import org.springframework.util.Assert;

public class DatabaseContextHolder {
	private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<DatabaseType>();

    public static void setDatabaseType(DatabaseType DatabaseType) {
        Assert.notNull(DatabaseType, "DatabaseType cannot be null");
        contextHolder.set(DatabaseType);
        
    }
 
    public static DatabaseType getDatabaseType() {
        return (DatabaseType) contextHolder.get();
    }
 
    public static void clearDatabaseType() {
        contextHolder.remove();
    }
}
