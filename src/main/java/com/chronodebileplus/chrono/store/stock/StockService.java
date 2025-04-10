package com.chronodebileplus.chrono.store.stock;

import com.chronodebileplus.chrono.store.product.Product;
import com.chronodebileplus.chrono.store.product.ProductRepository;
import com.chronodebileplus.chrono.store.product.ProductStockDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    ProductRepository productRepository;

    public StockDto save(StockDto dto) {
        List<ProductStockDto> dtoProducts = dto.getProducts();

        // get existing products
        List<Long> productIds = dtoProducts
            .stream()
            .map(ProductStockDto::getProductId)
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
        List<Product> products = dtoProducts
            .stream()
            .map(dtoProduct -> {
                Product product;

                if (
                    dtoProduct.getProductId() != null &&
                    existingProductsMap.containsKey(dtoProduct.getProductId())
                ) {
                    // update product
                    product = existingProductsMap.get(
                        dtoProduct.getProductId()
                    );
                    product.setProductName(dtoProduct.getProductName());
                    product.setQuantity(dtoProduct.getQuantity());
                } else {
                    // create new product
                    product = new Product(
                        dtoProduct.getProductId(),
                        dtoProduct.getProductName(),
                        dtoProduct.getQuantity(),
                        0
                    );
                }

                return product;
            })
            .toList();

        // create and update products
        var saved = productRepository.saveAll(products);

        // update response
        var response = saved
            .stream()
            .map(p ->
                new ProductStockDto(
                    p.getProductId(),
                    p.getProductName(),
                    p.getQuantity()
                )
            )
            .toList();

        return new StockDto(response);
    }
}
