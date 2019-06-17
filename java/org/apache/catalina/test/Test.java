package org.apache.catalina.test;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * @author liuweilong
 * @description
 * @date 2019/4/25 13:57
 */
public class Test {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9999);
        String basePath = Test.class.getResource("/").getPath();
        //这里要把html文件放到父目录下的src/main/webapp，因为springboot就是这么做的
        File file = new File("/webapps");
        //告诉tomcat你的webapp在哪
        Context context = tomcat.addWebapp("/", file.getAbsolutePath());
        WebResourceRoot resourceRoot = new StandardRoot(context);
        resourceRoot.addPreResources(new DirResourceSet(resourceRoot, "/target",
                basePath, "/"));

        context.setResources(resourceRoot);


        tomcat.start();
        tomcat.getServer().await();

    }
}
