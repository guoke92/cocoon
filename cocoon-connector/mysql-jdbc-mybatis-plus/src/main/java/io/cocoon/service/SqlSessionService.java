package io.cocoon.service;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import io.cocoon.db.mapper.CcTestMapper;
import io.cocoon.model.CcTestDO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 **/
public class SqlSessionService {

    public static void main(String[] args) throws IOException {

        //InputStream resourceAsStream = Resources.getResourceAsStream("mybatis_cfg.xml");
        //SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //
        //try(SqlSession sqlSession = sqlSessionFactory.openSession()){
        //    CcTestMapper mapper1 = sqlSession.getMapper(CcTestMapper.class);
        //    CcTestDO ccTestDO1 = mapper1.selectById(1L);
        //    System.out.println("ccTestDO1 = " + ccTestDO1);
        //}

        InputStream resourceAsStreamPlus = Resources.getResourceAsStream("mybatis_cfg.xml");
        SqlSessionFactory sqlSessionFactoryPlus = new MybatisSqlSessionFactoryBuilder().build(resourceAsStreamPlus);
        try(SqlSession sqlSessionPlus = sqlSessionFactoryPlus.openSession()){
            //QueryWrapper<CcTestDO> queryWrapper = new QueryWrapper<>();
            //queryWrapper.eq("id", 2L);
            //BaseMapper mapper = sqlSessionPlus.getMapper(CcTestMapper.class);
            CcTestDO ccTestDO = sqlSessionPlus.getMapper(CcTestMapper.class).selectById(1L);
            System.out.println("ccTestDO = " + ccTestDO);
        }

    }

}
