-- -----------------------------------------------------
-- Populate product_category table
-- -----------------------------------------------------
INSERT INTO `product_category` (category_name) VALUES ('CD');
INSERT INTO `product_category` (category_name) VALUES ('Vinyl');
INSERT INTO `product_category` (category_name) VALUES ('DVD');
INSERT INTO `product_category` (category_name) VALUES ('Book');

-- -----------------------------------------------------
-- Populate products table
-- -----------------------------------------------------
-- -----------------------------------------------------
-- >>>>>>>> CD
-- -----------------------------------------------------
INSERT INTO `product` (sku, name, description, unit_price, image_url, units_in_stock,category_id) 
VALUES ('cd000001', 
		'Metallica: Master of puppets', 
        'Master of Puppets is the third studio album by the American heavy metal band Metallica, released " +
			"on March 3, 1986, by Elektra Records.',
        19.99,
        'assets/images/cd/metallica-master-of-puppets.jpg',
        20,
        1);
        
INSERT INTO `product` (sku, name, description, unit_price, image_url, units_in_stock,category_id) 
VALUES ('cd000002', 
		'Iron Maiden: Powerslave', 
        'Powerslave is the fifth studio album by the English " +
                "heavy metal band Iron Maiden, released on 3 September 1984 through " +
                "EMI Records in Europe and its sister label Capitol Records in North America.',
        19.99,
        'assets/images/cd/iron-maiden-powerslave.jpg',
        15,
        1);
        
INSERT INTO `product` (sku, name, description, unit_price, image_url, units_in_stock,category_id) 
VALUES ('cd000003', 
		'Guns \'n\' Roses: Appetite for Destruction', 
        'Appetite for Destruction is the debut studio album by American " +
                "hard rock band Guns N\' Roses. It was released on July 21, 1987, by Geffen Records.',
        19.99,
        'assets/images/cd/guns-n-roses-appetite-for-destruction.jpg',
        18,
        1);
        
-- -----------------------------------------------------
-- >>>>>>>> VINYL
-- -----------------------------------------------------
INSERT INTO `product` (sku, name, description, unit_price, image_url, units_in_stock,category_id) 
VALUES ('vl000001', 
		'Iron Maiden: Rock in Rio', 
        'Rock in Rio is a live album and video by English heavy metal " +
                "band Iron Maiden, recorded at the Rock in Rio festival, Brazil in 2001 on " +
                "the last night of the Brave New World Tour. The band played to approximately " +
                "250,000 people; the second largest crowd of their career',
        44.99,
        'assets/images/vinyl/iron-maiden-rock-in-rio.jpg',
        10,
        2);

INSERT INTO `product` (sku, name, description, unit_price, image_url, units_in_stock,category_id) 
VALUES ('vl000002', 
		'Slayer: South of Heaven', 
        'South of Heaven is the fourth studio album by American thrash " +
                "metal band Slayer, released on July 5, 1988 by Def Jam Recordings.',
        23.99,
        'assets/images/vinyl/slayer-south-of-heaven.jpg',
        8,
        2);
        
INSERT INTO `product` (sku, name, description, unit_price, image_url, units_in_stock,category_id) 
VALUES ('vl000003', 
		'Metallica: And Justice for All', 
        '...And Justice for All is the fourth studio album by American heavy " +
                "metal band Metallica, released on September 7, 1988[4] by Elektra Records. It was " +
                "the first album following the death of bassist Cliff Burton in 1986, and the first " +
                "to feature new bassist Jason Newsted.',
        44.99,
        'assets/images/vinyl/metallica-and-justice-for-all.jpg',
        0,
        2);
        
