package com.wj.hsqldb.service;

import com.wj.hsqldb.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by wenjing.liu on 2021/3/9 in J1.
 * 通过TransactionTemplate来处理事务
 */
@Configuration
@PropertySource("classpath:/config/jdbc.properties")
public class BookManageTransactionTemplateService {
    @Value("${jdbc.table}")
    private String jdbcTable = "book";
    //1.从xml文件中获取DataSourceTransactionManager
    @Resource
    private DataSourceTransactionManager platformTransactionManager;
    private TransactionTemplate transactionTemplate;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void createTransactionManager() {
        //2.定义事务的传播行为以及隔离级别
        transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //3.调用 transactionTemplate.execute执行业务逻辑
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                createBookTableSQL(platformTransactionManager.getDataSource());
            }
        });
    }

    /**
     * 通过事务管理
     *
     * @param book
     * @return
     */
    public int addBook(Book book) {
        int result = transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus transactionStatus) {
                return addBookSQL(book);
            }
        });
        return result;
    }


    /**
     * 通过事务管理
     *
     * @return
     */
    public List<Book> getBook() {
        List<Book> books = transactionTemplate.execute(new TransactionCallback<List<Book>>() {
            @Override
            public List<Book> doInTransaction(TransactionStatus transactionStatus) {
                return getBookSQL();
            }
        });
        return books;
    }

    /**
     * 原创建table的业务逻辑
     *
     * @param source
     */
    private void createBookTableSQL(DataSource source) {
        jdbcTemplate = new JdbcTemplate(source);
        System.out.println("BookManagerJdbcService 初始化 自动创建表!!!");
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s " +
                "(id INTEGER PRIMARY KEY , name VARCHAR(50) NOT NULL, price DOUBLE, online DATE)", jdbcTable);
        jdbcTemplate.execute(sql);
        System.out.println(String.format("BookManagerJdbcService 创建 %s 表", jdbcTable));
    }


    /**
     * 同{@link BookManagerJdbcService#addBook(Book)}
     *
     * @param book
     * @return
     */
    private int addBookSQL(Book book) {
        //1.查询
        String query = String.format("SELECT * FROM %s ", jdbcTable);
        List<Book> books = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Book.class));
        //2.在总行数的基础上在加1，得到新数据的id
        int id = books == null ? 1 : books.size() + 1;
        //3.插入新的数据
        String insert = String.format("INSERT INTO %s \n" +
                "(id, name, price, online)\n" +
                "VALUES (%d , '%s', %f,'%s' )", jdbcTable, id, book.name, book.price, book.online);
        int result = jdbcTemplate.update(insert);
        System.out.println(String.format("%s已经成功加入数据库,目前数据库总共有%d条数据 ", book.name, result));
        return result;
    }

    /**
     * 同{@link BookManagerJdbcService#getBook()}
     *
     * @return
     */
    private List<Book> getBookSQL() {
        String sql = String.format("SELECT * FROM %s ", jdbcTable);
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return books;
    }

    public static void main(String[] args) {
        commitPlatformTransaction();
    }

    /**
     * 测试DataSourceTransactionManager的使用
     */
    private static void commitPlatformTransaction() {

        //1.从xml文件中获取DataSourceTransactionManager
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:config/application-context-trans.xml");
        DataSourceTransactionManager manager = applicationContext.getBean(DataSourceTransactionManager.class);
        //2.定义事务的传播行为以及隔离级别
        TransactionTemplate transactionTemplate = new TransactionTemplate(manager);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //3.调用 transactionTemplate.execute执行业务逻辑
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                insertBook(manager.getDataSource());
            }
        });
        queryBook(manager.getDataSource());
    }

    /**
     * 事务处理
     *
     * @param source
     */
    private static void insertBook(DataSource source) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(source);
        String jdbcTable = "book";
        List<Book> books = queryBook(source);
        //在总行数的基础上在加1，得到新数据的id
        int id = books == null ? 1 : books.size() + 1;
        System.out.println("要插入的book id为 = " + id);
        // 插入新的数据
        String insert = String.format("INSERT INTO %s \n" +
                "(id, name, price, online)\n" +
                "VALUES (%d , '%s', %f,'%s' )", jdbcTable, id, "PlatformTransaction", 20.0f, "2020-09-09");
        int result = jdbcTemplate.update(insert);
        System.out.println("update的行为  = " + result);
    }

    private static List<Book> queryBook(DataSource source) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(source);
        String jdbcTable = "book";
        String createTable = String.format("CREATE TABLE IF NOT EXISTS %s " +
                "(id INTEGER PRIMARY KEY , name VARCHAR(50) NOT NULL, price DOUBLE, online DATE)", jdbcTable);
        jdbcTemplate.execute(createTable);
        String sql = String.format("SELECT * FROM %s ", jdbcTable);
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        System.out.println(String.format("目前有%d本书", books == null ? 0 : books.size()));
        return books;
    }

    /**
     * 测试使用TransactionTemplate
     */
    private static void commitTransactionTemplate() {
        //从xml文件中获取DataSourceTransactionManager
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:config/application-context-trans.xml");
        DataSourceTransactionManager platformTransactionManager = applicationContext.getBean(DataSourceTransactionManager.class);

        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //使用execute来自动实现事务管理，自动实现成功之后提交，失败之后回滚
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                insertBook(platformTransactionManager.getDataSource());
            }
        });

//        transactionTemplate.execute(new TransactionCallback<Object>() {
//            @Override
//            public Object doInTransaction(TransactionStatus transactionStatus) {
//                return null;
//            }
//        });
    }
}
