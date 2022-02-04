insert
into
    address_book (created_date,
                  last_modified_date,
                  version,
                  name,
                  user_id,
                  id)
values ('2022-02-04 08:17:21.492',
        '2022-02-04 08:17:21.492',
        0,
        'Coles',
        'u0001',
        '12e96e96-3a1b-44f7-957e-ad9c6c061018');

insert
into
    address_book (created_date,
                  last_modified_date,
                  version,
                  name,
                  user_id,
                  id)
values ('2022-02-04 08:17:21.492',
        '2022-02-04 08:17:21.492',
        0,
        'Bunnings',
        'u0001',
        'd9558f1e-9649-4ea5-80bc-b1596ba265a6');

insert
into
    contact (created_date,
             last_modified_date,
             version,
             address_book_id,
             first_name,
             last_name,
             phone_number,
             id)
values ('2022-02-04 08:17:21.492',
        '2022-02-04 08:17:21.492',
        0,
        '12e96e96-3a1b-44f7-957e-ad9c6c061018',
        'Will',
        'Fry',
        '0400123123',
        'ba346384-cba5-4538-a901-8c5401f7de35');

insert
into
    contact (created_date,
             last_modified_date,
             version,
             address_book_id,
             first_name,
             last_name,
             phone_number,
             id)
values ('2022-02-04 08:17:21.492',
        '2022-02-04 08:17:21.492',
        0,
        '12e96e96-3a1b-44f7-957e-ad9c6c061018',
        'Jane',
        'Fry',
        '0400123124',
        '917b21df-ff88-4105-aaf5-bb23c3320073');

insert
into
    contact (created_date,
             last_modified_date,
             version,
             address_book_id,
             first_name,
             last_name,
             phone_number,
             id)
values ('2022-02-04 08:17:21.492',
        '2022-02-04 08:17:21.492',
        0,
        'd9558f1e-9649-4ea5-80bc-b1596ba265a6',
        'Jane',
        'Fry',
        '0400123124',
        'f94a1339-eab3-451f-b8e6-a25e7be87353');

insert
into
    contact (created_date,
             last_modified_date,
             version,
             address_book_id,
             first_name,
             last_name,
             phone_number,
             id)
values ('2022-02-04 08:17:21.492',
        '2022-02-04 08:17:21.492',
        0,
        'd9558f1e-9649-4ea5-80bc-b1596ba265a6',
        'Jane',
        'White',
        '0400123125',
        '7409d773-69d3-42f0-93dc-1296234b1a7c');
