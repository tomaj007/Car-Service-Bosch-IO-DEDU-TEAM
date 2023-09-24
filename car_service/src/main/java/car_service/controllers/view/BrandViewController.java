package car_service.controllers.view;

import car_service.data.entity.Brand;
import car_service.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/brandView")
public class BrandViewController {

    private final BrandService brandService;

    public BrandViewController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public String getBrand(Model model) {
        final List<Brand> brands = brandService.getBrand();
        model.addAttribute("brands", brands);
        return "/brand/brand";
    }

    @GetMapping("/create-brand")
    public String showCreateBrandForm(Model model) {
        model.addAttribute("brand", new Brand());
        return "/brand/create-brand";
    }

    @PostMapping("/create")
    public String createBrand(@ModelAttribute Brand brand) {
        brandService.createBrand(brand);
        return "redirect:/brandView";
    }

    @GetMapping("/edit/{id}")
    public String showEditBrand(Model model, @PathVariable Long id) {
        model.addAttribute("brand", brandService.getBrand(id));
        return "/brand/edit-brand";
    }

    @PostMapping("/update/{id}")
    public String updateBrand(Model model, @PathVariable Long id, Brand brand) {
        brandService.updateBrand(brand, id);
        return "redirect:/brandView";
    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable int id) {
        brandService.deleteBrand(id);
        return "redirect:/brandView";
    }
}