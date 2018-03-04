package com.university.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@NamedQueries({
        @NamedQuery(name = Department.GET_DEPARTMENT_BY_NAME, query = "select d from Department d where d.name =:name")
})

@Entity
@Table(name = "departments")
public class Department {


    public static final String GET_DEPARTMENT_BY_NAME = "Department.getByName";

    public Department() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "chief")
    private String chief;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "departments_lectors",
            joinColumns = @JoinColumn(name = "departments_id"),
            inverseJoinColumns = @JoinColumn(name = "lectors_id"))
    private List<Lector> lectors;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public List<Lector> getLectors() {
        return lectors;
    }

    public void setLectors(List<Lector> lectors) {
        this.lectors = lectors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chief='" + chief + '\'' +
                '}';
    }
}
