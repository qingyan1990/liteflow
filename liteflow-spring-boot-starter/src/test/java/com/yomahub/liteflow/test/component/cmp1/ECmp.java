package com.yomahub.liteflow.test.component.cmp1;

import com.alibaba.fastjson.JSON;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component("e")
public class ECmp extends NodeComponent {

    @Override
    public void process() throws Exception {
        System.out.println("EComp executed!");
        Object responseData = this.getSlot().getResponseData();
        if (Objects.isNull(responseData)){
            System.out.println("EComp responseData flow must be set end .");
            //执行到某个条件时，手动结束流程。
            this.setIsEnd(true);
        }
        System.out.println("EComp responseData responseData=" + JSON.toJSONString(responseData));
    }
}
