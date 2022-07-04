CREATE TABLE prices (
   id BIGINT NOT NULL,
   start_date TIMESTAMP,
   end_date TIMESTAMP,
   price_list INT,
   brand_id BIGINT,
   product_id BIGINT,
   priority INT,
   price Decimal(10,2),
   curr VARCHAR(255),
   CONSTRAINT pk_prices PRIMARY KEY (id)
);

ALTER TABLE prices ADD CONSTRAINT FK_PRICES_ON_ID FOREIGN KEY (brand_id) REFERENCES brands (id);