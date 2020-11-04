package kz.lib_mob_client.utils;

import kz.lib_mob_client.entity.UserInfo;
import kz.lib_mob_client.entity.Users;

public interface DataManager {

    void saveUser(Users users);
    Users getUsers();

    void saveUserInfo(UserInfo userInfo);
    UserInfo getUserInfo();

}