-- -----------------------------------------------------
-- >>>>>>>> DVD
-- -----------------------------------------------------
INSERT INTO `product` (sku, name, description, unit_price, image_url, units_in_stock,category_id) 
VALUES ('dd000001', 
		'Iron Maiden: The Early Years', 
        'The History of Iron Maiden – Part 1: The Early Days is a DVD video " +
                "by Iron Maiden, released in 2004. It features the first part of The History of" +
                " Iron Maiden series, a 90-minute documentary which describes their beginnings in" +
                " London\'s East End in 1975 through to the Piece of Mind album and tour in 1983.',
        14.99,
        'assets/images/dvd/iron-maiden-the-early-days.jpg',
        10,
        3);
        
INSERT INTO `product` (sku, name, description, unit_price, image_url, units_in_stock,category_id) 
VALUES ('dd000002', 
		'Slayer: War at Warfield', 
        'War at the Warfield is a concert video by Slayer which was released " +
                "on July 29, 2003, through American Recordings.[1][2] Recorded at Warfield Theatre" +
                " in San Francisco, California, on December 7, 2001, it is the band\'s second video album.',
        14.99,
        'assets/images/dvd/slayer-war-at-the-warfield.jpg',
        10,
        3);
        
INSERT INTO `product` (sku, name, description, unit_price, image_url, units_in_stock,category_id) 
VALUES ('dd000003', 
		'Iron Maiden: Death on the Road', 
        'Death on the Road is a live album and video released by British heavy " +
                "metal band Iron Maiden on 29 August 2005 on CD and vinyl, and on 6 February 2006 " +
                "on DVD. The album was recorded at Westfalenhallen in Dortmund, Germany on 24 November " +
                "2003, during the Dance of Death World Tour.',
        14.99,
        'assets/images/dvd/iron-maiden-death-on-the-road.jpg',
        10,
        3);
        
-- -----------------------------------------------------
-- >>>>>>>> BOOK
-- -----------------------------------------------------
INSERT INTO `product` (sku, name, description, unit_price, image_url, units_in_stock,category_id) 
VALUES ('bk000001', 
		'Run to the Hills: Iron Maiden, the Authorized Biography', 
        'For more than 25 years, Iron Maiden has led the way in heavy metal " +
                "-selling over 50 million records, setting trends, and influencing everyone from " +
                "Metallica to Marilyn Manson. In this popular biography, now in its third edition," +
                " the authors interview individual band members and get their stories of how it all" +
                " began, describe Maiden\'s rise to the top, and provide a general picture of the music" +
                " industry.',
		 22.99,
        'assets/images/book/iron-maiden-run-to-the-hills.jpg',
        10,
        4);
        
INSERT INTO `product` (sku, name, description, unit_price, image_url, units_in_stock,category_id) 
VALUES ('bk000002', 
		'Metallica: The $24.95 Book', 
        'The metal band everybody knows—but nobody has ever figured out. " +
                "Ben Apatoff has taken the ultimate deep dive into the dark legend of Metallica, " +
                "one of rock\'s weirdest and most fascinating mysteries. In this brilliant book, " +
                "he tells the definitive story of how a band of nobodies took on the world—and why" +
                " the world will never be the same.',
		 24.99,
        'assets/images/book/metallica-the-$24.95-book.jpg',
        0,
        4);
INSERT INTO `product` (sku, name, description, unit_price, image_url, units_in_stock,category_id) 
VALUES ('bk000003', 
		'On The Road With The Ramones', 
        'Throughout the remarkable twenty-two-year career of the Ramones the " +
                "seminal punk rock band, Rock \'n\' Roll Hall of Famers, Recording Academy Grammy " +
                "Lifetime Achievement Award winners and inductees into The Library of Congress\' " +
                "National Recording Registry, Monte A. Melnick saw it all. He was the band\'s tour" +
                " manager from their 1974 CBGB debut to their final show in 1996. Now, in this NEW " +
                "UPDATED EDITION he tells his story. Full of insider perspectives and exclusive " +
                "interviews and packed with over 250 personal color photos and images',
		 19.99,
        'assets/images/book/ramones-on-the-road.jpg',
        5,
        4);