/**
 * <p>Title: liteflow</p>
 * <p>Description: 轻量级的组件式流程框架</p>
 * @author Bryan.Zhang
 * @email weenyc31@163.com
 * @Date 2020/4/1
 */
package com.yomahub.liteflow.test.privateDelivery.cmp;

import com.yomahub.liteflow.core.NodeComponent;

import java.util.Set;

public class BCmp extends NodeComponent {

    @Override
    public void process() {
        System.out.println("BCmp executed!");
        Integer value = this.getPrivateDeliveryData();
        Set<Integer> testSet = this.getSlot().getData("testSet");
        testSet.add(value);
    }
}
