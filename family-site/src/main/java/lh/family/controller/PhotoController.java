package lh.family.controller;

import lh.base.support.model.DomainPage;
import lh.family.controller.support.BaseController;
import lh.family.model.Photo;
import lh.family.query.support.Query;
import lh.family.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
