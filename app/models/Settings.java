package models;

public class Settings {

    private static List<String> users;
    private static String key;

    public static List<String> getUsers() {
        return users;
    }

    public static void setUsers(String users) {
        this.users = new ArrayList<>();
        for (String s : users.split(",")){
            this.users.add(s.trim());
        }
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        this.key = key;
    }
}