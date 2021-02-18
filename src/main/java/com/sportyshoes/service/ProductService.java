package com.sportyshoes.service;

import com.sportyshoes.entity.ProductEntity;
import com.sportyshoes.entity.UserEntity;
import com.sportyshoes.repository.ProductRepository;
import com.sportyshoes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<ProductEntity> findAll() {
        return repository.findAll();
    }

    public ProductEntity save(ProductEntity product) {
        return repository.save(product);
    }

    public Optional<ProductEntity> findById(Integer userId) {
        return repository.findById(userId);
    }

    public List<ProductEntity> saveAll(List products) {
        return repository.saveAll(products);
    }

    public void deleteById(Integer userId) {
        repository.deleteById(userId);
    }

    public void delete(ProductEntity product) {
        repository.delete(product);
    }
}
