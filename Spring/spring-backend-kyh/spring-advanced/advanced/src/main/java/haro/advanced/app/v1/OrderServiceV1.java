package haro.advanced.app.v1;

import haro.advanced.app.v0.OrderRepositoryV0;
import haro.advanced.trace.TraceStatus;
import haro.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepositoryV1;
    private final HelloTraceV1 trace;

    public void orderItem(String itemId) {


        TraceStatus status = null;

        try {
            status = trace.begin("OrderService.orderItem()");
            orderRepositoryV1.save(itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }

}
