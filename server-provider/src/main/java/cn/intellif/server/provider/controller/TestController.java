package cn.intellif.server.provider.controller;

import cn.intellif.server.common.LogUtils;
import cn.intellif.server.common.ServerResult;
import cn.intellif.server.provider.service.ITestService;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author buchengyin
 * @Date 2019/3/30 15:39
 **/
@RestController
@Api(tags = "TestController", description = "测试web项目")
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ITestService test;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping("/test")
    public Object test(){
        return "test";
    }



    @GetMapping("/hello")
    @ApiOperation(value = "说hello", notes = "说hello")
    @ApiImplicitParam(name = "word", value = "单词", required = true, dataType = "Object")
    public Object hello(String word) {
        try {
            System.out.println("=====================>test===============");
           String content =  test.hello(word);
           return ServerResult.success(content);
        }catch (BlockException e){
            return ServerResult.fail("block");
        }catch (Exception e){
            return ServerResult.fail(e.getMessage());
        }
    }



    @PostMapping("/batchSave")
    @ApiOperation(value = "批量添加", notes = "批量添加")
    public Object batchTest() {
        test.batchSaveTest(0);
        return ServerResult.success();
    }

    @PostMapping("/executeSql")
    @ApiOperation(value = "sql查询器", notes = "sql查询器")
    @ApiImplicitParam(name = "sql", value = "sql语句", required = true)
    public Object querySql(@RequestBody String sql) {
        if (sql.contains("select")|| sql.contains("show")) {
            List<Map> query = jdbcTemplate.query(sql, new RowMapper<Map>() {
                @Override
                public Map mapRow(ResultSet resultSet, int i) throws SQLException {
                    int columnCount = resultSet.getMetaData().getColumnCount();
                    Map map = new HashMap();
                    for (int j = 1; j <= columnCount; j++) {
                        map.put(resultSet.getMetaData().getColumnLabel(j), resultSet.getObject(j));
                    }
                    return map;
                }
            });
            return ServerResult.successWithData(query);
        } else {
            jdbcTemplate.execute(sql);
            return ServerResult.success();
        }
    }
}
