package project.spaceshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.spaceshop.dto.CategoryDto;
import project.spaceshop.entity.Order;
import project.spaceshop.entity.Product;
import project.spaceshop.entity.ProductInOrder;
import project.spaceshop.repository.CategoryRepository;
import project.spaceshop.repository.ProductRepository;
import project.spaceshop.service.api.*;
import project.spaceshop.util.ImageUtil;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final OrderService orderService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductInOrderService productInOrderService;
    private final static int PAGE_SIZE = 6;

    @Autowired
    public AdminController(OrderService orderService, ProductService productService, CategoryService categoryService, CategoryRepository categoryRepository, ProductRepository productRepository, ProductInOrderService productInOrderService) {
        this.orderService = orderService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productInOrderService = productInOrderService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("dd/MM/yyyy").parse(value));
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        });

    }

    @GetMapping(value = "/admin")
    public String adminAccount(Model model) {
        return "admins";
    }

    @GetMapping(value = "/admin/order/page/{pageNo}")
    public String getAllOrders(@PathVariable("pageNo") int pageNo, Model model) {
        Page<Order> page = orderService.findPaginated(pageNo, PAGE_SIZE);
        List<Order> list = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("orders", list);
        return "adminOrders";
    }

    @GetMapping(value = "/admin/order/{idOrder}")
    public String getProductsInOrder(Model model, @PathVariable("idOrder") int idOrder){
        List<ProductInOrder> productInOrderList = productInOrderService.findAllByOrderId(idOrder);
        List<Product> products = new ArrayList<>();
        for (ProductInOrder productInOrder : productInOrderList){
            products.add(productService.findProductById(productInOrder.getProduct().getId()));
        }
        model.addAttribute("products", products);
        model.addAttribute("imgUtil", new ImageUtil());
        return "adminProductsInOrder";
    }

    @GetMapping(value = "admin/order/status")
    public String changeOrderStatus(@RequestParam(name = "orderStatus") String orderStatus,
                                    @RequestParam(name = "orderId") int orderId) {
        orderService.changeOrderStatusById(orderId, orderStatus);
        return "redirect:/admin/order/page/1";
    }

    @GetMapping(value = "/admin/product")
    public String adminProductsPage(){
        return "adminProductPage";
    }

    @GetMapping(value = "/admin/category/new")
    public String createCategory(Model model){
        CategoryDto category = new CategoryDto();
        model.addAttribute("category", category);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("categoryName", category.getCategoryName());
        return "newCategory";
    }

    @PostMapping(value = "/admin/category/new-process")
    public String createCategoryProcess(CategoryDto category){
        categoryService.saveCategory(category);
        return "redirect:/admin/category/new";
    }

    @PostMapping(value = "/admin/category/new/{idCategory}")
    public String deleteCategoryById(@PathVariable("idCategory") int id){
        if (productService.findProductByCategory(id).isEmpty()){
            categoryService.deleteCategoryById(id);
            return "redirect:/admin/category/new";
        } else return "errorOfNotNullCategory";

    }

    @GetMapping(value = "/admin/existing/product/{pageNo}")
    public String existingProducts(@PathVariable("pageNo") int pageNo, Model model){
        Page<Product> page = productService.findPaginated(pageNo, PAGE_SIZE);
        List<Product> list = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("products", list);
        model.addAttribute("imgUtil", new ImageUtil());
        return "existingProducts";
    }

    @GetMapping(value = "/admin/product/new")
    public String createProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("imgUtil", new ImageUtil());
        return "newProduct";
    }

    @PostMapping(value = "/admin/product/new-process")
    public String createProductProcess(Product product){
        productService.saveProduct(product);
        return "redirect:/admin/product/new";
    }
//
//    @RequestParam(name = "dateStart") Date dateStart,
//    @RequestParam(name = "dateEnd") Date dateEnd,

    @GetMapping(value = "/admin/statistic")
    public String getStatistic(Model model){
        String dateStart = "";
        String dateEnd = "";
        model.addAttribute("dateStart", dateStart);
        model.addAttribute("dateEnd", dateEnd);
        return "adminStatistic";
    }

    @GetMapping(value = "/admin/statistic/income")
    public String getStatistic(@RequestParam(name = "dateStart", required = false) String dateStart,
                               @RequestParam(name = "dateEnd", required = false) String dateEnd, Model model) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStartParsed = format.parse(dateStart);
        Date dateEndParsed = format.parse(dateEnd);
        NumberFormat f = NumberFormat.getInstance(new Locale("ru", "RU"));
        int i = orderService.getIncomeByDatePeriod(dateStartParsed, dateEndParsed);
        model.addAttribute("income", f.format(i));
        return "adminIncome";
    }

}
