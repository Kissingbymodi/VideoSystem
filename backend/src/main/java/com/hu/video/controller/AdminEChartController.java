package com.hu.video.controller;

import com.hu.video.service.IAdminEChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminEChartController {

    @Autowired
    private IAdminEChartService adminEChartService;

    @RequestMapping("getFilmData")
    @ResponseBody
    public List<String> getFilmData() {
        return adminEChartService.getFilmData();
    }

    @RequestMapping("getData")
    @ResponseBody
    public List<Integer> getData() {
        return adminEChartService.getData();

    }
}
