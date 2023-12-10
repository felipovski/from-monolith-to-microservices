create sequence hotel_sequence start with 1 increment by 1;
INSERT INTO Hotel(id, travelOrderId, nights) values (nextVal('hotel_sequence'), 1, 3);
INSERT INTO Hotel(id, travelOrderId, nights) values (nextVal('hotel_sequence'), 2, 5);
INSERT INTO Hotel(id, travelOrderId, nights) values (nextVal('hotel_sequence'), 3, 4);
INSERT INTO Hotel(id, travelOrderId, nights) values (nextVal('hotel_sequence'), 4, 1);
INSERT INTO Hotel(id, travelOrderId, nights) values (nextVal('hotel_sequence'), 5, 2);
