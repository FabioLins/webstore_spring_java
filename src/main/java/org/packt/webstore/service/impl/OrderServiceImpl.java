package org.packt.webstore.service.impl;

import org.packt.webstore.domain.Order;
import org.packt.webstore.repository.OrderRepository;
import org.packt.webstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public long saveOrder(Order order) {
		
		return this.orderRepository.saveOrder(order);
	
	}

}
