package lh.family.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lh.base.support.model.DomainPage;
import lh.family.controller.support.AjaxResponse;
import lh.family.controller.support.BaseController;
import lh.family.form.CommentForm;
import lh.family.model.Comment;
import lh.family.query.CommentQuery;
import lh.family.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by lh on 2016/5/15.
 */
@Controller
@RequestMapping("comment")
public class CommentController extends BaseController {
    @Autowired
    private ICommentService commentService;

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public AjaxResponse list(CommentQuery query, Model model) {
        DomainPage<Comment> domainPage = commentService.findAll(query.getRealPage(), query.getSize(), Sort.Direction.DESC, "id");
        return AjaxResponse.ok().data(JSON.toJSONString(domainPage, SerializerFeature.DisableCircularReferenceDetect));
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public AjaxResponse add(@RequestBody @Valid CommentForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Comment comment = form.asComment();
            comment.setUser(getCurrentUser());
            commentService.add(comment);
            return AjaxResponse.success().data(comment);
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }
}
