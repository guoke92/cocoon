import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.mysql.cj.jdbc.Driver;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;

import java.util.List;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-09-27 16:57
 **/
public class MysqlJdbcUtil {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/cocoon";
    private static String username = "root";
    private static String password = "123456";

    public static void main(String[] args) throws JsonProcessingException {

        EmbeddedDatabaseFactory embeddedDatabaseFactory = new EmbeddedDatabaseFactory();
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(Driver.class);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        List<CcTestDO> query = jdbcTemplate.query("SELECT id, name, age, sex, create_time, update_time FROM cc_test WHERE id=? ", new BeanPropertyRowMapper<>(CcTestDO.class), 1L);

        System.out.println("query = " + new JsonMapper().writeValueAsString(query));
    }

}