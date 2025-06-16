package com.task.test.product.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductTest {

    @Test
    @DisplayName("제품 생성 테스트")
    void createTest() {
        //given
        //when
        Product result = Product.create("apple", 1000, 5);

        //then
        assertThat(result)
                .extracting(Product::getName, Product::getPrice, Product::getQuantity)
                .containsExactly("apple", 1000, 5);
    }

    @Test
    @DisplayName("제품 생성 시 이름을 입력하지 않은 경우 예외가 발생한다.")
    void createNullNameTest() {
        //given
        //when
        //then
        assertThatThrownBy(() -> Product.create(null, 1000,5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제품 이름은 필수값입니다.");
    }

    @Test
    @DisplayName("제품 생성 시 이름이 공백인 경우 예외가 발생한다.")
    void createBlankNameTest() {
        //given
        //when
        //then
        assertThatThrownBy(() -> Product.create("", 1000,5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제품 이름은 필수값입니다.");
    }

    @Test
    @DisplayName("제품 생성 시 가격이 null 경우 예외가 발생한다.")
    void createNullPriceTest() {
        //given
        //when
        //then
        assertThatThrownBy(() -> Product.create("apple", null,5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제품 가격은 필수값입니다.");
    }

    @Test
    @DisplayName("제품 생성 시 가격이 음수인 경우 예외가 발생한다.")
    void createNegativePriceTest() {
        //given
        //when
        //then
        assertThatThrownBy(() -> Product.create("apple", -1,5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제품 가격은 0 이상이어야합니다.");
    }

    @Test
    @DisplayName("제품 생성 시 수량이 null 경우 예외가 발생한다.")
    void createNullQuantityTest() {
        //given
        //when
        //then
        assertThatThrownBy(() -> Product.create("apple", 1000,null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제품 수량은 필수값입니다.");
    }

    @Test
    @DisplayName("제품 생성 시 수량이 음수인 경우 예외가 발생한다.")
    void createNegativeQuantityTest() {
        //given
        //when
        //then
        assertThatThrownBy(() -> Product.create("apple", 1000,-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("제품 수량은 0 이상이어야합니다.");
    }
    
    @Test
    @DisplayName("요청 수량만큼 재고가 감소한다.")
    void deductQuantityTest() {
        //given
        Product product = Product.create("apple", 1000, 5);
        
        //when
        product.deductQuantity(3);
        
        //then
        assertThat(product.getQuantity()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("요청 수량이 재고보다 많은 경우 예외가 발생한다.")
    void deductMoreThanQuantityTest() {
        //given
        Product product = Product.create("apple", 1000, 5);
        
        //when
        //then
        assertThatThrownBy(() -> product.deductQuantity(6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("재고가 부족합니다.");
    }
}
