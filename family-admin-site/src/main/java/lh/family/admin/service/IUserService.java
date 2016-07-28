package lh.family.admin.service;


import lh.base.support.service.IBaseService;
import lh.family.admin.model.User;

import java.util.Optional;

/**
 * Created by lh on 2016/4/8.
 */
public interface IUserService extends IBaseService<User> {
    Optional<User> verifyUser(String name, String password);
    boolean isExistedName(String name);
}
