package lh.family.controller;

import lh.family.controller.support.AjaxResponse;
import lh.family.controller.support.BaseController;
import lh.family.form.UserForm;
import lh.family.model.User;
import lh.family.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by lh on 2016/4/6.
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Optional<User> userOptional = userService.findOne(id);
        if (!isPresent(userOptional)) {
            return RESOURCE_NOT_FOUND;
        }
        model.addAttribute("userForm", new UserForm(userOptional.get()));
        return "user/detail";
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
            return AjaxResponse.success();
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }

}
