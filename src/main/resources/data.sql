# DELETE FROM trips;
# DELETE FROM hotels;
# DELETE FROM airports;
# DELETE FROM cities;
# DELETE FROM countries;
# DELETE FROM continents;
#
# insert into continents(continent_id,continent_name)values ('1','Europe');
# insert into continents(continent_id,continent_name)values ('2','Asia');
# insert into continents(continent_id,continent_name)values ('3','Africa');
# insert into continents(continent_id,continent_name)values ('4','South America');
# insert into continents(continent_id,continent_name)values ('5','North America');
# insert into continents(continent_id,continent_name)values ('6','Australia');
# insert into countries  (id, country_name,continent_id)values (1,'Poland',1);
# insert into countries  (id, country_name,continent_id)values (2,'France',1);
# insert into countries  (id, country_name,continent_id)values (3,'Great Britain',1);
# insert into countries  (id, country_name,continent_id)values (4,'Iran',2);
# insert into countries  (id, country_name,continent_id)values (5,'Czad',3);
# insert into cities  (id, city_name,country_id)values (1,'London',3);
# insert into cities  (id, city_name,country_id)values (2,'Paris',2);
# insert into cities  (id, city_name,country_id)values (3,'Wachock',1);
# insert into cities  (id, city_name,country_id)values (4,'Teheran',4);
# insert into cities  (id, city_name,country_id)values (5,'Katowice',1);
# insert into airports (id, airport_name, city_id) value (1, 'Orly', 2);
# insert into airports (id, airport_name, city_id) value (2, 'Heathrow', 1);
# insert into airports (id, airport_name, city_id) value (3, 'Stansted', 1);
# insert into airports (id, airport_name, city_id) value (4, 'Charles De-Gaulle', 2);
# insert into airports (id, airport_name, city_id) value (5, 'Beauvais', 2);
# insert into airports (id, airport_name, city_id) value (6, 'Pyrzowice', 5);
# insert into hotels (id, description, standard, hotel_name, city_id) value (1, 'Small hotel', '4Stars', 'Hilton', 1);
# insert into hotels (id, description, standard, hotel_name, city_id) value (2, 'Cosy country hotel ', '3Stars', 'Zacisze', 3);
# insert into hotels (id, description, standard, hotel_name, city_id) value (3, 'Luxury hotel in big city ', '5Stars', 'Mariott', 2);
# insert into hotels (id, description, standard, hotel_name, city_id) value (4, 'Boutique hotel ', '4Stars', 'Le Corbusiere', 4);
#
# insert into trips (id, depart_date,return_date,adult_price,children_price,promo_price,board_type,home_airport_id_pk,destin_airport_id_pk,hotel_id_pk,number_days,adult_vacancy,children_vacancy)
# values (1,'2019-09-01','2019-09-15',1700.30,890.60,1200.00,'BB',6,1,3,15,12,2);
# insert into trips (id, depart_date,return_date,adult_price,children_price,promo_price,board_type,home_airport_id_pk,destin_airport_id_pk,hotel_id_pk,number_days,adult_vacancy,children_vacancy)
# values (2,'2019-09-01','2019-09-15',1200.60,690.60,900.00,'FB',6,2,1,15,3,0);
# insert into trips (id, depart_date,return_date,adult_price,children_price,promo_price,board_type,home_airport_id_pk,destin_airport_id_pk,hotel_id_pk,number_days,adult_vacancy,children_vacancy)
# values (3,'2019-06-15','2019-06-22',1200.60,690.60,900.00,'FB',6,2,1,7,2,0);
# insert into trips (id, depart_date,return_date,adult_price,children_price,promo_price,board_type,home_airport_id_pk,destin_airport_id_pk,hotel_id_pk,number_days,adult_vacancy,children_vacancy)
# values (4,'2019-06-23','2019-06-30',873.00,460.60,600.00,'FB',null,null,2,7,6,5);
#
# insert into visitors (id, first_name, last_name, city, street, street_nr,zip_code,age,trip_id_pk) value (1, 'Anna', 'Nowak', 'Katowice', 'Zamkowa','23/5','40-001',34,1);
# insert into visitors (id, first_name, last_name, city, street, street_nr,zip_code,age,trip_id_pk) value (2, 'Bożena', 'Kowalska', 'Bytom', 'Parkowa','1/6K','43-901',60,2);
# insert into visitors (id, first_name, last_name, city, street, street_nr,zip_code,age,trip_id_pk) value (3, 'Brajan', 'Nowak', 'Katowice', 'Zamkowa','23/5','40-001',8,1);
# insert into visitors (id, first_name, last_name, city, street, street_nr,zip_code,age,trip_id_pk) value (4, 'Janusz', 'Anzorge', 'Zawiercie', 'Pokątna','4','32-031',52,2);