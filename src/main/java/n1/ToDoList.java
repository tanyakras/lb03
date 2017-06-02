package n1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 14.04.2017.
 */
public class ToDoList {
    public static final String JDBC_H2_TEST = "jdbc:h2:~/test";
    private ArrayList<Smth> list = new ArrayList<>();
    private int count = 1;

    public ToDoList(){
    }

    public ToDoList(int n){
        for (int i = 0; i < n; i++) {
            list.add(new Smth("", count));
            count++;
        }
    }

    void add(String message) throws SQLException {
        //count++;
        //list.add(new Smth(message, count));
        try (Connection c = DriverManager.getConnection(JDBC_H2_TEST)){
         try(PreparedStatement ps = c.prepareStatement("insert into todo(text) values (?)")){
             ps.setString(1, message);
             ps.executeUpdate();
         }
        }
    }

    /*void delete(int id){
        for (Smth l1: list
                ) { if(l1.getId()==id){list.remove(l1);
            break;}

        }
    }*/
    void delete(int id) throws SQLException {
        try (Connection c = DriverManager.getConnection(JDBC_H2_TEST)){
            try(PreparedStatement ps = c.prepareStatement("delete from todo where id=?")){
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }

    List<Smth> view() throws SQLException {
//        for (Smth smth : list) {
//            System.out.println(smth.toString());
//        }
        List<Smth> list = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(JDBC_H2_TEST)){
            try(PreparedStatement ps = c.prepareStatement("select id, text from todo order by id")){
              try(ResultSet rs = ps.executeQuery()){
                  while (rs.next()){
                      int id = rs.getInt(1);
                      String text = rs.getString(2);
                      list.add(new Smth(text, id));
                  }
              }
            }
        }
        return list;
    }

    /*List<Smth> view(){
        return list;
    }*/

}
