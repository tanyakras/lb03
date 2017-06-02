package n1;

/**
 * Created by admin on 14.04.2017.
 */
public class Smth {
    final String what;
    final int id;

    public Smth(String msg, int id){
        what = msg;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getWhat() {
        return what;
    }

    @Override
    public String toString() {
        return "to do no."+id+", message:"+what;
    }
}