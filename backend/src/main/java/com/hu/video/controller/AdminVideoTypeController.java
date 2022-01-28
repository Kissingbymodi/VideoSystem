package com.hu.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hu.video.entity.TVideotype;
import com.hu.video.service.IAdminVideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminVideoTypeController {

    @Autowired
    private IAdminVideoTypeService adminVideoTypeService;

    @RequestMapping(value = "searchVideoType", method = RequestMethod.GET)
    public PageInfo<TVideotype> searchVideoType(int pageNum, int pageSize, String typeName) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //视频记录
        List<TVideotype> videoTypes = adminVideoTypeService.getVideoTypeByName(typeName);
        //获得 视频分页
        PageInfo<TVideotype> vidoeTypeInfo = new PageInfo<TVideotype>(videoTypes);
        return vidoeTypeInfo;
    }

    @RequestMapping(value = "categoryPageInfo", method = RequestMethod.GET)
    public PageInfo<TVideotype> categoryPageInfo(int pageNum, int pageSize) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //视频记录
        List<TVideotype> videoTypes = adminVideoTypeService.categoryList();
        //获得 视频分页
        PageInfo<TVideotype> vidoeTypeInfo = new PageInfo<TVideotype>(videoTypes);
        return vidoeTypeInfo;
    }

    @RequestMapping("addcategory")
    public String addVideoType(@RequestBody TVideotype videotype) {
        boolean flag = adminVideoTypeService.addVideoType(videotype);
        return flag ? "200" : "404";
    }

    @RequestMapping("getCategory")
    public TVideotype getCategory(Long id) {
        return adminVideoTypeService.getCategoryById(id);
    }

    @RequestMapping("deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        boolean flag = adminVideoTypeService.deleteCategoryById(id);
        return flag ? "success" : "fail";
    }

}
