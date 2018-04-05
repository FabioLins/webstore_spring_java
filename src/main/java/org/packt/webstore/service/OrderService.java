package org.packt.webstore.service;

import org.packt.webstore.domain.Order;

public interface OrderService {

	long saveOrder(Order order);
	
}
