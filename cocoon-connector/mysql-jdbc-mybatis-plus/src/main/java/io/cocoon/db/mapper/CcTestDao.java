package io.cocoon.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.cocoon.model.CcTestDO;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class CcTestDao extends ServiceImpl<CcTestMapper, CcTestDO>{

    public boolean insertOne(CcTestDO ccTestDO){
        return this.save(ccTestDO);
    }

    public CcTestDO selectById(long l) {
        return this.lambdaQuery().eq(CcTestDO::getId, l).one();
    }
}
