class MySQLDatabase{ // Low Level Module
    public void saveToSQL(String data){
        System.out.println(" Executing SQL Query: INSERT INTO users VALUES('{" + data + "'})" );
    }
}

class MongoDBDatabase{ // Low Level Module
    public void saveToMongoDB(String data){
        System.out.println(" Executing MongoDB Function: db.users.insert({name: '" + data + "'})" );
    }
}

class UserService{ // High-Level Module
    private final MySQLDatabase sqlDB = new MySQLDatabase();
    private final MongoDBDatabase mongoDB = new MongoDBDatabase();

    public void storeUserToSQL(String user){
        // MySQL Specific code
        sqlDB.saveToSQL(user);
    }

    public void storeUserToMongo(String user){
        // MongoDB Specific code
        mongoDB.saveToMongoDB(user);
    }
}

public class DIPViolated {
      public static void main(String[] args) {
        UserService service = new UserService();
        service.storeUserToSQL("Peter");
        service.storeUserToMongo("Miles");
      }   
}
