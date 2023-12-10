create sequence travelorder_sequence start with 1 increment by 1;
INSERT INTO TravelOrder(id) VALUES (nextVal('travelorder_sequence'));
INSERT INTO TravelOrder(id) VALUES (nextVal('travelorder_sequence'));
INSERT INTO TravelOrder(id) VALUES (nextVal('travelorder_sequence'));
INSERT INTO TravelOrder(id) VALUES (nextVal('travelorder_sequence'));
INSERT INTO TravelOrder(id) VALUES (nextVal('travelorder_sequence'));
INSERT INTO TravelOrder(id) VALUES (nextVal('travelorder_sequence'));

create sequence flight_sequence start with 1 increment by 1;
INSERT INTO Flight(id, travelOrderId, fromAirport, toAirport) values (nextVal('flight_sequence'), 1, 'GRU', 'JUZ');
INSERT INTO Flight(id, travelOrderId, fromAirport, toAirport) values (nextVal('flight_sequence'), 2, 'JUZ', 'GRU');
INSERT INTO Flight(id, travelOrderId, fromAirport, toAirport) values (nextVal('flight_sequence'), 3, 'GRU', 'POA');
INSERT INTO Flight(id, travelOrderId, fromAirport, toAirport) values (nextVal('flight_sequence'), 4, 'GRU', 'LIS');

create sequence hotel_sequence start with 1 increment by 1;
INSERT INTO Hotel(id, travelOrderId, nights) values (nextVal('hotel_sequence'), 1, 3);
INSERT INTO Hotel(id, travelOrderId, nights) values (nextVal('hotel_sequence'), 2, 5);
INSERT INTO Hotel(id, travelOrderId, nights) values (nextVal('hotel_sequence'), 3, 4);
INSERT INTO Hotel(id, travelOrderId, nights) values (nextVal('hotel_sequence'), 4, 1);
INSERT INTO Hotel(id, travelOrderId, nights) values (nextVal('hotel_sequence'), 5, 2);
