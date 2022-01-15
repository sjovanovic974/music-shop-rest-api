package sasa.jovanovic.musicshop.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import sasa.jovanovic.musicshop.models.Product;
import sasa.jovanovic.musicshop.models.ProductCategory;
import sasa.jovanovic.musicshop.services.ProductCategoryService;
import sasa.jovanovic.musicshop.services.ProductService;

import java.math.BigDecimal;

@Component
@Profile("default")
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
        loadData();
    }

    private void loadData() {

        ProductCategory cds = new ProductCategory();
        cds.setCategoryName("CD");

        productCategoryService.saveProductCategory(cds);

        Product cd1 = new Product();
        cd1.setSku("cd000001");
        cd1.setName("Metallica: Master of puppets");
        cd1.setDescription("Master of Puppets is the third studio album by the American heavy " +
                "metal band Metallica, released on March 3, 1986, by Elektra Records.");
        cd1.setUnitPrice(new BigDecimal("19.99"));
        cd1.setImageUrl("assets/images/cd/metallica-master-of-puppets.jpg");
        cd1.setActive(true);
        cd1.setUnitsInStock(20);
        cd1.setCategory(cds);

        productService.saveProduct(cd1);

        Product cd2 = new Product();
        cd2.setSku("cd000002");
        cd2.setName("Iron Maiden: Powerslave");
        cd2.setDescription("Powerslave is the fifth studio album by the English " +
                "heavy metal band Iron Maiden, released on 3 September 1984 through " +
                "EMI Records in Europe and its sister label Capitol Records in North America.");
        cd2.setUnitPrice(new BigDecimal("19.99"));
        cd2.setImageUrl("assets/images/cd/iron-maiden-powerslave.jpg");
        cd2.setActive(true);
        cd2.setUnitsInStock(15);
        cd2.setCategory(cds);

        productService.saveProduct(cd2);

        Product cd3 = new Product();
        cd3.setSku("cd000003");
        cd3.setName("Guns'n'Roses: Appetite for Destruction");
        cd3.setDescription("Appetite for Destruction is the debut studio album by American " +
                "hard rock band Guns N' Roses. It was released on July 21, 1987, by Geffen Records.");
        cd3.setUnitPrice(new BigDecimal("19.99"));
        cd3.setImageUrl("assets/images/cd/guns-n-roses-appetite-for-destruction.jpg");
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
        v1.setDescription("Rock in Rio is a live album and video by English heavy metal " +
                "band Iron Maiden, recorded at the Rock in Rio festival, Brazil in 2001 on " +
                "the last night of the Brave New World Tour. The band played to approximately " +
                "250,000 people; the second largest crowd of their career");
        v1.setUnitPrice(new BigDecimal("44.99"));
        v1.setImageUrl("assets/images/vinyl/iron-maiden-rock-in-rio.jpg");
        v1.setActive(true);
        v1.setUnitsInStock(10);
        v1.setCategory(vinyls);

        productService.saveProduct(v1);

        Product v2 = new Product();
        v2.setSku("vl000002");
        v2.setName("Slayer: South of Heaven");
        v2.setDescription("South of Heaven is the fourth studio album by American thrash " +
                "metal band Slayer, released on July 5, 1988 by Def Jam Recordings.");
        v2.setUnitPrice(new BigDecimal("23.99"));
        v2.setImageUrl("assets/images/vinyl/slayer-south-of-heaven.jpg");
        v2.setActive(true);
        v2.setUnitsInStock(10);
        v2.setCategory(vinyls);

        productService.saveProduct(v2);

        Product v3 = new Product();
        v3.setSku("vl000003");
        v3.setName("Metallica: And Justice for All");
        v3.setDescription("...And Justice for All is the fourth studio album by American heavy " +
                "metal band Metallica, released on September 7, 1988[4] by Elektra Records. It was " +
                "the first album following the death of bassist Cliff Burton in 1986, and the first " +
                "to feature new bassist Jason Newsted.");
        v3.setUnitPrice(new BigDecimal("44.99"));
        v3.setImageUrl("assets/images/vinyl/metallica-and-justice-for-all.jpg");
        v3.setActive(true);
        v3.setUnitsInStock(0);
        v3.setCategory(vinyls);

        productService.saveProduct(v3);

        ProductCategory dvds = new ProductCategory();
        dvds.setCategoryName("DVD");

        productCategoryService.saveProductCategory(dvds);

        Product d1 = new Product();
        d1.setSku("dd000001");
        d1.setName("Iron Maiden: The Early Years");
        d1.setDescription("The History of Iron Maiden – Part 1: The Early Days is a DVD video " +
                "by Iron Maiden, released in 2004. It features the first part of The History of" +
                " Iron Maiden series, a 90-minute documentary which describes their beginnings in" +
                " London's East End in 1975 through to the Piece of Mind album and tour in 1983. ");
        d1.setUnitPrice(new BigDecimal("14.99"));
        d1.setImageUrl("assets/images/dvd/iron-maiden-the-early-days.jpg");
        d1.setActive(true);
        d1.setUnitsInStock(10);
        d1.setCategory(dvds);

        productService.saveProduct(d1);

        Product d2 = new Product();
        d2.setSku("dd000002");
        d2.setName("Slayer: War at Warfield");
        d2.setDescription("War at the Warfield is a concert video by Slayer which was released " +
                "on July 29, 2003, through American Recordings.[1][2] Recorded at Warfield Theatre" +
                " in San Francisco, California, on December 7, 2001, it is the band's second video album.");
        d2.setUnitPrice(new BigDecimal("14.99"));
        d2.setImageUrl("assets/images/dvd/slayer-war-at-the-warfield.jpg");
        d2.setActive(true);
        d2.setUnitsInStock(10);
        d2.setCategory(dvds);

        productService.saveProduct(d2);

        Product d3 = new Product();
        d3.setSku("dd000003");
        d3.setName("Iron Maiden: Death on the Road");
        d3.setDescription("Death on the Road is a live album and video released by British heavy " +
                "metal band Iron Maiden on 29 August 2005 on CD and vinyl, and on 6 February 2006 " +
                "on DVD. The album was recorded at Westfalenhallen in Dortmund, Germany on 24 November " +
                "2003, during the Dance of Death World Tour.");
        d3.setUnitPrice(new BigDecimal("14.99"));
        d3.setImageUrl("assets/images/dvd/iron-maiden-death-on-the-road.jpg");
        d3.setActive(true);
        d3.setUnitsInStock(10);
        d3.setCategory(dvds);

        productService.saveProduct(d3);


        ProductCategory books = new ProductCategory();
        books.setCategoryName("Book");

        productCategoryService.saveProductCategory(books);

        Product b1 = new Product();
        b1.setSku("bk000001");
        b1.setName("Run to the Hills: Iron Maiden, the Authorized Biography");
        b1.setDescription("For more than 25 years, Iron Maiden has led the way in heavy metal " +
                "-selling over 50 million records, setting trends, and influencing everyone from " +
                "Metallica to Marilyn Manson. In this popular biography, now in its third edition," +
                " the authors interview individual band members and get their stories of how it all" +
                " began, describe Maiden's rise to the top, and provide a general picture of the music" +
                " industry.");
        b1.setUnitPrice(new BigDecimal("22.99"));
        b1.setImageUrl("assets/images/book/iron-maiden-run-to-the-hills.jpg");
        b1.setActive(true);
        b1.setUnitsInStock(10);
        b1.setCategory(books);

        productService.saveProduct(b1);

        Product b2 = new Product();
        b2.setSku("bk000002");
        b2.setName("Metallica: The $24.95 Book");
        b2.setDescription("The metal band everybody knows—but nobody has ever figured out. " +
                "Ben Apatoff has taken the ultimate deep dive into the dark legend of Metallica, " +
                "one of rock's weirdest and most fascinating mysteries. In this brilliant book, " +
                "he tells the definitive story of how a band of nobodies took on the world—and why" +
                " the world will never be the same.");
        b2.setUnitPrice(new BigDecimal("24.95"));
        b2.setImageUrl("assets/images/book/metallica-the-$24.95-book.jpg");
        b2.setActive(true);
        b2.setUnitsInStock(0);
        b2.setCategory(books);

        productService.saveProduct(b2);

        Product b3 = new Product();
        b3.setSku("bk000003");
        b3.setName("On The Road With The Ramones");
        b3.setDescription("Throughout the remarkable twenty-two-year career of the Ramones the " +
                "seminal punk rock band, Rock 'n' Roll Hall of Famers, Recording Academy Grammy " +
                "Lifetime Achievement Award winners and inductees into The Library of Congress' " +
                "National Recording Registry, Monte A. Melnick saw it all. He was the band's tour" +
                " manager from their 1974 CBGB debut to their final show in 1996. Now, in this NEW " +
                "UPDATED EDITION he tells his story. Full of insider perspectives and exclusive " +
                "interviews and packed with over 250 personal color photos and images");
        b3.setUnitPrice(new BigDecimal("42.99"));
        b3.setImageUrl("assets/images/book/ramones-on-the-road.jpg");
        b3.setActive(true);
        b3.setUnitsInStock(5);
        b3.setCategory(books);

        productService.saveProduct(b3);
    }
}
