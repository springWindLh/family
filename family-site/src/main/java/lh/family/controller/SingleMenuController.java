package lh.family.controller;

import lh.family.form.SingleDetailForm;
import lh.base.support.model.DomainPage;
import lh.family.controller.support.BaseController;
import lh.family.model.SingleDetail;
import lh.family.model.SingleMenu;
import lh.family.query.support.Query;
import lh.family.service.ISingleDetailService;
import lh.family.service.ISingleMenuService;
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
@RequestMapping("singleMenu")
public class SingleMenuController extends BaseController{
    @Autowired
    private ISingleMenuService singleMenuService;
    @Autowired
    private ISingleDetailService singleDetailService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Query query, Model model) {
        DomainPage<SingleMenu> domainPage = singleMenuService.findAll(query.getRealPage(), query.getSize(), Sort.Direction.DESC, "weight");
        model.addAttribute("domainPage", domainPage);
        model.addAttribute("query", toJson(query));
        return "singleMenu/list";
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public String detailEdit(@PathVariable Long id, Model model) {
        Optional<SingleDetail> singleDetailOptional = singleDetailService.findOne(id);
        if (!singleDetailOptional.isPresent()) {
            return RESOURCE_NOT_FOUND;
        }
        SingleDetailForm form = new SingleDetailForm(singleDetailOptional.get());
        model.addAttribute("singleDetail", form);
        model.addAttribute("menuName", form.getMenuName());
        return "singleMenu/detail";
    }
}
