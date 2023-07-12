package org.zerock.j2.service;

import org.springframework.stereotype.Service;
import org.zerock.j2.dto.*;
import org.zerock.j2.entity.Product;
import org.zerock.j2.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public PageResponseDTO<ProductListDTO> list(PageRequestDTO requestDTO) {

        return productRepository.listWithReview(requestDTO);
        
    }
    @Override
    public Long register(ProductDTO productDTO) {
        
        Product product = Product.builder()
        .pname(productDTO.getPname())
        .pdesc(productDTO.getPdesc())
        .price(productDTO.getPrice())
        .build();

        productDTO.getImages().forEach(fname -> {
            product.addImage(fname);
        });

        return productRepository.save(product).getPno();
    }
    
}
