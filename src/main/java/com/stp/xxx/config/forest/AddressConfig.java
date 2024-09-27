package com.stp.xxx.config.forest;

import com.dtflys.forest.callback.AddressSource;
import com.dtflys.forest.http.ForestAddress;
import com.dtflys.forest.http.ForestRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AddressConfig implements AddressSource {

    @Value("${alist.server.host}")
    private String host;

    @Value("${alist.server.port}")
    private Integer port;

    @Override
    public ForestAddress getAddress(ForestRequest request) {
        return new ForestAddress(host, port);
    }
}
