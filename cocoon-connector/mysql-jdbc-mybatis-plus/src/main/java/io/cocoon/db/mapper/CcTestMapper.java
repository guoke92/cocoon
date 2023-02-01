package io.cocoon.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cocoon.model.CcTestDO;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public interface CcTestMapper extends BaseMapper<CcTestDO> {

    CcTestDO selectById(Long id);
}
