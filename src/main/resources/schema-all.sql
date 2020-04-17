DROP TABLE IF EXISTS items;

CREATE TABLE items (
  id serial NOT NULL PRIMARY KEY 
  , name text NOT NULL
  , description text NOT NULL
  , price_m integer NOT NULL
  , price_l integer NOT NULL
  , image_path text NOT NULL
  , deleted boolean DEFAULT false NOT NULL
) ;