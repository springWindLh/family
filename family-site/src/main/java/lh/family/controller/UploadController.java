package lh.family.controller;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Json;
import lh.family.controller.support.AjaxResponse;
import lh.family.controller.support.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by lh on 2016/5/5.
 */
@Controller
@RequestMapping("qiniu")
public class UploadController extends BaseController {
    //设置七牛账号的ACCESS_KEY和SECRET_KEY
    @Value("${qiniu_access_key}")
    private String accessKey;
    @Value("${qiniu_secret_key}")
    private String secretKey;
    //七牛域名
    @Value("${qiniu_domain}")
    private String domain;

    //要上传的空间名
    @Value("${qiniu_bucket_name}")
    private String bucketName;

    private static Auth auth;
    //创建上传对象
    private static UploadManager uploadManager;

    public Auth getAuth() {
        if (auth == null) {
            auth = Auth.create(accessKey, secretKey);
        }
        return auth;
    }

    public UploadManager getUploadManager() {
        if (uploadManager == null) {
            uploadManager = new UploadManager();
        }
        return uploadManager;
    }

    public String getUpToken() {
        return getAuth().uploadToken(bucketName);
    }

    @ResponseBody
    @RequestMapping(value = "upload/{type:(?:avatar|newsRoll)}", method = RequestMethod.POST)
    public AjaxResponse upload(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable String type) throws IOException {
        try {
            String suffix = getFileSuffix(file.getOriginalFilename());
            if (!suffix.equalsIgnoreCase(".JPEG") && !suffix.equalsIgnoreCase(".JPG") && !suffix.equalsIgnoreCase(".GIF")
                    && !suffix.equalsIgnoreCase(".PNG") && !suffix.equalsIgnoreCase(".BMP")) {
                return AjaxResponse.fail().msg("请上传图片文件");
            }
            return AjaxResponse.ok().data(getImageUrl(file, type));
        } catch (QiniuException e) {
            Response r = e.response;
            System.out.println(r.toString());
        }
        return AjaxResponse.fail();
    }

    private String getImageUrl(MultipartFile file, String type) throws IOException {
        String fileName = type + "/" + translateFileName(file.getOriginalFilename());
        Response rsp = getUploadManager().put(file.getBytes(), fileName, getUpToken());
        String imageUrl = domain + "/" + Json.decode(rsp.bodyString()).get("key").toString();
        return imageUrl;
    }

    public String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public String translateFileName(String fileName) {
        if (Optional.ofNullable(fileName).isPresent() && fileName.contains(".")) {
            return String.valueOf(UUID.randomUUID()).replaceAll("-", "") + getFileSuffix(fileName);
        } else {
            return fileName;
        }
    }
}
