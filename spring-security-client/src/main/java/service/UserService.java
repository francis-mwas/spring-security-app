package service;


import entity.User;
import model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);
}
