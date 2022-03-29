package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.model.User;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User psheshich = new User("Пшешич", "Хапчанский", (byte) 67);
        User fayka = new User("Файка", "Пронырска", (byte) 22);
        User carlich = new User("Карлич", "Воняйка", (byte) 88);
        User codich = new User("Кодыч", "Заипайко", (byte) 89);
        userService.saveUser(psheshich.getName(), psheshich.getLastName(), psheshich.getAge());
        userService.saveUser(fayka.getName(), fayka.getLastName(), fayka.getAge());
        userService.saveUser(carlich.getName(), carlich.getLastName(), carlich.getAge());
        userService.saveUser(codich.getName(), codich.getLastName(), codich.getAge());

        for (User u: userService.getAllUsers()) {
            System.out.println(u);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
