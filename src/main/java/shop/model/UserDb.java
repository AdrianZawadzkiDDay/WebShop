package shop.model;

import java.util.*;

public class UserDb {
    private static UserDb db;

    private List<User> users;

    private UserDb(){
        this.users = new LinkedList<>();
        users.add(new User("admin@admin.com", "pass", "admin", Arrays.asList("admin", "user")));
        users.add(new User("user@user.com", "pass", "user", Collections.singletonList("user")));
    }

    public static UserDb getInstance(){
        if(db == null){
            db = new UserDb();
        }
        return db;
    }

    public Optional<User> checkLogin(final String username, final String password){
        return users.stream()
                .filter(user -> user.getUserName().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }

    public boolean checkIfUserExist(final String userName, final String email){
        return users.stream().anyMatch(u -> u.getUserName().equals(userName) || u.getEmail().equals(email));
    }

    public User addUser(final String userName, final String email, final String password, final List<String> roles){
       final User newUser = new User(email, password, userName, roles);
       users.add(newUser);
       return newUser;

    }

}
