package lh.family.admin.controller;

import lh.base.support.model.DomainPage;
import lh.family.admin.controller.support.AjaxResponse;
import lh.family.admin.controller.support.BaseController;
import lh.family.admin.model.Leaveword;
import lh.family.admin.query.LeavewordQuery;
import lh.family.admin.service.ILeavewordService;
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
 * Created by lh on 2016/4/27.
 */
@Controller
@RequestMapping("leaveword")
public class LeavewordController extends BaseController {
    @Autowired
    private ILeavewordService leavewordService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(LeavewordQuery query, Model model) {
        DomainPage<Leaveword> domainPage = leavewordService.findByQueryItems(query.getRealPage(), query.getSize(), Sort.Direction.DESC, "id", query.asQueryItems());
        model.addAttribute("domainPage", domainPage);
        model.addAttribute("query", toJson(query));
        return "leaveword/list";
    }

    @ResponseBody
    @RequestMapping(value = "batch/delete", method = RequestMethod.POST)
    public AjaxResponse batchDelete(@RequestBody List<String> ids) {
        try {
            leavewordService.delete(leavewordService.findAll(getIdsList(ids)));
            return AjaxResponse.ok();
        } catch (Exception e) {
            return AjaxResponse.fail().msg(e.getMessage());
        }
    }
}
