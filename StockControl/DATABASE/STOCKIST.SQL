CREATE TABLE stockist (
        id_stockist serial PRIMARY KEY,
        descr CHARACTER VARYING(255),
        date_end_validity date,
        UNIQUE (descr)
);

CREATE TABLE customer (
        id_customer serial PRIMARY KEY,
        descr CHARACTER VARYING(255),
        date_end_validity date,
        UNIQUE (descr)
);


CREATE TABLE brand (
        id_brand serial PRIMARY KEY,
        descr CHARACTER VARYING(255),
        date_end_validity date,
        UNIQUE (descr)
);
CREATE TABLE category (
        id_category serial PRIMARY KEY,
        descr CHARACTER VARYING(255),
        date_end_validity date,
        UNIQUE (descr)
);
CREATE TABLE product (
        id_product serial PRIMARY KEY,
        descr CHARACTER VARYING(255),
        date_end_validity date,
        UNIQUE (descr)
);
CREATE TABLE range (
        id_range serial PRIMARY KEY,
        descr CHARACTER VARYING(255),
        date_end_validity date,
        UNIQUE (descr)
);

CREATE TABLE link_stockist_brand (
        id_stockist INTEGER REFERENCES stockist (id_stockist),
        id_brand INTEGER REFERENCES brand (id_brand),
        date_end_validity date,
        UNIQUE (id_stockist,id_brand)
);

CREATE TABLE link_brand_category (
        id_brand INTEGER REFERENCES brand (id_brand),
        id_category INTEGER REFERENCES category (id_category),      
        date_end_validity date,
        UNIQUE (id_category,id_brand)
);
CREATE TABLE link_category_product (
        id_category INTEGER REFERENCES category (id_category),    
        id_product INTEGER REFERENCES product (id_product),  
        date_end_validity date,
        UNIQUE (id_category,id_product)
);
CREATE TABLE link_product_range (
        id_range INTEGER REFERENCES range (id_range),    
        id_product INTEGER REFERENCES product (id_product),  
        date_end_validity date,
        UNIQUE (id_range,id_product)
);

							
CREATE TABLE customer_order (
        id_customer_order INTEGER PRIMARY KEY,
        descr CHARACTER VARYING(255),
        date_end_validity date,
        UNIQUE (descr)
);
CREATE TABLE stockist_order (
        id_stockist_order INTEGER PRIMARY KEY,
        descr CHARACTER VARYING(255),
        date_end_validity date,
        UNIQUE (descr)
);
CREATE TABLE link_order (
        id_customer_order INTEGER REFERENCES customer_order (id_customer_order),    
        id_stockist_order INTEGER REFERENCES stockist_order (id_stockist_order),  
        date_end_validity date,
        UNIQUE (id_customer_order,id_stockist_order)
);
							


						
