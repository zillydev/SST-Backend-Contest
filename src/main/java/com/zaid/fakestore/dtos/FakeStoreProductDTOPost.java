package com.zaid.fakestore.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTOPost {
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
