package repository;

import entity.Pet;
import exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetRepository {

    private final String INSERT_QUERY = "INSERT INTO pet(pet, age, id_profile) VALUES (?, ?, ?)";
    private final String GET_BY_PET_QUERY = "SELECT * FROM pet WHERE id_profile = ?";
    private final String GET_BY_PET_AND_AGE_QUERY = "SELECT * FROM pet WHERE id_profile = ? AND pet LIKE ?";
    private final String UPDATE_QUERY = "UPDATE pet SET pet = ?, age = ? WHERE id_profile = ? AND id_pet = ?";
    private final String DELETE_QUERY = "DELETE FROM pet WHERE id_pet = ? AND id_profile = ?";
    private final String GET_ALL_PETS_QUERY = "SELECT * FROM pet";


    public boolean savePet(Pet pet) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
            ps.setString(1, pet.getPet());
            ps.setInt(2, pet.getAge());
            ps.setInt(3, pet.getIdProfile());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pet> getByPet(int idProfile) {
        List<Pet> pets = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_BY_PET_QUERY)) {
            ps.setInt(1, idProfile);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pet p = new Pet();
                    p.setId(rs.getInt("id_pet"));
                    p.setPet(rs.getString("pet"));
                    p.setAge(rs.getInt("age"));
                    p.setIdProfile(rs.getInt("id_profile"));
                    pets.add(p);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pets;
    }

    public List<Pet> getPetsByNameAndAge(int idProfile, String petName) {
        List<Pet> pets = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_PET_AND_AGE_QUERY)) {

            preparedStatement.setInt(1, idProfile);
            preparedStatement.setString(2, "%" + petName + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Pet pet = new Pet();
                    pet.setId(resultSet.getInt("id_pet"));
                    pet.setPet(resultSet.getString("pet"));
                    pet.setAge(resultSet.getInt("age"));
                    pet.setIdProfile(resultSet.getInt("id_profile"));
                    pets.add(pet);
                }
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return pets;
    }

    public boolean updatePet(Pet pet) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY)) {
            ps.setString(1, pet.getPet());
            ps.setInt(2, pet.getAge());
            ps.setInt(3, pet.getIdProfile());
            ps.setInt(4, pet.getId());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public boolean deletePet(int petId, int profileId) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_QUERY)) {
            ps.setInt(1, petId);
            ps.setInt(2, profileId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_ALL_PETS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pet p = new Pet();
                    p.setId(rs.getInt("id_pet"));
                    p.setPet(rs.getString("pet"));
                    p.setAge(rs.getInt("age"));
                    p.setIdProfile(rs.getInt("id_profile"));
                    pets.add(p);
                }
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return pets;
    }
}
