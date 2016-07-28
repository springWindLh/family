package lh.family.interceptor;

import lh.base.support.dao.QueryItem;
import lh.family.model.SingleMenu;
import lh.family.service.ISingleMenuService;
import org.springframework.data.domain.Sort;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lh on 2016/5/10.
 */
public class SingleMenuInterceptor extends HandlerInterceptorAdapter {
    private ISingleMenuService singleMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<QueryItem> queryItems = new ArrayList<>();
        queryItems.add(new QueryItem("status", SingleMenu.Status.ONLINE));
        List<SingleMenu> singleMenus = singleMenuService.findByQueryItems(0, 7, Sort.Direction.DESC, "weight", queryItems).getDomains();
        request.setAttribute("singleMenus", singleMenus);
        return true;
    }

    public ISingleMenuService getSingleMenuService() {
        return singleMenuService;
    }

    public void setSingleMenuService(ISingleMenuService singleMenuService) {
        this.singleMenuService = singleMenuService;
    }
}
