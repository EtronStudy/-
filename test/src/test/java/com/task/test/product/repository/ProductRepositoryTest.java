package com.task.test.product.repository;

import com.task.test.product.entity.Product;
import org.assertj.core.api.AbstractListAssert;
import org.assertj.core.api.ObjectAssert;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.*;

@ActiveProfiles("test")
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("제품 조회 테스트")
    void saveProductTest() {
        //given
        Product product1 = Product.create("apple", 1000, 5);
        Product product2 = Product.create("banana", 2000, 10);
        Product product3 = Product.create("orange", 3000, 15);

        productRepository.saveAll(List.of(product1, product2, product3));

        //when
        List<Product> result = productRepository.findAll();

        //then
        assertThat(result)
                .hasSize(3)
                .extracting(Product::getName, Product::getPrice, Product::getQuantity)
                .containsExactly(
                        tuple("apple", 1000, 5),
                        tuple("banana", 2000, 10),
                        tuple("orange", 3000, 15)
                );

    }
}
