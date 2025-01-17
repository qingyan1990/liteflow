package com.yomahub.liteflow.entity.flow.parallel;

import com.yomahub.liteflow.entity.flow.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.function.Supplier;

/**
 * 并行异步worker对象，提供给CompletableFuture用
 * @author Bryan.Zhang
 * @since 2.6.4
 */
public class ParallelSupplier implements Supplier<WhenFutureObj> {

    private static final Logger LOG = LoggerFactory.getLogger(ParallelSupplier.class);

    private final Executable executableItem;

    private final Integer slotIndex;

    public ParallelSupplier(Executable executableItem, Integer slotIndex) {
        this.executableItem = executableItem;
        this.slotIndex = slotIndex;
    }

    @Override
    public WhenFutureObj get() {
        try {
            executableItem.execute(slotIndex);
            return WhenFutureObj.success(executableItem.getExecuteName());
        } catch (Exception e){
            return WhenFutureObj.fail(executableItem.getExecuteName(), e);
        }
    }
}
