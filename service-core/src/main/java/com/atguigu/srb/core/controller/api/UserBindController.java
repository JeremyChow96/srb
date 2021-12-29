package com.atguigu.srb.core.controller.api;


import com.alibaba.fastjson.JSON;
import com.atguigu.common.result.R;
import com.atguigu.srb.base.util.JwtUtils;
import com.atguigu.srb.core.hfb.RequestHelper;
import com.atguigu.srb.core.pojo.vo.UserBindVO;
import com.atguigu.srb.core.service.UserBindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 用户绑定表 前端控制器
 * </p>
 *
 * @author Jeremy
 * @since 2021-12-17
 */
@Api(tags = "会员账号绑定")
@RestController
@RequestMapping("/api/core/userBind")
@Slf4j
public class UserBindController {


    @Resource
    private UserBindService userBindService;



    @ApiOperation("账户绑定提交数据")
    @PostMapping("/auth/bind")
    public R bind(@RequestBody UserBindVO userBindVO, HttpServletRequest request){
        // 从header中获取 token ，确保用户已登录,获取用户信息
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);

        //根据userId绑定用户信息
        String formStr= userBindService.commitBindUser(userBindVO,userId);
        return R.ok().data("formStr",formStr);

    }


    @ApiOperation("账户绑定异步回调")
    @PostMapping("/notify")
    public String notify(HttpServletRequest request) {
        Map<String, Object> paramMap = RequestHelper.switchMap(request.getParameterMap());
        log.info("用户账号绑定异步回调：" + JSON.toJSONString(paramMap));

        //校验签名
        if(!RequestHelper.isSignEquals(paramMap)) {
            log.error("用户账号绑定异步回调签名错误：" + JSON.toJSONString(paramMap));
            return "fail";
        }

        //修改绑定状态
        userBindService.notify(paramMap);
        return "success";
    }
}

