package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "RuleName")
public class RuleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Column(name = "description")
    private String description;

    @Column(name = "json")
    private String json;

    @Column(name = "template", length = 512)
    private String template;

    @Column(name = "sqlStr")
    private String sqlStr;

    @Column(name = "sqlPart")
    private String sqlPart;

    public RuleName() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getJson() { return json; }
    public void setJson(String json) { this.json = json; }

    public String getTemplate() { return template; }
    public void setTemplate(String template) { this.template = template; }

    public String getSqlStr() { return sqlStr; }
    public void setSqlStr(String sqlStr) { this.sqlStr = sqlStr; }

    public String getSqlPart() { return sqlPart; }
    public void setSqlPart(String sqlPart) { this.sqlPart = sqlPart; }

    // Alias pour coller aux templates existants :
    // Les html utilisent "sql" (th:field="*{sql}" et ${ruleName.sql})
    public String getSql() {
        return this.sqlStr;
    }

    public void setSql(String sql) {
        this.sqlStr = sql;
    }
}
