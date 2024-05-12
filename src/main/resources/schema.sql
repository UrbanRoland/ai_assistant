CREATE EXTENSION IF NOT EXISTS "vector";
CREATE EXTENSION IF NOT EXISTS "hstore";
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS "vectore-store" (
    "id" UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    "context" TEXT,
    "metadata" JSON,
    "embedding" VECTOR(1536)
);
CREATE INDEX ON "vectore-store" USING HNSW(embedding vector_cosine_ops);