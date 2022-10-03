package com.nourish1709.advisoryservice.domain.system_user.applicant;

import com.nourish1709.advisoryservice.domain.system_user.SystemUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "applicant")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Applicant extends SystemUser {

    @Column(name = "ssn")
    private String ssn;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(name = "phone_number", joinColumns = @JoinColumn(name = "applicant_id"))
    @AttributeOverride(name = "value", column = @Column(name = "value", nullable = false))
    @AttributeOverride(name = "type", column = @Column(name = "type", nullable = false))
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();
}
