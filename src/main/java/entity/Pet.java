package entity;

public class Pet {
    private int id_pet;
    private String pet;
    private int age;
    private int id_profile;

    public Pet() {

    }

    public int getId() {
        return id_pet;
    }

    public void setId(int id_pet) {
        this.id_pet = id_pet;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIdProfile() {
        return id_profile;
    }

    public void setIdProfile(int id_profile) {
        this.id_profile = id_profile;
    }
}
