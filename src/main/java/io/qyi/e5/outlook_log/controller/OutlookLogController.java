package io.qyi.e5.outlook_log.controller;


import com.influxdb.client.InfluxDBClient;
import io.qyi.e5.bean.result.Result;
import io.qyi.e5.config.security.UsernamePasswordAuthenticationToken;
import io.qyi.e5.outlook.service.IOutlookService;
import io.qyi.e5.outlook_log.entity.OutlookLog;
import io.qyi.e5.outlook_log.service.IOutlookLogService;
import io.qyi.e5.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 落叶
 * @since 2020-03-03
 */
// @Slf4j
@RestController
@RequestMapping("/outlookLog")
public class OutlookLogController {

//    @Autowired
//    private OutlookLogMapper outlookLogMapper;

    @Autowired
    private IOutlookLogService outlookLogService;
    @Autowired
    IOutlookService outlookService;

    @Autowired
    InfluxDBClient influxDBClient;

    @Value("${spring.influx.org:''}")
    private String org;

    @Value("${spring.influx.bucket:''}")
    private String bucket;


    @Value("${page.size}")
    private int pageSize;

    @GetMapping("/findLog")
    public Result findLog(@RequestParam int outlookId){
        // log.info("查询");
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        int github_id = authentication.getGithub_id();
        List<OutlookLog> list = outlookLogService.findAllList(github_id, outlookId);
        return ResultUtil.success(list);
    }

}
