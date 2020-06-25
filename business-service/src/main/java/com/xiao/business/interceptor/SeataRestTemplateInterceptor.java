package com.xiao.business.interceptor;

import io.seata.core.context.RootContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

public class SeataRestTemplateInterceptor implements ClientHttpRequestInterceptor {
    public SeataRestTemplateInterceptor() {
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(httpRequest);


        /**
         * 绑定全局事务 XID
         * 事务提交或回滚，自动解绑
         * @see http://seata.io/zh-cn/docs/user/microservice.html
         */
        String xid = RootContext.getXID();
        if (StringUtils.isNotEmpty(xid)) {
            requestWrapper.getHeaders().add(RootContext.KEY_XID, xid);
        }

        return clientHttpRequestExecution.execute(requestWrapper, bytes);
    }
}
