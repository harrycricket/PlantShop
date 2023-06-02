create database PlantShop
GO
use PlantShop
GO
create table Accounts(
    accID int identity(1,1)primary key,
    email varchar(30)  unique,
    password varchar(30),
    fullname varchar(30),
    phone varchar(12),
    status int check(status =1 or status=0),-- 1:active; 0:inactive
    role int check(role=1 or role=0),    --:admin, 0:user
	token varchar(50)
)
GO
create table Categories(
      CateID int identity(1,1) primary key,
      CateName varchar(30)
)
GO
create table Plants(
      PID  int identity(1,1) primary key,
      PName varchar(30),
      price int check(price>=0),
      imgPath varchar(100),
      description text,
      status int,    --1:active, 0:inactive
      CateID int foreign key references Categories(cateID)
)
GO
create table Orders(
    OrderID int identity(1,1) primary key,
    OrdDate date,
    shipdate date,
    status int check(status =1 or status=2 or status=3),--1:processing, 2: completed, 3: cancel
    AccID int foreign key references Accounts(accID)
)
GO
create table OrderDetails(
    DetailId int identity(1,1) primary key,
    OrderID int foreign key references Orders(orderID),
    FID int foreign key references Plants(pID),
    quantity int check(quantity>=1)
)
GO

insert into Categories values('Wildflower')
insert into Categories values('Bonsai')
insert into Categories values('Herb')
insert into Categories values('Flower')

insert into Plants values('Daisy', 20000,'images/img1.jpg','a small flower with white petals and a yellow centre that often grows in grass.',1,1)
insert into Plants values('Feverfew', 15000,'images/img2.jpg','a herb with thin, delicate leaves and flowers similar to a daisy, 
that is sometimes used to treat headaches.',1,1)
insert into Plants values('White clover', 30000,'images/img3.jpg','a small plant with three round leaves on each stem, often fed to cows.',1,1)
insert into Plants values('Welsh poppy', 20000,'images/img4.jpg','a plant with large, delicate flowers that are typically red and have small, black seeds.',1,1)
insert into Plants values('Silverweed', 10000,'images/img5.jpg','a rosaceous perennial creeping plant, Potentilla anserina, 
with silvery pinnate leaves and yellow flowers.',1,1)
insert into Plants values('Pine', 21000,'images/img6.jpg','an evergreen tree that grows in cooler areas of the world.',1,2)
insert into Plants values('Yew', 35000,'images/img7.jpg','an evergreen tree with flat leaves like needles and small, red fruits.',1,2)
insert into Plants values('Fuchsia', 50000,'images/img8.jpg','a small plant, often grown in gardens, that has red, purple, or white flowers that hang down.',1,2)
insert into Plants values('Privet', 40000,'images/img9.jpg','an evergreen bush often grown as a hedge around the edges of gardens.',1,2)
insert into Plants values('Jade', 20000,'images/img10.jpg','a green precious stone from which jewellery and small decorative items are made, 
especially in China and Japan.',1,2)
insert into Plants values('Aloe vera', 25000,'images/img11.jpg','a type of plant with thick pointed leaves, 
or the thick liquid found in the leaves that is used to treat damaged skin.',1,3)
insert into Plants values('Lavender', 35000,'images/img12.jpg','a plant that has grey-green leaves like needles and small, pale purple flowers; 
the dried flowers and stems of the plant that are used in soap, etc. because of their strong, pleasant smell.',1,3)
insert into Plants values('Anise', 45000,'images/img13.jpg','a Mediterranean plant with small, yellowish-white flowers and seeds that taste of liquorice, 
used to give this flavour to food and drink.',1,3)
insert into Plants values('Marshmallow', 55000,'images/img14.jpg',' Marshmallow root (Althaea officinalis) is a perennial herb that native to Europe, 
Western Asia, and Northern Africa.',1,3)
insert into Plants values('Eucalyptus', 60000,'images/img15.jpg','any of several types of tree, found especially in Australia, 
that produce an oil with a strong smell. Eucalyptus oil is used in medicine and industry.',1,3)
insert into Plants values('Rose', 100000,'images/img16.jpg','a garden plant with thorns on its stems and pleasant-smelling flowers, or a flower from this plant.',1,4)
insert into Plants values('Lily', 90000,'images/img17.jpg','The flowers represent purity, innocence and rebirth: in religious iconography, they often represent the Virgin Mary, 
and are also often depicted at the Resurrection of Christ.',1,4)
insert into Plants values('Orchid', 500000,'images/img18.jpg','a plant with beautifully coloured flowers that have an unusual shape',1,4)
insert into Plants values('Lotus', 200000,'images/img19.jpg','a tropical plant with large, flat leaves that float on the surface of lakes and pools and large 
round flowers with layers of petals and a cone-shaped part in the middle',1,4)
insert into Plants values('Hydrangea', 40000,'images/img20.jpg','a bush on which there are round groups of pink, white, or blue flowers',1,4)

update Plants set status = 1;