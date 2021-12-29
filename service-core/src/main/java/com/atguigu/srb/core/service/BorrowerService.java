package com.atguigu.srb.core.service;

import com.atguigu.srb.core.pojo.entity.Borrower;
import com.atguigu.srb.core.pojo.vo.BorrowerVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 借款人 服务类
 * </p>
 *
 * @author Jeremy
 * @since 2021-12-17
 */
public interface BorrowerService extends IService<Borrower> {

    void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId);

    Integer getStatusByUserId(@Param("userId") Long userId);
}
