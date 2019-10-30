package entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
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

    @Column
    String email;

    @NaturalId
    @Column
    String userName;

    @Column
    Date createdAt;

    @Column
    Boolean enabled;

    @ManyToMany
    @JoinTable(name = "employer_role")
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
                '}';
    }
}
