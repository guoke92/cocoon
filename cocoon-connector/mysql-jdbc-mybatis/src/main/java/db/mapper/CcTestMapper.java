package db.mapper;

import model.CcTestDO;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public interface CcTestMapper {

    CcTestDO selectById(Long id);
}
