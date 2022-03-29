-- Initialisation des tables
INSERT INTO Country(code, name) VALUES
    ('FR', 'France'), -- Les clés sont auto-générées
    ('UK', 'United Kingdom'),
    ('US', 'United States of America');

INSERT INTO CITY(name, population, country_id) VALUES
    ('Paris', 12, 1),
    ('London', 18, 2),
    ('New York', 27, 3);