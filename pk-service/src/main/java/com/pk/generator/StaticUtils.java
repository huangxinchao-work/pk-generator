package com.pk.generator;

/**
 * @author: Roy
 * @description:
 * @create: 2020-04-14 14:50
 * @since: JDK1.8
 */
public class StaticUtils {

    public static InitBean initBean;

    public static long getPk(){
        return initBean.getInitializationCode();
    }

    public static void setInitBean(InitBean initBean) {
        StaticUtils.initBean = initBean;
    }


}
