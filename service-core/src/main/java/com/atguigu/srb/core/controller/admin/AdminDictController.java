package com.atguigu.srb.core.controller.admin;


import com.alibaba.excel.EasyExcel;
import com.atguigu.common.exception.BusinessException;
import com.atguigu.common.result.R;
import com.atguigu.common.result.ResponseEnum;
import com.atguigu.srb.core.pojo.dto.ExcelDictDTO;
import com.atguigu.srb.core.pojo.entity.Dict;
import com.atguigu.srb.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author Jeremy
 * @since 2021-12-17
 */

@Api(tags = "数据字典管理")
//@CrossOrigin
@RestController
@RequestMapping("/admin/core/dict")
@Slf4j
public class AdminDictController {

    @Autowired
    private DictService dictService;


    @ApiOperation("Excel批量导入")
    @PostMapping("/import")
    public R batchImport(
            @ApiParam(value = "Excel数据字典文件", required = true)
            @RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            dictService.importData(inputStream);
            return R.ok().message("数据字典数据批量导入成功");
        } catch (Exception e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }
    }

    @ApiOperation("Excel数据导出")
    @GetMapping("/export")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("mydict", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class).sheet("数据字典").doWrite(dictService.listDictData());
    }



    @ApiOperation("根据上级Id获取子节点数据")
    @GetMapping("/listByParentId/{parentId}")
    public  R listByParentId(@ApiParam(value = "上级节点id",required = true)
                             @PathVariable Long parentId){
        List<Dict> list = dictService.listByParentId(parentId);
        return  R.ok().data("list",list);

    }
}

