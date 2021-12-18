package com.example.hw9.domain;

import javax.persistence.*;

@Entity
@Table(name = "Cache")
public class Cache {
    private Long id;

    private String a;
    private String operator;
    private String b;
    private String result;

    public Cache() {
    }

    public Cache(String a, String operator, String b, String result) {
        this.a = a;
        this.operator = operator;
        this.b = b;
        this.result = result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Column(name = "a")
    public String getA() {
        return a;
    }

    public void setA(String firstArgument) {
        this.a = firstArgument;
    }

    @Column(name = "operator")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Column(name = "b")
    public String getB() {
        return b;
    }

    public void setB(String secondArgument) {
        this.b = secondArgument;
    }

    @Column(name = "result")
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}