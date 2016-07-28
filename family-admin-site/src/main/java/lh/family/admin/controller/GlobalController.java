package lh.family.admin.controller;

import lh.family.admin.controller.support.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lh on 2016/4/19.
 */
@Controller
public class GlobalController extends BaseController {
    @RequestMapping(value = "resourceNotFound")
    public String resourceNotFound() {
        return "global/resourceNotFound";
    }

    @RequestMapping(value = {"/","admin"})
    public String index() {
        return "home";
    }
}
