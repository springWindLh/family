package lh.family.admin.controller;

import lh.base.support.model.DomainPage;
import lh.family.admin.controller.support.AjaxResponse;
import lh.family.admin.controller.support.BaseController;
import lh.family.admin.form.SingleDetailForm;
import lh.family.admin.form.SingleMenuForm;
import lh.family.admin.model.SingleDetail;
import lh.family.admin.model.SingleMenu;
import lh.family.admin.query.support.Query;
import lh.family.admin.service.ISingleDetailService;
import lh.family.admin.service.ISingleMenuService;
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
 * Created by lh on 2016/5/3.
 */
@Controller
@RequestMapping("singleMenu")
public class SingleMenuController extends BaseController {
    @Autowired
    private ISingleMenuService singleMenuService;
    @Autowired
    private ISingleDetailService singleDetailService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Query query, Model model) {
        DomainPage<SingleMenu> domainPage = singleMenuService.findAll(query.getRealPage(), query.getSize(), Sort.Direction.DESC, "id");
        model.addAttribute("domainPage", domainPage);
        model.addAttribute("query", toJson(query));
        return "singleMenu/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("singleMenuForm", new SingleMenuForm());
        return "singleMenu/menu_form";
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public AjaxResponse add(@RequestBody @Valid SingleMenuForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            singleMenuService.add(form.asSingleMenu());
            return AjaxResponse.success().jumpUrl("/singleMenu/list");
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Optional<SingleMenu> singleMenuOptional = singleMenuService.findOne(id);
        if (!singleMenuOptional.isPresent()) {
            return RESOURCE_NOT_FOUND;
        }
        model.addAttribute("singleMenuForm", new SingleMenuForm(singleMenuOptional.get()));
        return "singleMenu/menu_form";
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public AjaxResponse edit(@RequestBody @Valid SingleMenuForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        if (!singleMenuService.exists(form.getId())) {
            return AjaxResponse.fail().jumpUrl(RESOURCE_NOT_FOUND);
        }
        try {
            singleMenuService.update(form.asSingleMenu());
            return AjaxResponse.success().jumpUrl("/singleMenu/list");
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }

    @RequestMapping(value = "detail/add", method = RequestMethod.GET)
    public String detailAdd(@RequestParam String menuName, @RequestParam Long menuId, Model model) {
        model.addAttribute("singleDetailForm", new SingleDetailForm());
        model.addAttribute("menuName", menuName);
        model.addAttribute("menuId", menuId);
        return "singleMenu/detail_form";
    }

    @ResponseBody
    @RequestMapping(value = "detail/add", method = RequestMethod.POST)
    public AjaxResponse detailAdd(@RequestBody @Valid SingleDetailForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        Optional<SingleMenu> menuOptional = singleMenuService.findOne(form.getMenuId());
        try {
            SingleDetail singleDetail = form.asSingleDetail();
            menuOptional.ifPresent(singleDetail::setMenu);
            singleDetailService.add(singleDetail);
            return AjaxResponse.success().jumpUrl("/singleMenu/list");
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }

    @RequestMapping(value = "detail/edit/{id}", method = RequestMethod.GET)
    public String detailEdit(@PathVariable Long id, Model model) {
        Optional<SingleDetail> singleDetailOptional = singleDetailService.findOne(id);
        if (!singleDetailOptional.isPresent()) {
            return RESOURCE_NOT_FOUND;
        }
        SingleDetailForm form = new SingleDetailForm(singleDetailOptional.get());
        model.addAttribute("singleDetailForm", form);
        model.addAttribute("menuName", form.getMenuName());
        return "singleMenu/detail_form";
    }

    @ResponseBody
    @RequestMapping(value = "detail/edit", method = RequestMethod.POST)
    public AjaxResponse detailEdit(@RequestBody @Valid SingleDetailForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        if (!singleDetailService.exists(form.getId())) {
            return AjaxResponse.fail().jumpUrl(RESOURCE_NOT_FOUND);
        }
        Optional<SingleMenu> menuOptional = singleMenuService.findOne(form.getMenuId());
        try {
            SingleDetail singleDetail = form.asSingleDetail();
            menuOptional.ifPresent(singleDetail::setMenu);
            singleDetailService.update(singleDetail);
            return AjaxResponse.success().jumpUrl("/singleMenu/list");
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "batch/delete", method = RequestMethod.POST)
    public AjaxResponse batchDelete(@RequestBody List<String> ids) {
        try {
            singleMenuService.delete(singleMenuService.findAll(getIdsList(ids)));
            return AjaxResponse.ok();
        } catch (Exception e) {
            return AjaxResponse.fail().msg(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "change/status/{id}/{status}", method = RequestMethod.POST)
    public AjaxResponse changeStatus(@PathVariable Long id, @PathVariable String status) {
        Optional<SingleMenu> menuOptional = singleMenuService.findOne(id);
        if (!menuOptional.isPresent()) {
            return AjaxResponse.fail().jumpUrl(RESOURCE_NOT_FOUND);
        }
        SingleMenu singleMenu = menuOptional.get();
        if (singleMenu.getDetail() == null && SingleMenu.Status.ONLINE.name().equals(status)) {
            return AjaxResponse.fail().msg("请先编辑内容，再进行上线");
        }
        SingleMenu.Status menuStatus = SingleMenu.Status.valueOf(status);
        singleMenu.setStatus(menuStatus);
        try {
            singleMenuService.update(singleMenu);
            return AjaxResponse.ok().msg(singleMenu.getName() + menuStatus.getValue() + "成功");
        } catch (Exception e) {
            return AjaxResponse.fail().msg(e.getMessage());
        }
    }

}
