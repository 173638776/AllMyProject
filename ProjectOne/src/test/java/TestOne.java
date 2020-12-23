import com.icis.dao.RouteDao;
import com.icis.dao.impl.RouteDaoImpl;
import com.icis.pojo.Route;
import com.icis.pojo.Seller;
import com.icis.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * xiejiajian     2020/10/26 8:54
 */
public class TestOne {
    private RouteDao routeDao=new RouteDaoImpl();
    private JdbcTemplate jdbcTemplate=JDBCUtils.getJDBCTemplate();
    @Test
     public  void delJedis() {
        Jedis jedis=new Jedis();
        jedis.del("listCategory");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
    }


     public  void TestJDBCTemplate() {
        JdbcTemplate jdbcTemplate= JDBCUtils.getJDBCTemplate();
        String sql="select * from user ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
            System.out.println(list);
    }

    @Test
     public  void showJedis() {
        Jedis jedis=new Jedis();
        String s = jedis.get("listCategory");
        System.out.println(s);

    }




@Test
    public void  getRouteCount() {
        String sql="SELECT COUNT(*) FROM tab_route ";
    Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);
    System.out.println(integer);
}

@Test
 public  void xxx() {
        Integer rid=1;
    String sql="SELECT sname FROM tab_seller,tab_route WHERE rid=? AND tab_route.`sid`=tab_seller.`sid`";
    Seller seller = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), rid);
    System.out.println(seller.getSname());
}

@Test
 public  void test11() {

         String str="   a   a  b  b c  c";
         System.out.println(str.replaceAll(" ", ""));

  }
}
