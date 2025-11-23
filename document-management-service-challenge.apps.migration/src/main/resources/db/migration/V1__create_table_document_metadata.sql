CREATE TABLE document_metadata (
   id SERIAL PRIMARY KEY,
   "user" VARCHAR(150) NOT NULL,
   name VARCHAR(255) NOT NULL,
   tags TEXT[] NOT NULL,
   minio_path VARCHAR(500) NOT NULL,
   file_size int NOT NULL default 0,
   file_type VARCHAR(100) NOT NULL,
   created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
