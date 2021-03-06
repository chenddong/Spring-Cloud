package net.nanquanyuhao.cloud.contract;

import feign.Contract;
import feign.MethodMetadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 在类 MyUrl 中，@Target(ElementType.METHOD)表明为方法级注解，只实现 processAnnotationOnMethod 即可
 * Created by nanquanyuhao on 2017/10/27.
 */
public class MyContract extends Contract.BaseContract {

    /**
     * 类级别的注解
     * @param methodMetadata
     * @param aClass
     */
    protected void processAnnotationOnClass(MethodMetadata methodMetadata, Class<?> aClass) {

    }

    /**
     * 翻译器实现：方法级别的注解
     * @param methodMetadata
     * @param annotation
     * @param method
     */
    protected void processAnnotationOnMethod(MethodMetadata methodMetadata, Annotation annotation, Method method) {

        // 首先判断注解类 annotation 类型为MyUrl才会处理
        if (MyUrl.class.isInstance(annotation)) {
            // 读取注解中配置
            MyUrl myUrl = method.getAnnotation(MyUrl.class);
            String url = myUrl.url();
            String httpMethod = myUrl.method();

            // 将读取的配置设置到模板内
            methodMetadata.template().method(httpMethod);
            methodMetadata.template().append(url);
        }
    }

    /**
     * 参数级别的注解
     * @param methodMetadata
     * @param annotations
     * @param i
     * @return
     */
    protected boolean processAnnotationsOnParameter(MethodMetadata methodMetadata, Annotation[] annotations, int i) {
        return false;
    }
}
