package com.zzq.zzqframeworkservicedubboprovider.service.impl;

import com.zzq.zzqframeworkdatadubboprovider.service.ZKServiceProviderTestService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * ZKServiceProviderTestServiceImpl
 *
 * @author zhouzhiqiang
 * @version 1.0
 * @date 2022-03-25 10:37
 */
@DubboService
@Service
public class ZKServiceProviderTestServiceImpl implements ZKServiceProviderTestService {
    @Override
    public String getZKServiceProviderTest() {
        return "ZKServiceProviderTestServiceImpl Test Success";
    }
}
