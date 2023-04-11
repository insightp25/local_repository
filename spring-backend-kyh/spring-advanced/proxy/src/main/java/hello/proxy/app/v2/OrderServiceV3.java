package hello.proxy.app.v2;

public class OrderServiceV3 {

    private final OrderRepositoryV2 orderRepository;

    public OrderServiceV3(OrderRepositoryV2 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }

}
