package jdbc;

import java.sql.*;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-09-26 19:29
 **/
public class MysqlJdbcUtil {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/cocoon";
    private static String username = "root";
    private static String password = "123456";

    static{
        try {
            // 1、加载数据库驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {

        }
    }

    public static void main(String[] args) throws Exception {

        // 2、获取数据连接
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3、获取会话
        Statement statement = connection.createStatement();

        // 指定执行语句
        String sql = "SELECT id, name, age, sex, create_time, update_time FROM cc_test WHERE id=1";
        // 4、执行查询语句
        ResultSet resultSet = statement.executeQuery(sql);
        // 5、获取执行结果
        while (resultSet.next()){
            String name = resultSet.getString("name");
            System.out.println("name = " + name);
        }

        // 关闭资源
        resultSet.close();
        statement.close();
        connection.close();

    }


}
