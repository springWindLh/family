package lh.family.controller.support;

import com.alibaba.fastjson.JSON;
import lh.base.support.model.CanLogicDelDomain;
import lh.family.model.User;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by lh on 2016/4/8.
 */
public class BaseController {
    public static final String RESOURCE_NOT_FOUND = "resourceNotFound";

    private HttpServletRequest request;

    protected void setPageTitle(Model model, String title) {
        model.addAttribute("pageTitle", title);
    }

    protected void enableGoBack(Model model) {
        model.addAttribute("pageGoBack", true);
    }

    protected Object toJson(Object object) {
        return JSON.toJSON(object);
    }

    protected List<Long> getIdsList(List<String> ids) {
        return ids.stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected User getCurrentUser() {
        return (User) getRequest().getSession().getAttribute("user");
    }

    protected <T extends CanLogicDelDomain> boolean isPresent(Optional<T> domain) {
        return domain.isPresent() && !domain.get().getDel();
    }
}
