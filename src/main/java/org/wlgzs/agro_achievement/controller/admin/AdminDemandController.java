package org.wlgzs.agro_achievement.controller.admin;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.agro_achievement.base.BaseController;
import org.wlgzs.agro_achievement.entity.Demand;
import org.wlgzs.agro_achievement.entity.Type;
import org.wlgzs.agro_achievement.util.Result;

import java.util.List;


/**
 * @author:胡亚星
 * @createTime 2019-02-20 17:07
 * @description:
 **/

@RestController
@RequestMapping("/admin")
public class AdminDemandController extends BaseController {

    //查看所有需求(搜索)
    @RequestMapping(value = "/adminAdminDemand")
    public ModelAndView adminAdminDemand(Model model, @RequestParam(value = "current", defaultValue = "1") int current,
                                         @RequestParam(value = "limit", defaultValue = "8") int limit,
                                         @RequestParam(name = "findName", defaultValue = "") String findName) {
        Result result = iDemandService.adminDemandList(findName, current, limit);
        List<Demand> demandList = (List<Demand>) result.getData();

        model.addAttribute("demandList", demandList);
        model.addAttribute("TotalPages", result.getPages());//总页数
        model.addAttribute("Number", result.getCurrent());//当前页数
        model.addAttribute("findName", findName);
        return new ModelAndView("admin/adminDemand");
    }

    //去添加需求
    @RequestMapping(value = "/toAdminAddDemand")
    public ModelAndView toAdd(Model model) {
        //查询所有类型
        Result result1 = iTypeService.selectAllType();
        List<Type> typeList = (List<Type>) result1.getData();
        model.addAttribute("typeList", typeList);
        return new ModelAndView("admin/AddDemand");
    }

    //管理员添加需求
    @RequestMapping(value = "/adminAddDemand")
    public Result adminAddDemand(Model model ,Demand demand,String time){
        Result result = iDemandService.saveDemand(demand,time);
        return result;
    }

    //跳转到修改需求
    @RequestMapping("/toAdminEditDemand")
    public ModelAndView toEdit(Model model, Integer demandId) {
        Result result = iDemandService.demandDetails(demandId);
        Demand demand = (Demand) result.getData();
        model.addAttribute("demand", demand);
        return new ModelAndView("admin/adminEditDemand");
    }

    //修改需求
    @RequestMapping(value = "/adminEditDemand")
    public Result modifyDemand(Demand demand, Model model,String time) {
        Result result = iDemandService.modifyDemand(demand,time);
        return result;

    }

    //删除成果
    @RequestMapping(value = "/adminDeleteDemand")
    public Result adminDeleteDemand(Integer demandId, Model model) {
        Result result = iDemandService.deleteDemand(demandId);
        return result;
    }

}
