#!/bin/sh

# Aguarda o MinIO estar pronto
sleep 10

# Configura o alias local
mc alias set myminio http://localhost:9000 admin admin123

# Cria o bucket "fotos" se não existir
mc mb -p myminio/fotos || echo "Bucket já existe"
