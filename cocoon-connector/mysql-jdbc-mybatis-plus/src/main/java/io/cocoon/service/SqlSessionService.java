package io.cocoon.service;

import com.baomidou.mybatisplus.core.MybatisMapperRegistry;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.cocoon.db.mapper.CcTestDao;
import io.cocoon.db.mapper.CcTestMapper;
import io.cocoon.model.CcTestDO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class SqlSessionService {

    public static void main(String[] args) throws Exception {

        InputStream resourceAsStreamPlus = Resources.getResourceAsStream("mybatis_cfg.xml");
        SqlSessionFactory sqlSessionFactoryPlus = new MybatisSqlSessionFactoryBuilder().build(resourceAsStreamPlus);

        try(SqlSession sqlSessionPlus = sqlSessionFactoryPlus.openSession()){
            // ServiceImpl.baseMapper强依赖spring容器环境, 由Autowire 注入实例
            // 此处无spring容器环境，只能通过反射将BaseMapper注入到Dao（ServiceImpl）中
            BaseMapper<?> mapper = sqlSessionPlus.getMapper(CcTestMapper.class);
            CcTestDao ccTestDao = new CcTestDao();
            Class<CcTestDao> ccTestDaoClass = CcTestDao.class;
            Field baseMapperField = ccTestDaoClass.getSuperclass().getDeclaredField("baseMapper");
            baseMapperField.setAccessible(true);
            baseMapperField.set(ccTestDao, mapper);

            CcTestDO ccTestDO = new CcTestDO();
            ccTestDO.setName("test");
            ccTestDO.setAge(1);
            ccTestDO.setSex(true);
            ccTestDO.setCreateTime(LocalDateTime.now());
            ccTestDO.setUpdateTime(LocalDateTime.now());

            boolean b = ccTestDao.insertOne(ccTestDO);
            System.out.println("b = " + b);
            System.out.println("ccTestDO = " + ccTestDO);

        }

    }

}
