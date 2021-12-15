package sasa.jovanovic.musicshop.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.models.ProductCategory;
import sasa.jovanovic.musicshop.services.ProductCategoryService;
import sasa.jovanovic.musicshop.services.ProductService;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    @Autowired
    public DataLoader(ProductService productService, ProductCategoryService productCategoryService) {
        this.productService = productService;
        this.productCategoryService = productCategoryService;
    }
    
    @Override
    public void run(String... args) throws Exception {

        int count = productService.getAllProducts().size();

        if(count == 0) {
            loadData();
        }
    }

    private void loadData(){

        ProductCategory cds = new ProductCategory();
        cds.setCategoryName("CD");

        productCategoryService.saveProductCategory(cds);

        Product cd1 = new Product();
        cd1.setSku("cd000001");
        cd1.setName("Metallica: Master of puppets");
        cd1.setDescription("3rd album");
        cd1.setUnitPrice(new BigDecimal("19.99"));
        cd1.setImageUrl("img/0001");
        cd1.setActive(true);
        cd1.setUnitsInStock(20);
        cd1.setCategory(cds);

        productService.saveProduct(cd1);

        Product cd2 = new Product();
        cd2.setSku("cd000002");
        cd2.setName("Iron Maiden: Powerslave");
        cd2.setDescription("Master Piece");
        cd2.setUnitPrice(new BigDecimal("19.99"));
        cd2.setImageUrl("img/0002");
        cd2.setActive(true);
        cd2.setUnitsInStock(15);
        cd2.setCategory(cds);

        productService.saveProduct(cd2);

        Product cd3 = new Product();
        cd3.setSku("cd000003");
        cd3.setName("Guns'n'Roses: Appetite for Destruction");
        cd3.setDescription("Powerful Debut");
        cd3.setUnitPrice(new BigDecimal("19.99"));
        cd3.setImageUrl("img/0003");
        cd3.setActive(true);
        cd3.setUnitsInStock(18);
        cd3.setCategory(cds);

        productService.saveProduct(cd3);
        

        ProductCategory vinyls = new ProductCategory();
        vinyls.setCategoryName("Vinyl");

        productCategoryService.saveProductCategory(vinyls);

        Product v1 = new Product();
        v1.setSku("vl000001");
        v1.setName("Iron Maiden: Rock in Rio");
        v1.setDescription("Double Live LP");
        v1.setUnitPrice(new BigDecimal("44.99"));
        v1.setImageUrl("img/0004");
        v1.setActive(true);
        v1.setUnitsInStock(10);
        v1.setCategory(vinyls);

        productService.saveProduct(v1);

        Product v2 = new Product();
        v2.setSku("vl000002");
        v2.setName("Slayer: South of Heaven");
        v2.setDescription("Classic Thrash Album");
        v2.setUnitPrice(new BigDecimal("23.99"));
        v2.setImageUrl("img/0005");
        v2.setActive(true);
        v2.setUnitsInStock(10);
        v2.setCategory(vinyls);

        productService.saveProduct(v2);

        Product v3 = new Product();
        v3.setSku("vl000003");
        v3.setName("Metallica: And Justice for All");
        v3.setDescription("Double LP");
        v3.setUnitPrice(new BigDecimal("44.99"));
        v3.setImageUrl("img/0006");
        v3.setActive(true);
        v3.setUnitsInStock(10);
        v3.setCategory(vinyls);

        productService.saveProduct(v3);

        ProductCategory dvds = new ProductCategory();
        dvds.setCategoryName("DVD");

        productCategoryService.saveProductCategory(dvds);

        Product d1 = new Product();
        d1.setSku("dd000001");
        d1.setName("Iron Maiden: The Early Years");
        d1.setDescription("Early History of the famous HM band");
        d1.setUnitPrice(new BigDecimal("14.99"));
        d1.setImageUrl("img/0007");
        d1.setActive(true);
        d1.setUnitsInStock(10);
        d1.setCategory(dvds);

        productService.saveProduct(d1);

        Product d2 = new Product();
        d2.setSku("dd000002");
        d2.setName("Slayer: War at Warfield");
        d2.setDescription("Thrash Gods at their best");
        d2.setUnitPrice(new BigDecimal("14.99"));
        d2.setImageUrl("img/0008");
        d2.setActive(true);
        d2.setUnitsInStock(10);
        d2.setCategory(dvds);

        productService.saveProduct(d2);

        Product d3 = new Product();
        d3.setSku("dd000003");
        d3.setName("Iron Maiden: Death on the Road");
        d3.setDescription("HM legends");
        d3.setUnitPrice(new BigDecimal("14.99"));
        d3.setImageUrl("img/0008");
        d3.setActive(true);
        d3.setUnitsInStock(10);
        d3.setCategory(dvds);

        productService.saveProduct(d3);

        
        ProductCategory books = new ProductCategory();
        books.setCategoryName("Book");

        productCategoryService.saveProductCategory(books);
        
        Product b1 = new Product();
        b1.setSku("bk000001");
        b1.setName("Iron Maiden: True Story");
        b1.setDescription("From the men who were there");
        b1.setUnitPrice(new BigDecimal("9.99"));
        b1.setImageUrl("img/0010");
        b1.setActive(true);
        b1.setUnitsInStock(10);
        b1.setCategory(books);

        productService.saveProduct(b1);

        Product b2 = new Product();
        b2.setSku("bk000002");
        b2.setName("Metallica: For whom the bells toll");
        b2.setDescription("The early years");
        b2.setUnitPrice(new BigDecimal("9.99"));
        b2.setImageUrl("img/0011");
        b2.setActive(true);
        b2.setUnitsInStock(10);
        b2.setCategory(books);

        productService.saveProduct(b2);

        Product b3 = new Product();
        b3.setSku("bk000003");
        b3.setName("Ramones: Gabba Gabba Hey");
        b3.setDescription("RnR music was never the same");
        b3.setUnitPrice(new BigDecimal("9.99"));
        b3.setImageUrl("img/0012");
        b3.setActive(true);
        b3.setUnitsInStock(5);
        b3.setCategory(books);

        productService.saveProduct(b3);
    }
}
