package entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;
    @NaturalId
    @Column
    String email;

    @NaturalId
    @Column
    String userName;

    @Column
    Date createdAt;

    @Column
    Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles;

    @ManyToOne
    @JoinColumn(name = "discipline")
    Discipline discipline;

    @OneToMany(mappedBy = "employer")
    List<Task> task;

    @Override
    public String toString() {
        return "Employer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", roles=" + roles +
                ", discipline=" + discipline +
                "}\n";
    }
}
