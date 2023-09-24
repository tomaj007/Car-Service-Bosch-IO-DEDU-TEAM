package car_service.service.implementation;

import car_service.data.entity.Brand;
import car_service.data.repository.BrandRepository;
import car_service.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImplementation implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImplementation(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public BrandRepository getBrandRepository() {
        return brandRepository;
    }

    @Override
    public List<Brand> getBrand() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrand(long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Brand id: " + id));
    }

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Brand brand, long id) {
        brand.setIdBrands(id);

        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public List<Brand> findAllByNameContaining(String name) {
        return brandRepository.findAllByNameContaining(name);
    }

}