package haro.advanced.app.v2;

import haro.advanced.trace.TraceId;
import haro.advanced.trace.TraceStatus;
import haro.advanced.trace.hellotrace.HelloTraceV1;
import haro.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepositoryV1;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId, String itemId) {


        TraceStatus status = null;

        try {
            status = trace.beginSync(traceId, "OrderService.orderItem()");
            orderRepositoryV1.save(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }

    }

}
