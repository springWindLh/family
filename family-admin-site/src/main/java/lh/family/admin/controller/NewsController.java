package lh.family.admin.controller;

import lh.base.support.model.DomainPage;
import lh.family.admin.controller.support.AjaxResponse;
import lh.family.admin.controller.support.BaseController;
import lh.family.admin.form.NewsForm;
import lh.family.admin.model.News;
import lh.family.admin.model.User;
import lh.family.admin.query.NewsQuery;
import lh.family.admin.service.INewsService;
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
 * Created by lh on 2016/4/19.
 */
@Controller
@RequestMapping("news")
public class NewsController extends BaseController {
    @Autowired
    private INewsService newsService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(NewsQuery query, Model model) {
        DomainPage<News> domainPage = newsService.findByQueryItems(query.getRealPage(), query.getSize(), Sort.Direction.DESC, "id", query.asQueryItems());
        model.addAttribute("domainPage", domainPage);
        model.addAttribute("query", toJson(query));
        return "news/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("newsForm", new NewsForm());
        return "news/form";
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public AjaxResponse add(@RequestBody @Valid NewsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            News news = form.asNews();
            news.setUser(getCurrentUser());
            newsService.add(news);
            return AjaxResponse.success().jumpUrl("/news/list");
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Optional<News> newsOptional = newsService.findOne(id);
        if (!isPresent(newsOptional)) {
            return RESOURCE_NOT_FOUND;
        }
        model.addAttribute("newsForm", new NewsForm(newsOptional.get()));
        return "news/form";
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public AjaxResponse edit(@RequestBody @Valid NewsForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        Optional<News> newsOptional = newsService.findOne(form.getId());
        if (!isPresent(newsOptional)) {
            return AjaxResponse.fail().jumpUrl(RESOURCE_NOT_FOUND);
        }
        try {
            User user = newsOptional.get().getUser();
            News news = form.asNews();
            news.setUser(user);
            newsService.update(news);
            return AjaxResponse.success().jumpUrl("/news/list");
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "batch/delete", method = RequestMethod.POST)
    public AjaxResponse batchDelete(@RequestBody List<String> ids) {
        try {
            newsService.delete(newsService.findAll(getIdsList(ids)));
            return AjaxResponse.ok();
        } catch (Exception e) {
            return AjaxResponse.fail().msg(e.getMessage());
        }
    }
}
