package com.alek.influentialpeople.home.service;

import org.springframework.stereotype.Service;
import com.alek.influentialpeople.model.Link;

@Service
public class TheLinkFactory implements LinkFactory {

	public TheLinkFactory() {
		super();
	}

	@Override
	public Link getLink(String url,String rel) {
		Link link =new Link(url,rel);
		return link;
	}

}
