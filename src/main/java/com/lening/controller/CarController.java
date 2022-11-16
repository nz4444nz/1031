package com.lening.controller;

import com.github.pagehelper.PageInfo;
import com.lening.ResultInfo;
import com.lening.entity.CarBean;
import com.lening.entity.CityBean;
import com.lening.entity.ColorBean;
import com.lening.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    @Resource
    private CarService carService;
    //全查 模糊查(购买时间，地址)
    @RequestMapping("/findAll")
    public String findAll(Model model, CarBean carBean,
                          @RequestParam(defaultValue = "1")Integer pageNum,
                          @RequestParam(defaultValue = "5")Integer pageSize){
        PageInfo<CarBean> pageInfo =  carService.findAll(carBean,pageNum,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("carBean",carBean);
        return "car_list";
    }
    //根据id删除车辆
    @RequestMapping("/deleteCar")
    public String deleteCar(Integer id){
        carService.deleteCar(id);
        return "redirect:findAll.do";
    }
    //去添加车辆页面  新增车辆
    @RequestMapping("/toAddCar")
    public String toAddCar(Model model){
        List<ColorBean> clist = carService.findC();
        List<CityBean> plist=carService.getCityListByPid(1);
        model.addAttribute("clist",clist);
        model.addAttribute("plist",plist);
        return "car_add";
    }
    @RequestMapping("/addCar")
    public String addCar(CarBean carBean){
        carService.addCar(carBean);
        return "redirect:findAll.do";
    }
    //全查城市
    @RequestMapping("/getCityListByPid")
    @ResponseBody
    public List<CityBean>getCityListByPid(Integer pid){
        return carService.getCityListByPid(pid);
    }
    //上传
    @RequestMapping("/fileUpload")
    @ResponseBody
    public ResultInfo fileUpload(HttpServletRequest request,MultipartFile filename){
        ResultInfo rs = null;
        try {
            String realPath = request.getServletContext().getRealPath("/upload/");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
            String format = simpleDateFormat.format(new Date());
            String originalFilename = filename.getOriginalFilename();
            String full = realPath + "/" + format + "/" + originalFilename;
            File file = new File(full);
            if (!file.exists()){
                file.mkdirs();
            }
            filename.transferTo(file);//
            String s = "/upload/" + format + "/" + originalFilename;
            rs = new ResultInfo(true,s);
            return rs;
        } catch (Exception e) {
            rs = new ResultInfo(false,"你上传失败啦");
            return rs;
        }
    }



}
