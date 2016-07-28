package lh.family.admin.controller;

import lh.base.support.model.DomainPage;
import lh.family.admin.controller.support.AjaxResponse;
import lh.family.admin.controller.support.BaseController;
import lh.family.admin.form.UserForm;
import lh.family.admin.model.User;
import lh.family.admin.query.UserQuery;
import lh.family.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by lh on 2016/4/6.
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(UserQuery query, Model model) {
        DomainPage<User> domainPage = userService.findByQueryItems(query.getRealPage(), query.getSize(), Sort.Direction.DESC, "id", query.asQueryItems());
        model.addAttribute("domainPage", domainPage);
        model.addAttribute("query", toJson(query));
        return "user/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "user/form";
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public AjaxResponse add(@RequestBody @Valid UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Optional<User> userOptional = userService.add(form.asUser());
            if (userOptional.get().getId() == getCurrentUser().getId()) {
                getRequest().getSession().setAttribute("userAvatar", userOptional.get().getAvatar());
            }
            return AjaxResponse.success().jumpUrl("/user/list");
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Optional<User> userOptional = userService.findOne(id);
        if (!isPresent(userOptional)) {
            return RESOURCE_NOT_FOUND;
        }
        model.addAttribute("userForm", new UserForm(userOptional.get()));
        return "user/form";
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public AjaxResponse edit(@RequestBody @Valid UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        Optional<User> userOptional = userService.findOne(form.getId());
        if (!isPresent(userOptional)) {
            return AjaxResponse.fail().jumpUrl(RESOURCE_NOT_FOUND);
        }
        try {
            User user = form.asUser();
            userService.update(user);
            getRequest().getSession().setAttribute("user", user);
            if (user.getId() == getCurrentUser().getId()) {
                getRequest().getSession().setAttribute("userAvatar", user.getAvatar());
            }
            return AjaxResponse.success().jumpUrl("/user/list");
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "batch/delete", method = RequestMethod.POST)
    public AjaxResponse batchDelete(@RequestBody List<String> ids) {
        try {
            userService.delete(userService.findAll(getIdsList(ids)));
            return AjaxResponse.ok();
        } catch (Exception e) {
            return AjaxResponse.fail().msg(e.getMessage());
        }
    }

    @RequestMapping(value = "change/password", method = RequestMethod.GET)
    public String changePassword() {
        return "user/change_password";
    }

}
