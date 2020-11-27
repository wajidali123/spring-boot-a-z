package org.johnpc.boot;

import org.johnpc.boot.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class UserDaoService {
    private static List users = new LinkedList();
    private static int userCount = 3;

    static {
        users.add(new User(1, "Wajid", LocalDateTime.now()));
        users.add(new User(2, "John", LocalDateTime.now()));
        users.add(new User(3, "Ali", LocalDateTime.now()));
    }

    public List<User> getAll(){
        return users;
    }

    public User saveUser(User user) {
        if(user.getId()==null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findUser(int id) {
        for (int i=0; i< users.size(); i++) {
            User user = (User) users.get(i);
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
}
