package lh.family.controller;

import lh.base.support.model.DomainPage;
import lh.family.controller.support.AjaxResponse;
import lh.family.controller.support.BaseController;
import lh.family.form.LeavewordForm;
import lh.family.model.Leaveword;
import lh.family.query.LeavewordQuery;
import lh.family.query.support.Query;
import lh.family.service.ILeavewordService;
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
 * Created by lh on 2016/5/21.
 */
@Controller
@RequestMapping("leaveword")
public class LeavewordController extends BaseController {
    @Autowired
    private ILeavewordService leavewordService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(LeavewordQuery query, Model model) {
        DomainPage<Leaveword> domainPage = leavewordService.findByQueryItems(query.getRealPage(), query.getSize(), Sort.Direction.DESC, "createdTime",query.asQueryItems());
        model.addAttribute("domainPage", domainPage);
        model.addAttribute("query", toJson(query));
        return "leaveword/list";
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public AjaxResponse add(@RequestBody @Valid LeavewordForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Leaveword leaveword = form.asLeaveword();
            leaveword.setUser(getCurrentUser());
            leavewordService.add(leaveword);
            return AjaxResponse.success();
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }
}
