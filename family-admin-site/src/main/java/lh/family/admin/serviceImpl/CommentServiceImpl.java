package lh.family.admin.serviceImpl;

import lh.base.support.dao.IBaseDao;
import lh.base.support.serviceImpl.BaseServiceImpl;
import lh.family.admin.model.Comment;
import lh.family.admin.service.ICommentService;
import lh.family.admin.service.IUserService;
import lh.family.admin.dao.ICommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * Created by lh on 2016/4/8.
 */
@Service
@Transactional
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements ICommentService {
    @Autowired
    private ICommentDao commentDao;

    @Override
    public IBaseDao getDao() {
        return commentDao;
    }
}
