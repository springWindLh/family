package lh.family.admin.controller;

import lh.family.admin.controller.support.AjaxResponse;
import lh.family.admin.controller.support.BaseController;
import lh.family.admin.model.User;
import lh.family.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
    public String loginPage() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public AjaxResponse login(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        Optional<User> userOptional = userService.verifyUser(user.getName(), user.getPassword());
        if (userOptional.isPresent()) {
            getRequest().getSession().setAttribute("user", userOptional.get());
            getRequest().getSession().setAttribute("userAvatar", userOptional.get().getAvatar() == null ? qiniuDomain + "/" + AVATAR_NAME : userOptional.get().getAvatar());
            getRequest().getSession().setAttribute("defaultAvatar", qiniuDomain + "/" + AVATAR_NAME);
            return AjaxResponse.ok().jumpUrl("home");
        }
        return AjaxResponse.fail().msg("用户名或密码错误");
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "login/out", method = RequestMethod.GET)
    public String loginOut() {
        return "redirect:/login";
    }
}
