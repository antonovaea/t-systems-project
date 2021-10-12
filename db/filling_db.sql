INSERT INTO planetshop.categories (category_name)
	VALUES ('Planet');
INSERT INTO planetshop.categories (category_name)
	VALUES ('Galaxy');
INSERT INTO planetshop.categories (category_name)
	VALUES ('Comet');

INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('NU-14','1','25000', LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/planet.jpg'),'Temperature is always below 100 degrees Celsius','10','1','Cosmo-Yety');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('RX-18','1','39000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/planet1.jpg'),'Temperature is simmilar to the Earth','10','1','Humanoid');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('SW-25','1','15000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/planet2.jpg'),'Temperature on the one side is simmilar to the Earth, on the other side 60 degrees Celsius bellow zero','10','1','White bear');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Arrakis-12','1','80000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/planet3.jpg'),'Temperature is 70 degrees Celsius above zero','10','1','Desert human');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Blue-45','1','120000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/planet4.jpg'),'Temperature is simmilar to the Earth','10','1','Fish');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Bones-13','1','34000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/planet5.jpg'),'Temperature is zero degrees Celsius','10','1','Cyborg');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('MG-876','1','65000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/planet6.jpg'),'Temperature is 50 degrees Celsius above zero','10','1','Crocodile');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Tar-98','1','120000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/planet7.jpg'),'Temperature is simmilar to the Earth','10','1','Human');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Dream-11','1','55000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/planet8.jpg'),'Temperature is zero degrees Celsius','10','1','Dreamers');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('RJ-78','1','75000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/planet9.jpg'),'Temperature is 20 degrees Celsius','10','1','Gigantic bird');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('FR-2','1','180000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/planet10.jpg'),'Temperature is simmilar to the Earth','10','1','Dinosaur');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('ALONE-1','1','25000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/planet11.jpg'),'Temperature is always below 1160 degrees Celsius','10','1','No inhabitants');

INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Amerra','2','5700000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/galaxy.jpg'),'It includes 100 planets','10','1','Different');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Manas','2','3400000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/galaxy1.jpg'),'It includes 87 planets','10','1','Different');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Durk','2','8900000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/galaxy2.jpg'),'It includes 130 planets','10','1','Different');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Libellan','2','2100000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/galaxy3.jpg'),'It includes 20 planets','10','1','Different');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('B-18','2','4500000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/galaxy4.jpg'),'It includes 70 planets','10','1','Different');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Zonar','2','1100000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/galaxy5.jpg'),'It includes 6 planets','10','1','Different');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('ERRA','2','9800000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/galaxy6.jpg'),'It includes 178 planets','10','1','Different');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Blu','2','6200000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/galaxy7.jpg'),'It includes 67 planets','10','1','Different');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Dorak','2','5700000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/galaxy8.jpg'),'It includes 120 planets','10','1','Different');

INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Aqwa','3','670000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/comet.jpg'),'It\'s speed is 1500 km/sec','10','1','No inhabitants');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Juna','3','480000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/comet1.jpg'),'It\'s speed is 800 km/sec','10','1','No inhabitants');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Nabi','3','890000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/comet2.jpg'),'It\'s speed is 1800 km/sec','10','1','No inhabitants');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('RM-24','3','970000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/comet3.jpg'),'It\'s speed is 1500 km/sec','10','1','No inhabitants');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Poi','3','340000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/comet4.jpg'),'It\'s speed is 500 km/sec','10','1','No inhabitants');
INSERT INTO planetshop.products (product_name,id_category,price,image,description,amount_in_stock,available,inhabitants)
	VALUES ('Mun','3','1400000',LOAD_FILE('C:/java_projects/sources/bootspaceshop/src/main/resources/static/image/comet5.jpg'),'It\'s speed is 5600 km/sec','10','1','No inhabitants');


INSERT INTO planetshop.users (user_name, user_surname, user_date_of_birth, role, phone, email, password)
	VALUES ('user', 'user userov', '01.01.01', 'ADMIN', '89999999999', 'user@mail.ru', '1234');
INSERT INTO planetshop.users (user_name, user_surname, user_date_of_birth, role, phone, email, password)
	VALUES ('user2', 'user userov2', '02.02.02', 'USER', '82222222222', 'user2@mail.ru', '1234');


