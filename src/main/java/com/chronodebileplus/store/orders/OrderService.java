package com.chronodebileplus.store.orders;

import com.chronodebileplus.store.product.Product;
import com.chronodebileplus.store.product.ProductOrderDto;
import com.chronodebileplus.store.product.ProductRepository;
import com.chronodebileplus.store.product.ProductStockDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public OrderDto save(OrderDto dto) {
        List<ProductOrderDto> dtoProducts = dto.getProducts();

        // get existing products
        List<Long> productIds = dtoProducts
            .stream()
            .map(ProductOrderDto::getProductId)
            .filter(id -> id != null)
            .toList();
        List<Product> existingProducts = productRepository.findAllById(
            productIds
        );
        // map of productId -> Product
        Map<Long, Product> existingProductsMap = existingProducts
            .stream()
            .collect(Collectors.toMap(Product::getProductId, p -> p));

        // prepare products
        List<Product> productsToUpdate = new ArrayList<Product>();
        List<Product> products = dtoProducts
            .stream()
            .map(dtoProduct -> {
                Optional<Product> product = Optional.empty();

                if (
                    dtoProduct.getProductId() != null &&
                    existingProductsMap.containsKey(dtoProduct.getProductId())
                ) {
                    if (
                        existingProductsMap
                            .get(dtoProduct.getProductId())
                            .getQuantity() <
                        dtoProduct.getQuantity()
                    ) {
                        throw new IllegalArgumentException("Not enough stock");
                    }
                    Product selectedProduct = existingProductsMap.get(
                        dtoProduct.getProductId()
                    );
                    // add product in productsToUpdate
                    Long newQty =
                        selectedProduct.getQuantity() -
                        dtoProduct.getQuantity();
                    if (newQty < 0) {
                        newQty = 0L;
                    }
                    selectedProduct.setQuantity(newQty);
                    productsToUpdate.add(selectedProduct);

                    // Copy the product to update the order
                    Product copiedProduct = selectedProduct.deepCopy();
                    copiedProduct.setQuantity(dtoProduct.getQuantity());

                    product = Optional.of(copiedProduct);
                }

                return product;
            })
            .filter(Optional::isPresent) // remove product not found from order
            .map(Optional::get)
            .toList();

        // delete all product of order
        orderRepository.deleteByOrderKey_Id(dto.getId());

        // format products to Order entities
        List<Order> orders = products
            .stream()
            .map(product ->
                new Order(
                    dto.getId(),
                    product.getProductId(),
                    product.getQuantity()
                )
            )
            .toList();

        // create and update products
        var saved = orderRepository.saveAll(orders);

        // update response
        var response = saved
            .stream()
            .map(p -> new ProductOrderDto(p.getProductId(), p.getQuantity()))
            .toList();

        return new OrderDto(dto.getId(), response);
    }
}
