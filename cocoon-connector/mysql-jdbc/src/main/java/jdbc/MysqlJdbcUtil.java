package jdbc;

import java.sql.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * @program: cocoon
 * @description:
 * @author: fanjunwei
 * @create: 2022-09-26 19:29
 **/
public class MysqlJdbcUtil {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://dev-mysql.lls.com:3350/bigdata_risk_xxljob?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
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

        //// 指定执行语句
        //String sql = "SELECT id, name, age, sex, create_time, update_time FROM cc_test WHERE id=1";
        //// 4、执行查询语句
        //ResultSet resultSet = statement.executeQuery(sql);
        //// 5、获取执行结果
        //while (resultSet.next()){
        //    String name = resultSet.getString("name");
        //    System.out.println("name = " + name);
        //}

        // 关闭资源
        //resultSet.close();

        //createTable(connection);

        statement.close();
        connection.close();

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ExecutorService executorService = Executors.newWorkStealingPool();
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            int index = i;
            forkJoinPool.execute(() -> {
                System.out.println("Thread = "+ Thread.currentThread().getName() +", index = " + index);
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();

    }

    public static void createTable(Connection connection) throws SQLException {
        String createTableSql = "CREATE TABLE dop_business_manager_offline_ticket (企业名称 VARCHAR(255),业务类型 VARCHAR(255),资金方 VARCHAR(255),具体资方产品 VARCHAR(255),票据号码 VARCHAR(255),票据类型 VARCHAR(255),票面金额 VARCHAR(255),票面到期日 VARCHAR(255),票据承兑人 VARCHAR(255),申请日期 VARCHAR(255),放款状态 VARCHAR(255),放款日期 VARCHAR(255),放款金额 VARCHAR(255),到期期限 VARCHAR(255),计入业绩日期 VARCHAR(255),预估该笔业务规模 VARCHAR(255),预估资方给我司分润比例 VARCHAR(255),预估我司营收 VARCHAR(255),归属机构 VARCHAR(255),归属资产部门 VARCHAR(255),归属资金部门 VARCHAR(255),归属资产业务经理 VARCHAR(255),归属资金业务经理 VARCHAR(255),数据来源 VARCHAR(255),录入时间 VARCHAR(255));";

        PreparedStatement preparedStatement = connection.prepareStatement(createTableSql);
        //preparedStatement.executeUpdate();

        String querySql = "select * from information_schema.TABLES t where TABLE_NAME = 'dop_business_manager_offline_ticket'";
        ResultSet resultSet = preparedStatement.executeQuery(querySql);
        while (resultSet.next()){
            String name = resultSet.getString("table_name");
            System.out.println("table_name = " + name);
        }
    }


}
