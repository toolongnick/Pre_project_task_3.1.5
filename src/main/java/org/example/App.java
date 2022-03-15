package org.example;

import org.example.configuration.MyConfig;
import org.example.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        List<User> allUsers = communication.getAllUsers();
        System.out.println(allUsers);

        User addedUser = new User(3L, "James", "Brown", (byte) 51);
        communication.addUser(addedUser);

        User changedUser = new User(3L, "Thomas", "Shelby", (byte)51);
        communication.changeUser(changedUser);

        communication.deleteUser(3L);

    }
}
