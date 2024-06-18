package gr.aueb.cf.customermanagmentapplication.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Serializable {


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

    private String orderNumber;

    private String userId;
    private String uuId;
}

