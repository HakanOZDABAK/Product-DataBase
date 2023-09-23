package com.hakanozdabak.service;

import hakanozdabak.productData.business.concretes.ProductManager;
import hakanozdabak.productData.business.responses.GetAllProductsResponse;
import hakanozdabak.productData.business.rules.ProductBusinessRules;
import hakanozdabak.productData.core.utilities.mappers.ModelMapperService;
import hakanozdabak.productData.dataAccess.abstracts.ProductRepository;
import hakanozdabak.productData.entities.concretes.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProductManagerTest {


    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductBusinessRules productBusinessRules;
    @Mock
    private ModelMapperService modelMapperService;


    @Test
    @DisplayName("Should Find Post By Id")
    void shouldFindPostById() {
        ProductManager productManager = new ProductManager(productRepository,productBusinessRules,modelMapperService);
        Product product = new Product(123,"Milk","Drink",10,true,5);
        GetAllProductsResponse expectedProductResponse = new GetAllProductsResponse(123,"Milk","Drink",10,true,5);
        Mockito.when(productRepository.findById(123)).thenReturn(Optional.of(product));

        Optional<Product> actualPostResponse = productManager.getById(123);

        assertThat(actualPostResponse.get().getId()).isEqualTo(expectedProductResponse.getId());
        assertThat(actualPostResponse.get().getName()).isEqualTo(expectedProductResponse.getName());
    }
    /*@Test
    @DisplayName("Should Save Posts")
    void shouldSavePosts() {
        User currentUser = new User(123L, "test user", "secret password", "user@email.com", Instant.now(), true);
        Subreddit subreddit = new Subreddit(123L, "First Subreddit", "Subreddit Description", Collections.emptyList(), Instant.now(), currentUser);
        Post post = new Post(123L, "First Post", "http://url.site", "Test",
                0, null, Instant.now(), null);
        PostRequest postRequest = new PostRequest(null, "First Subreddit", "First Post", "http://url.site", "Test");

        Mockito.when(subredditRepository.findByName("First Subreddit"))
                .thenReturn(Optional.of(subreddit));
        Mockito.when(authService.getCurrentUser())
                .thenReturn(currentUser);
        Mockito.when(postMapper.map(postRequest, subreddit, currentUser))
                .thenReturn(post);

        postService.save(postRequest);
        Mockito.verify(postRepository, Mockito.times(1)).save(postArgumentCaptor.capture());

        assertThat(postArgumentCaptor.getValue().getPostId()).isEqualTo(123L);
        assertThat(postArgumentCaptor.getValue().getPostName()).isEqualTo("First Post");
    }*/

}
