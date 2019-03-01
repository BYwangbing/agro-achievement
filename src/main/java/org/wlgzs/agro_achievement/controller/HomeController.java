package org.wlgzs.agro_achievement.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.agro_achievement.base.BaseController;
import org.wlgzs.agro_achievement.entity.*;
import org.wlgzs.agro_achievement.util.Result;

import java.util.List;

/**
 * @author:胡亚星
 * @createTime 2019-02-14 17:02
 * @description:
 **/
@Controller
@RequestMapping("/HomeController")
public class HomeController extends BaseController {

    //首页数据
    @RequestMapping("/home")
    public ModelAndView home(Model model){
        //首页技术供给
        List<Achievement> achievementList = iAchievementService.selectAchieveByTime();
        model.addAttribute("achievementList", achievementList);

        //首页技术需求
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status_code", "1");
        Page page = new Page(1, 10);
        IPage<Demand> iPage = iDemandService.page(page, queryWrapper);
        List<Demand> demandList = iPage.getRecords();
        model.addAttribute("demandList", demandList);

        //成功案例
        Result result = iCaseService.selectExample(1, 10);
        List<Example> exampleList = null;
        if (result.getCode() == 0) {
            exampleList = (List<Example>) result.getData();
        }
        model.addAttribute("exampleList", exampleList);

        //专家推荐
        Result result1 = iExpertsService.selectExperts(1, 10);
        List<Experts> expertsList = null;
        if (result1.getCode() == 0) {
            expertsList = (List<Experts>) result1.getData();
        }
        model.addAttribute("expertsList", expertsList);

        //机构推荐
        List<Organization> organizationList = iOrganizationService.selectAllOrganization(1, 10);
        model.addAttribute("organizationList", organizationList);

        //公告类型（新闻中心，交易活动，政策中心）
        List<Announcement> newsList = (List<Announcement>) iAnnouncementService.selectAnnouncement("", 1, 10).getData();
        List<Announcement> tradingList = (List<Announcement>) iAnnouncementService.selectAnnouncement("", 1, 10).getData();
        List<Announcement> policyList = (List<Announcement>) iAnnouncementService.selectAnnouncement("", 1, 10).getData();
        model.addAttribute("newsList", newsList);
        model.addAttribute("tradingList", tradingList);
        model.addAttribute("policyList", policyList);

        return new ModelAndView("index");
    }

    //成果小首页
    @RequestMapping("/AchievementHome")
    public ModelAndView AchievementHome(Model model) {
        //成果所有分类
        Result result1 = iTypeService.selectAllType();
        List<Type> typeList = (List<Type>) result1.getData();
        model.addAttribute("typeList", typeList);

        //最新发布
        List<Achievement> achievementTimeList = iAchievementService.selectAchieveByTime();
        model.addAttribute("achievementTimeList", achievementTimeList);

        //排行榜
        Result result = iAchievementService.rankingAchievement(1, 10);
        List<Achievement> achievementRankingList = (List<Achievement>) result.getData();
        model.addAttribute("achievementRankingList", achievementRankingList);

        //推荐
        List<Achievement> hotAchievement = iAchievementService.hotAchievement();
        model.addAttribute("hotAchievement", hotAchievement);
        return new ModelAndView("AchievementHome");
    }


    //专家小首页
    @RequestMapping(value = "/ExpertsHome")
    public ModelAndView ExpertsHome(Model model) {
        //所有专家分类
        Result result1 = iTypeService.selectAllType();
        List<Type> typeList = (List<Type>) result1.getData();
        model.addAttribute("typeList", typeList);

        //最新专家
        Result result = iExpertsService.selectExpertsByTime(8);
        List<Experts> expertsTimeList = (List<Experts>) result.getData();
        model.addAttribute("expertsTimeList", expertsTimeList);

        //专家排行
        Result result2 = iExpertsService.expertRanking(8);
        List<Experts> expertsRankingList = (List<Experts>) result.getData();
        model.addAttribute("expertsRankingList", expertsRankingList);
        System.out.println("expertsRankingList=="+expertsRankingList);
        //专家推荐

        return new ModelAndView("ExpertsHome");
    }

    //机构小首页
    @RequestMapping(value = "/OrganizationHome")
    public ModelAndView OrganizationHome(Model model) {

        //查询所有机构类别
        List<OrganizationType> list = (List<OrganizationType>) iOrganizationTypeService.selectAllOrganizationType().getData();
        model.addAttribute("OrganizationTypeList", list);

        //最新加入的机构
        List<Organization> organizationTimeList = iOrganizationService.selectOrganizationByTime();
        model.addAttribute("organizationTimeList",organizationTimeList);

        //机构排行
        List<Organization> organizationRankingList = iOrganizationService.rankingOrganization(1,10);
        model.addAttribute("organizationRankingList",organizationRankingList);

        return new ModelAndView("OrganizationHome");
    }

