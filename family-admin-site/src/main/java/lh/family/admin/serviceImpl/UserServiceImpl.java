package lh.family.admin.serviceImpl;

import lh.base.support.dao.IBaseDao;
import lh.base.support.serviceImpl.BaseServiceImpl;
import lh.base.support.util.Md5Util;
import lh.family.admin.dao.IUserDao;
import lh.family.admin.model.User;
import lh.family.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by lh on 2016/4/8.
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {
    private static final String NAME_REPEAT = "用户名已经存在";
    @Autowired
    private IUserDao userDao;

    @Override
    public IBaseDao getDao() {
        return userDao;
    }

    @Override
    public Optional<User> verifyUser(String name, String password) {
        List<User> users = userDao.findByFieldsAndValues("name", name, "password", Md5Util.encrypt32(password), "role", User.Role.ADMIN, "del", false);
        if (users.size() > 0) {
            return Optional.ofNullable(users.get(0));
        }
        return Optional.empty();
    }

    @Override
    public boolean isExistedName(String name) {
        List<User> users = userDao.findByFieldsAndValues("name", name);
        return users.size() > 0;
    }

    @Override
    public Optional<User> add(User user) {
        if (isExistedName(user.getName())) {
            throw new IllegalArgumentException(NAME_REPEAT);
        }
        return super.add(user);
    }

    @Override
    public Optional<User> update(User user) {
        List<User> users = userDao.findByFieldsAndValues("name", user.getName());
        if (users.size() > 0 && users.get(0).getId() != user.getId()) {
            throw new IllegalArgumentException(NAME_REPEAT);
        }
        return super.update(user);
    }
}
