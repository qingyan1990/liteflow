package com.yomahub.liteflow.test.component.cmp1;

import com.yomahub.liteflow.core.NodeComponent;

import java.util.Objects;


public class BCmp extends NodeComponent {
    @Override
    public void process() {
        System.out.println("BComp executed!");
        Integer requestData = this.getSlot().getRequestData();
        Integer divisor = 130;
        Integer result = divisor / requestData;
        this.getSlot().setResponseData(result);
    }

    @Override
    public boolean isAccess() {
        Integer requestData = this.getSlot().getRequestData();
        if (Objects.nonNull(requestData)){
            return true;
        }
        return false;
    }

}
