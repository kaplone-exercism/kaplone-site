package models;

public class LaserPair {

    private String user1;
    private String user2;


    public LaserPair(String user1, String user2){

        this.user1 = user1;
        this.user2 = user2;
    }

    @Override
    public String toString(){
        return user1 + " <-- VS --> " + user2;
    }

}