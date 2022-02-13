package sasa.jovanovic.musicshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="state")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;
}
