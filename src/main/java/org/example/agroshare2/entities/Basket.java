package org.example.agroshare2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "basket")
@SequenceGenerator(name = "public.basket_s", sequenceName = "basket_s", schema = "public")
public class Basket {
    @Id
    @Column(name = "basket_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket_s")
    @SequenceGenerator(name = "basket_s", sequenceName = "basket_s", allocationSize = 1)
    private Long basketId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_id")
    private Long orderId;
}
