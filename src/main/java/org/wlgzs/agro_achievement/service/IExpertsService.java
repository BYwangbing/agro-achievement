package org.wlgzs.agro_achievement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;
import org.wlgzs.agro_achievement.entity.Experts;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wlgzs.agro_achievement.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-01-19
 */
public interface IExpertsService extends IService<Experts> {

    //申请成为专家
    Result addExperts(HttpServletRequest request, String time, Experts experts,MultipartFile myFileName) throws FileNotFoundException;

    //查看（个人中心）专家信息
    Experts expertsUserDetails(HttpServletRequest request);

    //查看专家详情
    Experts expertsDetails(Integer expertsId);

    //前台查询所有专家（通过的）
    Result selectExperts(int current, int limit);

    //按添加时间查询专家（最新加入）
    Result selectExpertsByTime(int limit);

    //按点击量查询专家
    Result expertRanking(int limit);

    //专家推荐
    List<Experts> recommend(int limit);

    //搜索专家
    IPage<Experts> findName(String findName, int current, int limit);

    //按类型查询专家
    Result selectExpertsByType(String type,int current,int limit);

    /**
     * 管理员
     */
    //搜索专家
    Result findExpertsList(String findName, int current, int limit);

    //管理添加专家
    Result addAdminExperts(HttpSession session, HttpServletRequest request, String time, Experts experts, MultipartFile myFileName) throws FileNotFoundException;

    //修改专家信息
    Result modifyExperts(String time,Experts experts);

    //删除专家信息
    Result adminDeleteExpertsId(Integer expertsId);

    //按需求状态查询专家
    Result selectExpertsByCode(String statusCode,int current,int limit);

}
