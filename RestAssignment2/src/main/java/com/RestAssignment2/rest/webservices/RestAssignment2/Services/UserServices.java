package com.RestAssignment2.rest.webservices.RestAssignment2.Services;


import com.RestAssignment2.rest.webservices.RestAssignment2.MyClasses.User;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserServices {
    private static List<User> usersList = new ArrayList<>();
    static int count = usersList.size();

    UserServices() {
        usersList.add(new User(001, "Apoorva Garg", 22, "hey"));
        usersList.add(new User(002, "Jaswant Singh", 23, "hey"));
        usersList.add(new User(003, "Shubhanshi Tyagi", 20, "hey"));
        usersList.add(new User(004, "Daljit Kalsi", 23, "hey"));
        usersList.add(new User(005, "Yash Bhatia", 22, "hey"));
    }

    public static List<User> getAll() {
        return usersList;
    }


    public static User getOneUser(int id) {
        Iterator<User> iterator = usersList.iterator();
        while (iterator.hasNext()) {
            User usersModel = iterator.next();
            if (usersModel.getId() == id) {
                return usersModel;
            }
        }
        return null;
    }


    public static User addUser(User user) {
        if ((Integer) user.getId() == null)
            user.setId(count++);
        else {
            usersList.add(user);
        }
        return user;
    }

    public static User deleteUser(int id) {
        Iterator<User> iterator = usersList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}



