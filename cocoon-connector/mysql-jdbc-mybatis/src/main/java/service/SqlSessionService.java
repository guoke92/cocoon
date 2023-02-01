package service;

import db.mapper.CcTestMapper;
import model.CcTestDO;
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

        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis_cfg.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            CcTestMapper mapper = sqlSession.getMapper(CcTestMapper.class);
            CcTestDO ccTestDO = mapper.selectById(1L);
            System.out.println("ccTestDO = " + ccTestDO);
        }

    }

}
