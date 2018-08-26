insert into hms_hotel(
hotel_id,
hotel_name,
hotel_address,
hotel_rating,
hotel_staff_count,
hotel_room_count,
hotel_bed_count,
hotel_logo,
hotel_room_doorno_format,
hotel_status,
hotel_primary_email,
hotel_primary_phone,
hotel_primary_mobile,
hotel_secondary_email,
hotel_secondary_phone,
hotel_secondary_mobile,
hotel_fax_number
)values(
'xGfKl1',
'aybits_master',
'{
	"address_line1": "HMS Master Hotel",
	"address_line2": "Avenue Road",
	"city": "Bengaluru",
	"state": "Karnataka",
	"pin_code": "560032",
	"country": "India",
	"geo_location": "{\'lat\':100,\'long\':100}"
}',
'4-star',
100,
200,
400,
'images/hotel-logo.png',
'ROOM 200',
1,
'demo1@demo.com','9999999999','9999999999','demo2@demo.com','9999999999','9999999999','9999999999'
);

/*insert into hms_room_category(hotel_id,room_category_id,category_name,category_description,category_price,
							  bed_count,room_count,room_capacity,adult_occupancy,child_occupancy,is_category_active,date_created,date_updated,date_deleted)
							  values('HxGfKl1','RCgQc4x1','DEMO_ROOM_CATEGORY','DEMO',1000,100,100,5,2,2,1,current_timestamp(),null,null);

insert into hms_hotel_room(hotel_id,room_category_id,room_id,room_facilities,room_occupancy,room_status,date_created,date_updated,date_deleted)
                    values('HxGfKl1','RCgQc4x1','R100','DEMO_FACILITIES',5,1,current_timestamp(),null,null);

insert into hms_identification_params(hotel_id,identification_param_id,identification_param_type,identification_param_file,date_created,date_updated,date_deleted)
		                       values('HxGfKl1','IDg7vRs1',1,'DEMO_FILE',current_timestamp(),null,null);
							  
insert into hms_customer(hotel_id,corporate_id,identification_param_id,customer_id,customer_full_name,email,
						 mobile,home_address,customer_status,date_created,date_updated,date_deleted)
						values('HxGfKl1','COfjR2q1','IDg7vRs1','Ca2fe5R1','DEMO_CUSTOMER','demo@demo.com','9999999999',
							   '{
									"address_line1": "HMS Master Hotel",
									"address_line2": "Avenue Road",
									"city": "Bengaluru",
									"state": "Karnataka",
									"pin_code": "560032",
									"country": "India",
									"geo_location": "{\'lat\':100,\'long\':100}"
								}',1,current_timestamp(),null,null);*/
							  

