package me.alanfoster.camelry.blueprint.camel.dom.common;

/**
 * Represents a basic Person pojo.
 * This object exists purely for testing purposes.
 */
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String number;
    private Integer age;
    /**
     * A field with no getters/setters should not represent itself to blueprint's method intellisense
     */
    private String internalField;

    /**
     * Field with one character, an unlikely scenario, but it is an edge case none the less.
     */
    private Float f;

    // Public empty constructor, required for blueprint if no constructor args are given
    public Person() {
    }

    public Person(int id, String firstName, String lastName, String number, Integer age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.age = age;
    }

    // Second constructor with no id
    public Person(String firstName, String lastName, String number, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getF() {
        return f;
    }

    public void setF(Float f) {
        this.f = f;
    }
}
