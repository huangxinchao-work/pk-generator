package com.pk.generator;



/**
 * @author: Roy
 * @description: 获取下一主键
 * @create: 2020-04-13 09:29
 * @since: JDK1.8
 */
public class GainNextPk {

    public static InitBean initBean;

    static void setInitBean(InitBean initBean) {
        GainNextPk.initBean = initBean;
    }

    public static long getPk(){
        return initBean.getInitializationCode();
    }

}
