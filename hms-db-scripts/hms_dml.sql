insert into hms_hotel(
hotel_name,
hotel_address,
hotel_rating,
hotel_staff_count,
hotel_room_count,
hotel_bed_count,
hotel_logo,
hotel_room_doorno_format,
hotel_status,
hotel_contact_details
)values(
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
'{
                "fax_number": "9999999999",
                "mobile_number": "9999999999",
                "primary_email": "demo@demo.com",
                "primary_phone": "9999999999",
                "secondary_email": "demo@demo.com",
                "secondary_phone": "9999999999"
}'
);

