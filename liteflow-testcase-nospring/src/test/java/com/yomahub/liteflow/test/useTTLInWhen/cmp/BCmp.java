/**
 * <p>Title: liteflow</p>
 * <p>Description: 轻量级的组件式流程框架</p>
 * @author Bryan.Zhang
 * @email weenyc31@163.com
 * @Date 2020/4/1
 */
package com.yomahub.liteflow.test.useTTLInWhen.cmp;

import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.test.useTTLInWhen.TestTL;

public class BCmp extends NodeComponent {

	@Override
	public void process() {
		String value = TestTL.get();
		this.getSlot().setData(this.getNodeId(),value+",b");
		System.out.println("BCmp executed!");
	}

}
