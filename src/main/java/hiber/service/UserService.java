package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    List<User> getAllUsersWithSameCar(String model, int series);

    User getUserByCar(String model, int series);
}
