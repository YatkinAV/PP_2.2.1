package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
/*
      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
*/
        Car carPorshe = new Car("porshe", 911);
        Car carBMW = new Car("BMW", 6);

        User user1 = new User("Vasya", "Lastname1", "user1@mail.ru");
        User user2 = new User("Petya", "Lastname2", "user2@mail.ru");

        user1.setCar(carPorshe);
        user2.setCar(carBMW);

        userService.add(user1);
        userService.add(user2);

      /*List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }*/


        List<User> usersByCar = userService.getAllUsersWithSameCar(carPorshe.getModel(), carPorshe.getSeries());
        for (User user : usersByCar) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println(userService.getUserByCar(carBMW.getModel(), carBMW.getSeries()));

        context.close();
    }
}
