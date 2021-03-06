package sqlite.test.entity;

import rabbit.open.orm.annotation.Column;
import rabbit.open.orm.annotation.Entity;
import rabbit.open.orm.annotation.PrimaryKey;
import rabbit.open.orm.dml.policy.Policy;

@Entity("T_DEPARTMENT")
public class SQLiteDepartment {

    @PrimaryKey(policy=Policy.AUTOINCREMENT)
    @Column("ID")
    private Long id;

    @Column("NAME")
    private String name;
    
    @Column("TEAM_ID")
    private SQLiteTeam team;

    /**
     * @param name
     * @param team
     */
    public SQLiteDepartment(String name, SQLiteTeam team) {
        super();
        this.name = name;
        this.team = team;
    }

    public SQLiteDepartment() {
        super();
    }

    @Override
    public String toString() {
        return "Department [id=" + id + ", name=" + name + ", team=" + team
                + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SQLiteTeam getTeam() {
        return team;
    }

    public void setTeam(SQLiteTeam team) {
        this.team = team;
    }
    
}
