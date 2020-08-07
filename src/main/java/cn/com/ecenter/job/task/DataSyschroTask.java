package cn.com.ecenter.job.task;

import cn.com.ecenter.xzspxt.service.DataSyschroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class DataSyschroTask {

    @Autowired
    private DataSyschroService dataSyschroService;

    public void dataSyschro(String size) {
        dataSyschroService.dataSyschro(size);
    }
}
