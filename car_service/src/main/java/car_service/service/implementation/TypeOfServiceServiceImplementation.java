package car_service.service.implementation;

import car_service.data.entity.TypeOfService;
import car_service.data.repository.TypeOfServiceRepository;
import car_service.service.TypeOfServiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfServiceServiceImplementation implements TypeOfServiceService {

    private final TypeOfServiceRepository typeOfServiceRepository;

    public TypeOfServiceServiceImplementation(TypeOfServiceRepository typeOfServiceRepository) {
        this.typeOfServiceRepository = typeOfServiceRepository;
    }

    public TypeOfServiceRepository getTypeOfServiceRepository() {
        return typeOfServiceRepository;
    }

    @Override
    public List<TypeOfService> getTypeOfService() {
        return typeOfServiceRepository.findAll();
    }

    @Override
    public TypeOfService getTypeOfService(long id) {
        return typeOfServiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid typeOfService id: " + id));
    }

    @Override
    public TypeOfService createTypeOfService(TypeOfService typeOfService) {
        return typeOfServiceRepository.save(typeOfService);
    }

    @Override
    public TypeOfService updateTypeOfService(TypeOfService typeOfService, long id) {
        typeOfService.setIdTypeOfService(id);

        return typeOfServiceRepository.save(typeOfService);
    }

    @Override
    public void deleteTypeOfService(long id) {
        typeOfServiceRepository.deleteById(id);
    }

    @Override
    public List<TypeOfService> findByPriceBetween(double smallPrice, double bigPrice) {
        return typeOfServiceRepository.findByPriceBetween(smallPrice, bigPrice);
    }

    @Override
    public List<TypeOfService> findByDescriptionContaining(String description) {
        return typeOfServiceRepository.findByDescriptionContaining(description);
    }

    @Override
    public List<TypeOfService> findByDescription(String description) {
        return typeOfServiceRepository.findByDescription(description);
    }

    @Override
    public List<TypeOfService> findByIdLessThanOrderByType(long idTypeOfService) {
        return typeOfServiceRepository.findByIdTypeOfServiceLessThanOrderByType(idTypeOfService);
    }

}