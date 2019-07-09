package cn.intellif.server.provider.controller;

import cn.intellif.server.common.LogUtils;
import cn.intellif.server.common.ServerResult;
import cn.intellif.server.provider.service.ITest;
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
    private ITest test;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/hello")
    @ApiOperation(value = "说hello", notes = "说hello")
    @ApiImplicitParam(name = "word", value = "单词", required = true, dataType = "String")
    public String hello(String word) {
        LogUtils.info(this, "-------------provider hello invoke word:" + word);
        return test.hello(word);
    }


    @RequestMapping("/testJson")
    public Object testJson(@RequestBody Map<String,String> param){
        System.out.println(param);
        param.put("test","ok");
        return ServerResult.successWithData(param);
    }

    @PostMapping("/hi")
    @ApiOperation(value = "hi", notes = "嗨")
    @ApiImplicitParams({@ApiImplicitParam(name = "someThing", value = "某些事件", required = true), @ApiImplicitParam(name = "hahaha", value = "hahaha", required = true)})
    public String hi(String someThing, String hahaha) {
        LogUtils.info(this, someThing + hahaha);
        return test.hello(someThing);
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
