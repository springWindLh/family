package lh.family.controller;

import lh.base.support.model.DomainPage;
import lh.family.controller.support.BaseController;
import lh.family.form.NewsForm;
import lh.family.model.News;
import lh.family.query.NewsQuery;
import lh.family.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

/**
 * Created by lh on 2016/5/7.
 */
@Controller
@RequestMapping("news")
public class NewsController extends BaseController {
    @Autowired
    private INewsService newsService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(NewsQuery query, Model model) {
        DomainPage<News> domainPage = newsService.findByQueryItems(query.getRealPage(), query.getSize(), Sort.Direction.DESC, "createdTime", query.asQueryItems());
        model.addAttribute("domainPage", domainPage);
        model.addAttribute("query", toJson(query));
        return "news/list";
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Optional<News> newsOptional = newsService.findOne(id);
        if (!isPresent(newsOptional)) {
            return RESOURCE_NOT_FOUND;
        }
        model.addAttribute("news", new NewsForm(newsOptional.get()));
        return "news/detail";
    }
}
