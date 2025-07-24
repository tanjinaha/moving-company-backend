package com.tanjina.mvc.backend.repository;

import com.tanjina.mvc.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // 🔍 Search customer by partial name
    List<Customer> findByCustomerNameContainingIgnoreCase(String name);

    // 🔍 Exact match by phone and email (you can keep this if needed elsewhere)
    Optional<Customer> findByCustomerPhoneAndCustomerEmail(Long customerPhone, String customerEmail);

    // ✅ Smart match: reuse if (name + email) OR (name + phone) match
    @Query("SELECT c FROM Customer c WHERE " +
            "(c.customerName = :name AND c.customerEmail = :email) OR " +
            "(c.customerName = :name AND c.customerPhone = :phone)")
    Optional<Customer> findMatchingCustomer(@Param("name") String name,
                                            @Param("email") String email,
                                            @Param("phone") Long phone);
}
