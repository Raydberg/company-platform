# Desarrollo Local
## Levantar contenedores
```bash
docker-compose up --build
```
## Detener contenedores
```bash
docker-compose down
```


# Enviar a Produccion

## Preparar el JAR
```bash
docker-compose -f docker-compose.prod.yml up --build
```
## Detener el servicio
```bash
docker-compose -f docker-compose.prod.yml up --build
```