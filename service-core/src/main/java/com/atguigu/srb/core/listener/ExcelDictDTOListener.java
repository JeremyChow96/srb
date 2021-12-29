package com.atguigu.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.srb.core.mapper.DictMapper;
import com.atguigu.srb.core.pojo.dto.ExcelDictDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {


    public ExcelDictDTOListener() {
    }

    public ExcelDictDTOListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }



    private DictMapper dictMapper;

    List<ExcelDictDTO> list = new ArrayList<>();

    static final int BATCH = 5;

    @Override
    public void invoke(ExcelDictDTO data, AnalysisContext analysisContext) {

        log.info("解析到一条记录:{}", data);
        list.add(data);
        if (list.size() >= BATCH) {
            saveData();
            list.clear();
        }

    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //当数据不足 在最后存储剩余在list中的数据
        saveData();
        log.info("解析全部完成");
    }

    private void saveData() {
        log.info("{} 条数据存储到数据库", list.size());
        //调用Mapper层的save方法
        dictMapper.insertBatch(list);

        log.info("{} 条数据存储到数据库成功", list.size());
    }
}
