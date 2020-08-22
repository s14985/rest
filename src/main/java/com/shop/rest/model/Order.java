package com.shop.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.OffsetDateTime;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@ToString(exclude = { "productOrders", "user" })
@EqualsAndHashCode(exclude = { "productOrders", "user" })
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private OffsetDateTime dateCreated;

	private Status status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<ProductOrder> productOrders;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Order(Status status, User user) {
		this.status = status;
		this.user = user;
		this.dateCreated = OffsetDateTime.now();
	}
}
