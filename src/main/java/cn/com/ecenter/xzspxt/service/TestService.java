package cn.com.ecenter.xzspxt.service;

import cn.com.ecenter.xzspxt.entity.NytAddressInfoEntity;
import cn.com.ecenter.xzspxt.entity.QltQlsxEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.List;

public interface TestService {

    /**
     *
     * @return
     */
    QltQlsxEntity testQuartz();

    NytAddressInfoEntity testBase();

}
