package lh.family.serviceImpl;

import lh.base.support.dao.IBaseDao;
import lh.base.support.serviceImpl.BaseServiceImpl;
import lh.family.dao.ICommentDao;
import lh.family.model.Comment;
import lh.family.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
