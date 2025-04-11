package com.chronodebileplus.store.price;

import com.chronodebileplus.store.product.Product;
import com.chronodebileplus.store.product.ProductPriceDto;
import com.chronodebileplus.store.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriceService {
    @Autowired
    ProductRepository productRepository;

    public PriceDto get() {
        List<Product> products = productRepository.findAllByOrderByProductIdAsc();

        List<ProductPriceDto> productPriceDtos = products.stream()
                .map(product -> new ProductPriceDto(product.getProductId(), product.getAmount()))
                .toList();

        return new PriceDto(productPriceDtos);
    }

    public PriceDto save(PriceDto dto) {
        List<ProductPriceDto> dtoProducts = dto.getPrices();

        // get existing products
        List<Long> productIds = dtoProducts.stream()
                .map(ProductPriceDto::getProductId)
                .filter(Objects::nonNull)
                .toList();
        List<Product> existingProducts = this.productRepository.findAllById(productIds);

        // map of productId -> Product
        Map<Long, Product> existingProductsMap = existingProducts
                .stream()
                .collect(Collectors.toMap(Product::getProductId, p -> p));

        // prepare products
        List<Product> products = dtoProducts.stream()
                .map(dtoProduct -> {
                    Optional<Product> product = Optional.empty();

                    if (dtoProduct.getProductId() != null && existingProductsMap.containsKey(dtoProduct.getProductId())) {
                        Product existingProduct = existingProductsMap.get(dtoProduct.getProductId());
                        existingProduct.setAmount(dtoProduct.getAmount());

                        product = Optional.of(existingProduct);

                        //product.ifPresent(p -> {
                            // update product
                            //p.setProductName(dtoProduct.getProductName());
                        //    p.setAmount(dtoProduct.getAmount());
                        //});
                        //product.setAmount(dtoProduct.getAmount());

                    }

                    return product;
                })
                .filter(Optional::isPresent) // remove product not found from price
                .map(Optional::get)
                .toList();


        // save products
        var saved = this.productRepository.saveAll(products);

        var response = saved.stream()
                .map(p -> new ProductPriceDto(
                        p.getProductId(),
                        p.getAmount()
                ))
                .toList();

        return new PriceDto(response);
    }
}
