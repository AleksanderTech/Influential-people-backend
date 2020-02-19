package com.alek.influentialpeople.common.abstraction;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService<ID> {

    byte[]getImage(ID ownerId);

    String storeImage(ID ownerId, MultipartFile image);
}
