package learn.subject.shop.application;

import learn.subject.shop.domain.Product;
import learn.subject.shop.infra.repository.ProductRepository;
import learn.subject.shop.persentation.dto.request.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void save(CreateProductRequest request) {
        Product product = Product.toEntity(request);
        productRepository.save(product);
    }

}
