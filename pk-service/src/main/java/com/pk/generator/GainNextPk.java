package com.pk.generator;


/**
 * @author: Roy
 * @description: 获取下一主键
 * @create: 2020-04-13 09:29
 * @since: JDK1.8
 */
public class GainNextPk {

    private InitBean initBean;

    public GainNextPk(InitBean initBean) {
        this.initBean = initBean;
    }

    public long getPk(){
        return initBean.getInitializationCode();
    }

}
