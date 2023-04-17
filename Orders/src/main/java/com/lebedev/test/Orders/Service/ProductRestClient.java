package com.lebedev.test.Orders.Service;

import com.lebedev.test.Orders.Model.Product;
import com.lebedev.test.Orders.Model.ProductStockUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class ProductRestClient {
    @Value("${product.api.host.baseurl}")
    private String productApiHost;

    @Value("${product.api.get.product.by.id.path}")
    private String productByIdUri;

    @Value("${product.api.update.product.path}")
    private String updatePoductdUri;

    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private HttpStatus status;

    @Autowired
    public ProductRestClient(RestTemplateBuilder builder) {
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        this.restTemplate = builder.build();
    }

    public Product getProduct(Long id) {
        String uri = productApiHost + productByIdUri.replace("{id}", id.toString());
        ResponseEntity<Product> response = restTemplate.exchange(
                uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<Product>(){});

        return response.getBody();
    }

    /**
     * Reserve or reverse products in warehouse
     * @param productUpdate
     * @return @{@link Boolean} true if all products were reserved for the order, overviewed - false
     */
    public Boolean updateProductSock(List<ProductStockUpdate> productUpdate) {
        if(productUpdate == null || productUpdate.size() == 0) return null;
        HttpEntity<List<ProductStockUpdate>> requestEntity = new HttpEntity<>(productUpdate);

        String uri = productApiHost + updatePoductdUri;
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                uri, HttpMethod.PUT, requestEntity,
                new ParameterizedTypeReference<>() {
                });

        List<Product> resultList = response.getBody();

        //TODO more comprahansive validation of success
        return resultList.size() > 0;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
