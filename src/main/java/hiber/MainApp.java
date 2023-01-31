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

      User userOne = new User("Cezarius", "Juliy", "cj@mail.com");
      User userTwo = new User("Billy", "Gates", "big_boss@mail.com");
      User userThree = new User("Wally", "West", "double_w@mail.com");

      userOne.setCar(new Car("Ferrari", 123));
      userTwo.setCar(new Car("Ford", 456));
      userThree.setCar(new Car("Volkswagen", 789));

      userService.add(userOne);
      userService.add(userTwo);
      userService.add(userThree);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      System.out.println("------------------------------------------------------------\n");

      System.out.println(userService.getUserFromCarsModel("Ford", 456));

      System.out.println("\n------------------------------------------------------------\n");

      System.out.println(userService.getUserFromCarsModel("Ferrari", 123));

      context.close();
   }
}
