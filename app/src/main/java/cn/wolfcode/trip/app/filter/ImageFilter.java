package cn.wolfcode.trip.app.filter;

import cn.wolfcode.trip.base.util.UploadUtil;
import org.apache.commons.io.FileUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class ImageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //获取当前的请求路径
        String uri = request.getRequestURI();
        //判断文件目录中是否存在该图片
        File file = new File(UploadUtil.PATH, uri);
        if (file.exists()) {
            servletResponse.getOutputStream().write(FileUtils.readFileToByteArray(file));
        } else {
            //放行
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
