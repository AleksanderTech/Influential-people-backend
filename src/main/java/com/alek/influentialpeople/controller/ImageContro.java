//package com.alek.influentialpeople.controller;
//
//public class ImageContro {
//	  @GetMapping("/{id}/image")
//	    public ResponseEntity<byte[]> getAuthorImage(@PathVariable("id") long id) {
//		String authorImagePath = getAuthorService().getImagePath(id);
//		if (authorImagePath == null || authorImagePath.isEmpty()) {
//		    throw new NotFoundException();
//		}
//		byte[] authorImage = imageService.getImageBytes(authorImagePath);
//		if (authorImage == null || authorImage.length < 1) {
//		    throw new NotFoundException();
//		}
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg")
//			.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(authorImage.length)).body(authorImage);
//	    }
//
//	    @PutMapping("/{id}/image")
//	    public Response putAuthorImage(@PathVariable("id") long id, @RequestPart("image") MultipartFile multipartFile) {
//		if (id < 1) {
//		    throw new BadRequestException();
//		}
//		validateRole();
//		byte[] image = imageService.getImageBytes(multipartFile, AUTHOR_IMAGE_WIDTH, AUTHOR_IMAGE_HEIGHT);
//		if ((image == null || image.length < 1)) {
//		    throw new WrongDataException();
//		}
//		String authorImagePath = authorsImagesPath + "/" + imageService.getImageFileNameWithFormat(String.valueOf(id));
//		File imageFile = imageService.createImageFileFromBytes(authorImagePath, image);
//		if (imageFile == null || !imageFile.exists()) {
//		    throw new InternalErrorException();
//		}
//		if (!getAuthorService().saveImagePath(id, authorImagePath)) {
//		    throw new WrongDataException("Can not save image path!");
//		}
//		return new Response(Response.OK);
//	    }
//}
//
//    @GetMapping("/author/{id}/image")
//    public ResponseEntity<byte[]> getAuthorImage(@PathVariable("id") long id) {
//	if (id < 0) {
//	    throw new BadRequestException();
//	}
//	String authorImagePath = authorService.getImagePath(id);
//	if (authorImagePath == null || authorImagePath.isEmpty()) {
//	    throw new NotFoundException();
//	}
//	byte[] authorImage = imageService.getImageBytes(authorImagePath);
//	if (authorImage == null || authorImage.length < 1) {
//	    throw new NotFoundException();
//	}
//	return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpg")
//		.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(authorImage.length)).body(authorImage);
//    }