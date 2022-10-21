package com.jjj.crm.workbench.pojo;

import java.util.Objects;

/**
 * @className: com.jjj.crm.workbench.pojo.Chart
 * @description:
 * @author: 江骏杰
 * @create: 2022-10-21 19:37
 */
public class Chart {
    private String name;
    private String value;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chart chart = (Chart) o;
        return Objects.equals(name, chart.name) && Objects.equals(value, chart.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    public Chart() {
    }

    public Chart(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
