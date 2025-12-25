package service;

import entity.Pet;
import exception.DBException;
import exception.ServiceException;
import repository.PetRepository;

import java.util.List;

public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public boolean savePet(String petName, int age, int idProfile) {
        if (petName == null || petName.isBlank()) {
            throw new ServiceException("Имя питомца не может быть пустым.");
        }
        if (age <= 0) {
            throw new ServiceException("Возраст должен быть положительным.");
        }

        Pet pet = new Pet();
        pet.setPet(petName);
        pet.setAge(age);
        pet.setIdProfile(idProfile);
        return petRepository.savePet(pet);
    }

    public List<Pet> getByPet(int idProfile) {
        return petRepository.getByPet(idProfile);
    }

    public List<Pet> getPetsByNameAndAge(int idProfile, String pet) {
        return petRepository.getPetsByNameAndAge(idProfile, pet);
    }

    public boolean updatePet(int id, String petName, int age, int profileId) throws ServiceException {
        if (petName == null || petName.isBlank()) {
            throw new ServiceException("Имя питомца не может быть пустым.");
        }
        if (age <= 0) {
            throw new ServiceException("Возраст должен быть положительным.");
        }

        Pet pet = new Pet();
        pet.setId(id);
        pet.setPet(petName);
        pet.setAge(age);
        pet.setIdProfile(profileId);

        return petRepository.updatePet(pet);
    }

    public boolean deletePet(int petId, int profileId) {
        return petRepository.deletePet(petId, profileId);
    }


        public List<Pet> getAllPets() throws DBException {
            return petRepository.getAllPets();
        }
}
