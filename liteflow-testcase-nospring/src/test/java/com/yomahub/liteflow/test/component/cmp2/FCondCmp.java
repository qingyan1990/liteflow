package com.yomahub.liteflow.test.component.cmp2;

import com.yomahub.liteflow.core.NodeCondComponent;
import java.util.Objects;

public class FCondCmp extends NodeCondComponent {
    @Override
    public String processCond() {
        Integer requestData = this.getSlot().getRequestData();
        if (Objects.isNull(requestData)){
            return "d";
        } else if(requestData == 0){
            return "c";
        } else {
            return "b";
        }
    }
}
