package org.packt.webstore.repository;

import org.packt.webstore.domain.Order;

public interface OrderRepository {

	long saveOrder(Order order);
	
}
