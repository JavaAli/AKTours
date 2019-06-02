
insert into continents(continent_id,continent_name)values ('1','Europa');
insert into continents(continent_id,continent_name)values ('2','Asia');
insert into continents(continent_id,continent_name)values ('3','Africa');
insert into countries  (id, country_name,continent_id)values (1,'Poland',1);
insert into countries  (id, country_name,continent_id)values (2,'France',1);
insert into countries  (id, country_name,continent_id)values (3,'Great Britan',1);
insert into countries  (id, country_name,continent_id)values (4,'Iran',2);
insert into countries  (id, country_name,continent_id)values (5,'Czad',3);
insert into cities  (id, city_name,country_id)values (1,'Londyn',3);
insert into cities  (id, city_name,country_id)values (2,'Paris',2);
insert into cities  (id, city_name,country_id)values (3,'Wachock',1);
insert into cities  (id, city_name,country_id)values (4,'Teheran',4);
insert into airports (id, airport_name, city_id) value (1000, 'Orly', 2);
insert into airports (id, airport_name, city_id) value (1001, 'Heathrow', 1);
insert into airports (id, airport_name, city_id) value (1002, 'Stansted', 1);
insert into airports (id, airport_name, city_id) value (1003, 'Charles De-Gaulle', 2);
insert into airports (id, airport_name, city_id) value (1004, 'Beauvais', 2);
insert into hotels (id, description, standard, hotel_name, city_id) value (1, 'Small hotel', '4Stars', 'Hilton', 1);
insert into hotels (id, description, standard, hotel_name, city_id) value (2, 'Cosy country hotel ', '3Stars', 'Zacisze', 3);
insert into hotels (id, description, standard, hotel_name, city_id) value (3, 'Luxury hotel in big city ', '5Stars', 'Mariott', 2);
insert into hotels (id, description, standard, hotel_name, city_id) value (4, 'Boutique hotel ', '4Stars', 'Le Corbusiere', 4);

