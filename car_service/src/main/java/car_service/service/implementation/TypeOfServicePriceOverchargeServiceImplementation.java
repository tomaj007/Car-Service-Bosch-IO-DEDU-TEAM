package car_service.service.implementation;

import car_service.data.entity.TypeOfService;
import car_service.data.entity.TypeOfServicePriceOvercharge;
import car_service.data.repository.TypeOfServicePriceOverchargeRepository;
import car_service.service.TypeOfServicePriceOverchargeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeOfServicePriceOverchargeServiceImplementation implements TypeOfServicePriceOverchargeService {

    private final TypeOfServicePriceOverchargeRepository typeOfServicePriceOverchargeRepository;

    public TypeOfServicePriceOverchargeServiceImplementation(TypeOfServicePriceOverchargeRepository typeOfServicePriceOverchargeRepository) {
        this.typeOfServicePriceOverchargeRepository = typeOfServicePriceOverchargeRepository;
    }

    public TypeOfServicePriceOverchargeRepository getTypeOfServicePriceOverchargeRepository() {
        return typeOfServicePriceOverchargeRepository;
    }

    @Override
    public List<TypeOfServicePriceOvercharge> getTypeOfServicePriceOvercharge() {
        return typeOfServicePriceOverchargeRepository.findAll();
    }

    @Override
    public TypeOfServicePriceOvercharge getTypeOfServicePriceOvercharge(long id) {
        return typeOfServicePriceOverchargeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid typeOfServicePriceOvercharge id: " + id));
    }

    @Override
    public TypeOfServicePriceOvercharge createTypeOfServicePriceOvercharge(TypeOfServicePriceOvercharge typeOfServicePriceOvercharge) {
        return typeOfServicePriceOverchargeRepository.save(typeOfServicePriceOvercharge);
    }

    @Override
    public TypeOfServicePriceOvercharge updateTypeOfServicePriceOvercharge(TypeOfServicePriceOvercharge typeOfServicePriceOvercharge, long id) {
        typeOfServicePriceOvercharge.setId(id);
        return typeOfServicePriceOverchargeRepository.save(typeOfServicePriceOvercharge);
    }

    @Override
    public void deleteTypeOfServicePriceOvercharge(long id) {
        typeOfServicePriceOverchargeRepository.deleteById(id);
    }

    @Override
    public TypeOfService findByTypeOfService(long id) {
        return getTypeOfServicePriceOvercharge(id).getTypeOfService();
    }

}