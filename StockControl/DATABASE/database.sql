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
        id_stockist INTEGER REFERENCES stockist (id_stockist),
        descr CHARACTER VARYING(255),
        date_end_validity date,
        UNIQUE (descr)
);

CREATE TABLE category (
        id_category serial PRIMARY KEY,
        id_brand INTEGER REFERENCES brand (id_brand),
        descr CHARACTER VARYING(255),
        date_end_validity date,
        UNIQUE (id_brand, descr)
);

CREATE TABLE product (
        id_product serial PRIMARY KEY,
        id_category INTEGER REFERENCES category (id_category), 
		cod_product CHARACTER VARYING(255),
        descr CHARACTER VARYING(255),
		range CHARACTER VARYING(255),
		selling_price numeric,
        date_end_validity date,
        UNIQUE (cod_product)
);

CREATE TABLE customer_order (
        id_customer_order serial PRIMARY KEY,
		id_customer INTEGER REFERENCES customer (id_customer),
		date_order date,
		price_order numeric,
		real_order_price numeric,
		discount_percentage numeric,
        note CHARACTER VARYING(255),
        date_end_validity date
);

CREATE TABLE stockist_order (
        id_stockist_order serial PRIMARY KEY,
		date_order date,
		price_order numeric,
		shipping_charges numeric,
		other_shopping	numeric,	 
        note CHARACTER VARYING(255),
        date_end_validity date
);

CREATE TABLE stockist_order_product (
		id_stockist_order_product serial PRIMARY KEY,
        id_stockist_order INTEGER,
		id_stockist INTEGER REFERENCES stockist (id_stockist), 
		id_brand INTEGER REFERENCES brand (id_brand),
		id_category INTEGER REFERENCES category (id_category),
		id_product INTEGER REFERENCES product (id_product), 
		price numeric,
        descr CHARACTER VARYING(255),
        date_end_validity date
);


CREATE TABLE link_order (
		id_link_order serial PRIMARY KEY,
        id_customer_order INTEGER REFERENCES customer_order (id_customer_order),    
        id_stockist_order_product INTEGER REFERENCES stockist_order_product (id_stockist_order_product),  
		real_selling_price numeric,
        date_end_validity date,
        UNIQUE (id_customer_order,id_stockist_order_product)
);
							


						
