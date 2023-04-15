INSERT INTO teststations(id, version, station_name)
VALUES (nextval('teststations_seq'), 0, 'Schönbrunn');
INSERT INTO teststations(id, version, station_name)
VALUES (nextval('teststations_seq'), 0, 'Wollzeile');
INSERT INTO teststations(id, version, station_name)
VALUES (nextval('teststations_seq'), 0, 'Austria Center');

INSERT INTO addresses(id, version, street_number, zip_code, city, country)
VALUES (nextval('addresses_seq'), 0, 'Spengergasse 20', '1050', 'Wien', 'Österreich');
INSERT INTO persons(version, first_name, last_name, gender, birth_date, ssn, email, country_code, area_code,
                    serial_number, address_id, id)
VALUES (0, 'Klaus', 'UNGER', 'MALE', PARSEDATETIME('1969-09-02', 'yyyy-MM-dd'), 1234, 'unger@spengergasse.at', 43, 664,
        12345, nextval('persons_seq'), nextval('persons_seq'));
INSERT INTO addresses(id, version, street_number, zip_code, city, country)
VALUES (nextval('addresses_seq'), 0, 'Spengergasse 20a', '1051', 'Wien Margareten', 'Österreich');
INSERT INTO persons(version, first_name, last_name, gender, birth_date, ssn, email, country_code, area_code,
                    serial_number, address_id, id)
VALUES (0, 'Joachim', 'GRÜNEIS', 'MALE', PARSEDATETIME('1971-05-10', 'yyyy-MM-dd'), 5678, 'grueneis@spengergasse.at',
        +43, 664, 98765, currval('persons_seq'), nextval('persons_seq'));
INSERT INTO addresses(id, version, street_number, zip_code, city, country)
VALUES (nextval('addresses_seq'), 0, 'Spengergasse 20a', '1050', 'Wien', 'Österreich');
INSERT INTO persons(version, first_name, last_name, gender, birth_date, ssn, email, country_code, area_code,
                    serial_number, address_id, id)
VALUES (0, 'Susanne', 'DRS-POLLAK', 'FEMALE', PARSEDATETIME('1975-01-01', 'yyyy-MM-dd'), 2468,
        'grueneis@spengergasse.at', +43, 664, 23456, currval('persons_seq'), nextval('persons_seq'));

INSERT INTO tests(version, id, test_time_stamp, test_kit_type, test_result)
VALUES (0, nextval('tests_seq'), PARSEDATETIME('2021-05-01', 'yyyy-MM-dd'), 'PCR', 'Negative');
INSERT INTO tests(version, id, test_time_stamp, test_kit_type, test_result)
VALUES (0, nextval('tests_seq'), PARSEDATETIME('2021-05-03', 'yyyy-MM-dd'), 'PCR', 'Negative');
INSERT INTO tests(version, id, test_time_stamp, test_kit_type, test_result)
VALUES (0, nextval('tests_seq'), PARSEDATETIME('2021-05-05', 'yyyy-MM-dd'), 'PCR_FAST', 'Positive');
INSERT INTO tests(version, id, test_time_stamp, test_kit_type, test_result)
VALUES (0, nextval('tests_seq'), PARSEDATETIME('2021-05-05', 'yyyy-MM-dd'), 'PCR', 'Negative');
INSERT INTO tests(version, id, test_time_stamp, test_kit_type, test_result)
VALUES (0, nextval('tests_seq'), PARSEDATETIME('2021-05-01', 'yyyy-MM-dd'), 'AntiBody', 'Negative');
INSERT INTO tests(version, id, test_time_stamp, test_kit_type, test_result)
VALUES (0, nextval('tests_seq'), PARSEDATETIME('2021-05-03', 'yyyy-MM-dd'), 'PCR_FAST', 'Negative');
INSERT INTO tests(version, id, test_time_stamp, test_kit_type, test_result)
VALUES (0, nextval('tests_seq'), PARSEDATETIME('2021-05-05', 'yyyy-MM-dd'), 'PCR_FAST', 'Positive');
INSERT INTO tests(version, id, test_time_stamp, test_kit_type, test_result)
VALUES (0, nextval('tests_seq'), PARSEDATETIME('2021-05-05', 'yyyy-MM-dd'), 'PCR', 'Negative');