    //供需首页数据
    @RequestMapping(value = "/SupplyHome")
    public ModelAndView SupplyHome(Model model){

        //成果排行榜
        Result result = iAchievementService.rankingAchievement(1, 10);
        List<Achievement> achievementRankingList = (List<Achievement>) result.getData();
        model.addAttribute("achievement", achievementRankingList);

        //专家排行
        Result result1 = iExpertsService.expertRanking(10);
        List<Experts> expertsRankingList = (List<Experts>) result1.getData();
        model.addAttribute("experts", expertsRankingList);

        //首页技术需求
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status_code", "1");
        Page page = new Page(1, 10);
        IPage<Demand> iPage = iDemandService.page(page, queryWrapper);
        List<Demand> demandList = iPage.getRecords();
        model.addAttribute("demand", demandList);

        //机构排行
        List<Organization> organizationRankingList = iOrganizationService.rankingOrganization(1,10);
        model.addAttribute("organization",organizationRankingList);

        return new ModelAndView("SupplyHome");
    }

    //政策首页
    @RequestMapping(value = "/PolicyHome")
    public ModelAndView PolicyHome(Model model){
        //新闻中心
        Result result1 = iAnnouncementService.selectAnnouncement("新闻中心",1,10);
        List<Announcement> NewsCenter = (List<Announcement>) result1.getData();
        model.addAttribute("NewsCenter",NewsCenter);
        //交易活动
        Result result2 = iAnnouncementService.selectAnnouncement("交易活动",1,10);
        List<Announcement> TradingActivity = (List<Announcement>) result2.getData();
        model.addAttribute("TradingActivity",TradingActivity);
        //政策中心
        Result result3 = iAnnouncementService.selectAnnouncement("政策中心",1,10);
        List<Announcement> PolicyCenter = (List<Announcement>) result3.getData();
        model.addAttribute("PolicyCenter",PolicyCenter);

        return new ModelAndView("PolicyHome");
    }

    //查询成功案例（显示的）
    @GetMapping("/selectExample")
    public ModelAndView selectExample(Model model,@RequestParam(value = "current", defaultValue = "1") Integer current,
                                      @RequestParam(value = "limit", defaultValue = "8") Integer limit){
        Result result = iCaseService.selectExample(current,limit);
        List<Example> exampleList = (List<Example>) result.getData();
        if (exampleList != null) {
            model.addAttribute("msg","查询成功！");
        }else{
            model.addAttribute("msg","暂无数据！");
        }
        model.addAttribute("exampleList",exampleList);
        model.addAttribute("TotalPages", result.getPages());//总页数
        model.addAttribute("Number", result.getCurrent());//当前页数
        return new ModelAndView("ExampleList");
    }

    //查看专家信息
    @RequestMapping(value = "/expertsDetails")
    public ModelAndView expertsDetails(Model model,Integer expertsId) {
        Experts experts = iExpertsService.expertsDetails(expertsId);
        model.addAttribute("experts", experts);//专家信息
        if (experts == null) {
            model.addAttribute("msg", "不存在！");
        }
        return new ModelAndView("expertsDetails");
    }

    //前台查询所有专家（通过的）
    @RequestMapping(value = "/selectExperts")
    public ModelAndView selectExperts(Model model,@RequestParam(value = "current", defaultValue = "1") int current,
                                      @RequestParam(value = "limit", defaultValue = "8") int limit) {
        Result result = iExpertsService.selectExperts(current, limit);
        List<Experts> expertsList = (List<Experts>) result.getData();

        model.addAttribute("expertsList",expertsList);
        model.addAttribute("TotalPages", result.getPages());//总页数
        model.addAttribute("Number", result.getCurrent());//当前页数

        return new ModelAndView("expertsList");
    }

    //前台查询所有机构
    @RequestMapping(value = "/selectAllOrganization")
    public ModelAndView selectAllOrganization(Model model, @RequestParam(value = "current", defaultValue = "1") int current,
                                              @RequestParam(value = "limit", defaultValue = "8") int limit) {
        List<Organization> organizationList = iOrganizationService.selectAllOrganization(current, limit);
        if(organizationList != null){
            model.addAttribute("msg","查询成功！");
        }else{
            model.addAttribute("msg","查询成功！");
        }
        model.addAttribute("organizationList", organizationList);
        return new ModelAndView("OrganizationList");
    }

    //前台按类型查询机构
    @RequestMapping(value = "/selectAchieveByType")
    public ModelAndView selectOrganizationByType(Model model,@RequestParam(value = "type",defaultValue = "") String type,@RequestParam(value = "current", defaultValue = "1") int current,
                                                 @RequestParam(value = "limit", defaultValue = "8") int limit){
        Result result = iOrganizationService.selectOrganizationByType(type, current, limit);
        List<Organization> organizationList = (List<Organization>) result.getData();

        model.addAttribute("organizationList",organizationList);
        model.addAttribute("TotalPages", result.getPages());//总页数
        model.addAttribute("Number", result.getCurrent());//当前页数

        //查询所有机构类别
        List<OrganizationType> list = (List<OrganizationType>) iOrganizationTypeService.selectAllOrganizationType().getData();
        model.addAttribute("OrganizationTypeList",list);

        return new ModelAndView("OrganizationList");
    }

    //查看机构详情
    @RequestMapping(value = "/organizationDetails")
    public ModelAndView organizationDetails(Model model,Integer organizationId){
        Organization organization = iOrganizationService.getById(organizationId);
        model.addAttribute("organization",organization);
        return new ModelAndView("OrganizationDetails");
    }


}
