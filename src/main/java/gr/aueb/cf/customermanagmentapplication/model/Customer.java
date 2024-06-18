package gr.aueb.cf.customermanagmentapplication.model;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    private Long taxIdNumber;
    private String firstname;
    private String lastname;
    private String email;
    private Long phoneNumber;
    private String city;
    private String address;
    private String orderDate;
    private String deliveryDate;
    private Long paymentInAdvance;
    private Long totalAmount;
    private Long balance;
    @Column(nullable = false, updatable = false)
    private String orderNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; // Reference to the User entity
}

