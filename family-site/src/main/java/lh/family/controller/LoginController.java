package lh.family.controller;

import lh.base.support.util.Md5Util;
import lh.family.controller.support.AjaxResponse;
import lh.family.controller.support.BaseController;
import lh.family.form.UserForm;
import lh.family.model.User;
import lh.family.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by lh on 2016/4/8.
 */
@Controller
public class LoginController extends BaseController {
    private static final String AVATAR_NAME = "avatar.jpg";
    @Autowired
    private IUserService userService;

    //七牛域名
    @Value("${qiniu_domain}")
    private String qiniuDomain;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(String jumpUrl, Model model) {
        if (Optional.ofNullable(jumpUrl).isPresent() && !jumpUrl.isEmpty()) {
            model.addAttribute("jumpUrl", jumpUrl);
        }
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public AjaxResponse login(@RequestBody @Valid UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        Optional<User> userOptional = userService.verifyUser(form.getName(), form.getPassword());
        if (userOptional.isPresent()) {
            getRequest().getSession().setAttribute("user", userOptional.get());
            if (Optional.ofNullable(form.getJumpUrl()).isPresent() && !form.getJumpUrl().isEmpty()) {
                return AjaxResponse.success().jumpUrl(form.getJumpUrl());
            } else {
                return AjaxResponse.success().jumpUrl("/index");
            }
        }
        return AjaxResponse.fail().msg("用户名或密码错误");
    }

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public AjaxResponse register(@RequestBody @Valid UserForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            User user = new User();
            user.setName(form.getName());
            user.setPassword(Md5Util.encrypt32(form.getPassword()));
            user.setRole(User.Role.USER);
            user.setAvatar(qiniuDomain + "/" + AVATAR_NAME);
            userService.add(user);
            getRequest().getSession().setAttribute("user", user);
            if (Optional.ofNullable(form.getJumpUrl()).isPresent() && !form.getJumpUrl().isEmpty()) {
                return AjaxResponse.success().jumpUrl(form.getJumpUrl());
            } else {
                return AjaxResponse.success().jumpUrl("/index");
            }
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }

    @RequestMapping(value = "login/out", method = RequestMethod.GET)
    public String loginOut() {
        return "redirect:/login";
    }
}
