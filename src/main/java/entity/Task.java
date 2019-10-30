package entity;

import enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String name;

    @Column
    String description;

    @Column
    Date startDate;

    @Column
    Date endDate;

    @Enumerated
    @Column
    Status status;

    @ManyToOne
    @JoinTable(name = "employer_task")
    Employer employer;


    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
