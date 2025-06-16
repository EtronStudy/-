package learn.subject.shop.persentation.api;

import learn.subject.shop.application.ProductService;
import learn.subject.shop.domain.Product;
import learn.subject.shop.persentation.dto.request.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;

}
