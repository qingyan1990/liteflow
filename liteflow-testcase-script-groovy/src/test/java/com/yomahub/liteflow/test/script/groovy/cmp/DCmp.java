/**
 * <p>Title: liteflow</p>
 * <p>Description: 轻量级的组件式流程框架</p>
 * @author Bryan.Zhang
 * @email weenyc31@163.com
 * @Date 2020/4/1
 */
package com.yomahub.liteflow.test.script.groovy.cmp;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.entity.data.Slot;

@LiteflowComponent("d")
public class DCmp extends NodeComponent {

	@Override
	public void process() {
		Slot slot = this.getSlot();
		slot.setData("count",198);
		System.out.println("DCmp executed!");
	}

}
