package com.example.springboot_vue_backend.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/*
 * 这里我们回顾一下单页面应用的概念，在我们这个项目中，其实只有 index.html 这一个页面，
 * 所有的其它内容都是在这个页面里动态渲染的。当我们直接在后端访问/login路径时，服务器会
 * 后端并没有这个路径对应的内容，所以返回了Error Page。为了获取到我们需要的内容，我们
 * 要想办法触发前端路由，即在后端添加处理内容，把通过这个URL渲染出的index.html返回到浏
 * 览器。新建一个package名为error，新建实现ErrorPageRegistrar接口的类ErrorConfig，
 * 把默认的错误页面设置为 /index.html
 */

@Component
public class ErrorConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
        registry.addErrorPages(error404Page);
    }
}
