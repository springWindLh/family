package lh.family.controller;


import lh.base.support.dao.QueryItem;
import lh.base.support.model.DomainPage;
import lh.family.controller.support.BaseController;
import lh.family.model.News;
import lh.family.query.support.Query;
import lh.family.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lh on 2016/4/19.
 */
@Controller
public class GlobalController extends BaseController {
    @Autowired
    private INewsService newsService;

    @RequestMapping(value = "resourceNotFound")
    public String resourceNotFound() {
        return "global/resourceNotFound";
    }

    @RequestMapping(value = {"/", "index"})
    public String index(Query query, Model model) {
        List<QueryItem> queryItems = new ArrayList<>();
        queryItems.add(new QueryItem("recommend", true));
        queryItems.add(new QueryItem("del", false));
        queryItems.add(new QueryItem("status", News.Status.ONLINE));
        DomainPage<News> recommendPage = newsService.findByQueryItems(0, 5, Sort.Direction.DESC, "weight", queryItems);
        model.addAttribute("recommendPage", recommendPage);
        queryItems = new ArrayList<>();
        queryItems.add(new QueryItem("del", false));
        queryItems.add(new QueryItem("status", News.Status.ONLINE));
        DomainPage<News> domainPage = newsService.findByQueryItems(0, 12, Sort.Direction.DESC, "id", queryItems);
        model.addAttribute("domainPage", domainPage);
        model.addAttribute("query", toJson(query));
        return "index";
    }

}
