package lh.family.admin.controller;

import lh.base.support.model.DomainPage;
import lh.family.admin.controller.support.AjaxResponse;
import lh.family.admin.controller.support.BaseController;
import lh.family.admin.form.PhotoForm;
import lh.family.admin.model.Photo;
import lh.family.admin.query.support.Query;
import lh.family.admin.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by lh on 2016/5/13.
 */
@Controller
@RequestMapping("photo")
public class PhotoController extends BaseController {
    @Autowired
    private IPhotoService photoService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Query query, Model model) {
        DomainPage<Photo> domainPage = photoService.findAll(query.getRealPage(), query.getSize(), Sort.Direction.DESC, "id");
        model.addAttribute("domainPage", domainPage);
        model.addAttribute("query", toJson(query));
        return "photo/list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add() {
        return "photo/upload";
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public AjaxResponse add(@Valid PhotoForm form, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResponse.fail().msg(result.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Photo photo = form.asPhoto();
            photo.setUser(getCurrentUser());
            photoService.add(photo);
            return AjaxResponse.success();
        } catch (Exception e) {
            return AjaxResponse.failure().detail(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "batch/delete", method = RequestMethod.POST)
    public AjaxResponse batchDelete(@RequestBody List<String> ids) {
        try {
            photoService.delete(photoService.findAll(getIdsList(ids)));
            return AjaxResponse.ok();
        } catch (Exception e) {
            return AjaxResponse.fail().msg(e.getMessage());
        }
    }
}
