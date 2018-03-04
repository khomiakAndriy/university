DELETE FROM lectors;
DELETE FROM degrees;
DELETE FROM departments_lectors;
DELETE FROM departments;

insert into degrees (name) values ('Assistant'), ('Associate professor'), ('Professor');

insert into lectors (name, salary, degree_id) values 
('John Dou', 1500.00, 2), 
('Alan Smith', 1250.00, 1),
('Pattie Cyril', 1450.00, 3),
('Rhonda Barbie', 1300.00, 1),
('Meriwether Shaylyn', 1320.00, 2),
('Randy Izzy', 1270.00, 1),
('London Briar', 1470.00, 2),
('Phoenix Riley', 1510.00, 2),
('Courtney Wallis', 1180.00, 1),
('Cherokee Tristin', 1680.00, 3)
;

insert into departments (name, chief) values 
('Economшcal department', 'Lesley Laurie'),
('Law department', 'Dallas Montana'),
('Medical department', 'Lesley Laurie');

INSERT INTO departments_lectors (lectors_id, departments_id) VALUES 
  (1, 1),
  (1, 2),
  (2, 1),
  (2, 2),
  (3, 2),
  (4, 1),
  (4, 3),
  (5, 3),
  (6, 1),
  (6, 2),
  (7, 3),
  (8, 1),
  (8, 2),
  (9, 1),
  (9, 3),
  (10, 1),
  (10, 2);
  