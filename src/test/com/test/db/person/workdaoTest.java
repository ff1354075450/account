package test.com.test.db.person;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import com.test.db.person.Workdao;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import sun.rmi.runtime.Log;

/**
 * Workdao Tester.
 *
 * @author <Authors name>
 * @since <pre>一月 18, 2017</pre>
 * @version 1.0
 */
public class workdaoTest {

    @Before
    public void before() throws Exception {
        System.out.println("before");
    }

    @After
    public void after() throws Exception {
        System.out.println("after");
    }

    /**
     *
     * Method: addworker(String worker, String boss)
     *
     */
    @Test
    public void testAddworker() throws Exception {
        Workdao.addworker("xx","ff");
    }

    /**
     *
     * Method: update(String worker, float workload)
     *
     */
    @Test
    public void testUpdate() throws Exception {
        Workdao.update("xx","ff", (float) 2);

    }

    /**
     *
     * Method: query(String name)
     *
     */
    @Test
    public void testQuery() throws Exception {
        JSONArray res = com.test.db.person.Workdao.query("xx","ff");
        System.out.println(res);

    }

    /**
     *
     * Method: resultSetToJson(ResultSet rs)
     *
     */
    @Test
    public void testResultSetToJson() throws Exception {
//TODO: Test goes here... 
    }


    @Test
    public void querysum()throws Exception{
        System.out.println("sum结果："+Workdao.queryWorkersSum().toString());
    }

} 
