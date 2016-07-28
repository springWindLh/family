package lh.family.admin.controller;

import lh.base.support.model.DomainPage;
import lh.family.admin.controller.support.AjaxResponse;
import lh.family.admin.controller.support.BaseController;
import lh.family.admin.model.Comment;
import lh.family.admin.query.support.Query;
import lh.family.admin.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by lh on 2016/5/18.
 */
@Controller
@RequestMapping("comment")
public class CommentController extends BaseController {
    @Autowired
    private ICommentService commentService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Query query, Model model) {
        DomainPage<Comment> domainPage = commentService.findAll(query.getRealPage(), query.getSize(), Sort.Direction.DESC, "id");
        model.addAttribute("domainPage", domainPage);
        model.addAttribute("query", toJson(query));
        return "comment/list";
    }

    @ResponseBody
    @RequestMapping(value = "batch/delete", method = RequestMethod.POST)
    public AjaxResponse batchDelete(@RequestBody List<String> ids) {
        try {
            commentService.delete(commentService.findAll(getIdsList(ids)));
            return AjaxResponse.ok();
        } catch (Exception e) {
            return AjaxResponse.fail().msg(e.getMessage());
        }
    }
}
