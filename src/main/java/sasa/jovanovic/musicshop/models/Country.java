package sasa.jovanovic.musicshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="country")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    @JsonIgnore
    private List<State> states;
}
