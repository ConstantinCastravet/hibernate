package entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String name;


    @OneToMany(mappedBy = "discipline",fetch = FetchType.EAGER)
    List<Employer> members;


    @OneToOne
    Employer headOfDiscipline;

    public Discipline(String name){
        this.name=name;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
