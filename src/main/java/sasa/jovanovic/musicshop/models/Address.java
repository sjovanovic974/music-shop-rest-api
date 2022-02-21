package sasa.jovanovic.musicshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street", nullable = false)
    @NotBlank
    @Length(min = 2)
    private String street;

    @Column(name = "city", nullable = false)
    @NotBlank
    @Length(min = 2)
    private String city;

    @Column(name = "state", nullable = false)
    @NotBlank
    private String state;

    @Column(name = "country", nullable = false)
    @NotBlank
    private String country;

    @Column(name = "zip_code", nullable = false)
    @NotBlank
    @Length(min = 5, max = 5)
    private String zipCode;

    @OneToOne
    @JsonIgnore
    @PrimaryKeyJoinColumn
    private Order order;
}
