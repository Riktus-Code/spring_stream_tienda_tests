INSERT INTO fabricante VALUES(1, 'Asus');
INSERT INTO fabricante VALUES(2, 'Lenovo');
INSERT INTO fabricante VALUES(3, 'Hewlett-Packard');
INSERT INTO fabricante VALUES(4, 'Samsung');
INSERT INTO fabricante VALUES(5, 'Seagate');
INSERT INTO fabricante VALUES(6, 'Crucial');
INSERT INTO fabricante VALUES(7, 'Gigabyte');
INSERT INTO fabricante VALUES(8, 'Huawei');
INSERT INTO fabricante VALUES(9, 'Xiaomi');

INSERT INTO producto VALUES(1, 'Disco duro SATA3 1TB', 86.99, 5);
INSERT INTO producto VALUES(2, 'Memoria RAM DDR4 8GB', 120, 6);
INSERT INTO producto VALUES(3, 'Disco SSD 1 TB', 150.99, 4);
INSERT INTO producto VALUES(4, 'GeForce GTX 1050Ti', 185, 7);
INSERT INTO producto VALUES(5, 'GeForce GTX 1080 Xtreme', 755, 6);
INSERT INTO producto VALUES(6, 'Monitor 24 LED Full HD', 202, 1);
INSERT INTO producto VALUES(7, 'Monitor 27 LED Full HD', 245.99, 1);
INSERT INTO producto VALUES(8, 'Portátil Yoga 520', 559, 2);
INSERT INTO producto VALUES(9, 'Portátil Ideapd 320', 444, 2);
INSERT INTO producto VALUES(10, 'Impresora HP Deskjet 3720', 59.99, 3);
INSERT INTO producto VALUES(11, 'Impresora HP Laserjet Pro M26nw', 180, 3);

-- 1. Lista los nombres y los precios de todos los productos de la tabla producto
SELECT  nombre, precio FROM producto;
-- 2. Devuelve una lista de Producto completa con el precio de euros convertido a dólares .
SELECT nombre, ROUND(precio*1.17,2) FROM producto;
-- 3. Lista los nombres y los precios de todos los productos, convirtiendo los nombres a mayúscula.
SELECT UPPER(nombre), precio FROM producto;
-- 4. Lista el nombre de todos los fabricantes y a continuación en mayúsculas los dos primeros caracteres del nombre del fabricante.
    SELECT  nombre, UPPER(SUBSTR(nombre,1,2)) FROM fabricante;
-- 5. Lista el código de los fabricantes que tienen productos.
    SELECT distinct f.codigo FROM   fabricante f inner join producto p on f.codigo = p.codigo_fabricante;

-- 6. Lista los nombres de los fabricantes ordenados de forma descendente.
    select f.nombre from fabricante f order by f.nombre desc ;
-- 7. Lista los nombres de los productos ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
    select p.nombre, p.precio from producto p order by p.nombre asc , p.precio desc;
-- 8. Devuelve una lista con los 5 primeros fabricantes.
    select f.nombre from fabricante f limit 5;
-- 9. Devuelve una lista con 2 fabricantes a partir del cuarto fabricante. El cuarto fabricante también se debe incluir en la respuesta.
    select f.nombre from fabricante f limit 2 offset 3;
-- 10. Lista el nombre y el precio del producto más barato
    select p.nombre from producto p order by p.precio asc limit 1;
-- 11. Lista el nombre y el precio del producto más caro
    select p.nombre, p.precio from producto p order by p.precio desc limit 1;

-- 12. Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.
    select f.nombre from fabricante f where f.codigo = 2;
-- 13. Lista el nombre de los productos que tienen un precio menor o igual a 120€.
    select p.nombre from producto p where p.precio<= 120;
-- 14. Lista los productos que tienen un precio mayor o igual a 400€.
    select * from producto p where p.precio>=400;

-- 15. Lista todos los productos que tengan un precio entre 80€ y 300€.
    select * from producto p where p.precio>=80 && p.precio<=300;

-- 16. Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.
    select * from producto p where p.precio>200 and p.codigo_fabricante=6;
-- 17. Lista todos los productos donde el código de fabricante sea 1, 3 o 5 utilizando un Set de codigos de fabricantes para filtrar.
    select * from producto p where p.codigo_fabricante in (1,3,5);
-- 18. Lista el nombre y el precio de los productos en céntimos.
    select p.nombre, (p.precio*100) from producto p;
-- 19. Lista los nombres de los fabricantes cuyo nombre empiece por la letra S
    select f.nombre from fabricante f where f.nombre like 'S%';
-- 20. Devuelve una lista con los productos que contienen la cadena Portátil en el nombre.
    select * from producto p where p.nombre like '%Portátil%';
-- 21. Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.
    select p.nombre from producto p where p.nombre like '%Monitor%' and p.precio <215;
-- 22. Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€. Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente).
    select p.nombre from producto p where p.precio >=180 order by p.precio desc, p.nombre asc;
-- 23. Devuelve una lista con el nombre del producto, precio y nombre de fabricante de todos los productos de la base de datos. Ordene el resultado por el nombre del fabricante, por orden alfabético.
    select p.nombre, p.precio, f.nombre from producto p inner join fabricante f on f.codigo = p.codigo_fabricante;
-- 24. Devuelve el nombre del producto, su precio y el nombre de su fabricante, del producto más caro.
    select p.nombre, p.precio, f.nombre from producto p inner join fabricante f on f.codigo = p.codigo_fabricante order by p.precio desc limit 1;
-- 25. Devuelve una lista de todos los productos del fabricante Crucial que tengan un precio mayor que 200€.
    select p.nombre, p.precio from producto p inner join fabricante f on p.codigo_fabricante=f.codigo where f.nombre='Crucial' and p.precio>200;
-- 26. Devuelve un listado con todos los productos de los fabricantes Asus, Hewlett-Packard y Seagate.
    select p.nombre, p.precio from producto p inner join fabricante f on p.codigo_fabricante=f.codigo where f.nombre in ('Asus',' Hewlett-Packard','Seagate');
-- 27. Devuelve un listado con el nombre de producto, precio y nombre de fabricante, de todos los productos que tengan un precio mayor o igual a 180€. Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre.
    select p.nombre, p.precio, f.nombre from producto p inner join fabricante f on f.codigo = p.codigo_fabricante where p.precio >=180 order by p.precio desc, p.nombre asc;
-- 28. Devuelve un listado de los nombres fabricantes que existen en la base de datos, junto con los nombres productos que tiene cada uno de ellos. El listado deberá mostrar también aquellos fabricantes que no tienen productos asociados.
    select f.nombre, p.nombre from fabricante f inner join producto p on f.codigo = p.codigo_fabricante;
-- 29. Devuelve un listado donde sólo aparezcan aquellos fabricantes que no tienen ningún producto asociado.
    select f.nombre, p.nombre from fabricante f inner join producto p on f.codigo = p.codigo_fabricante where p.codigo_fabricante is null;

-- 30. Calcula el número total de productos que hay en la tabla productos. Utiliza la api de stream.
select count(p.codigo) from producto;
-- 31. Calcula el número de fabricantes con productos, utilizando un stream de Productos.
SELECT count(p.codigo_fabricante) as fabricante_con_producto from producto p ;
-- 32. Calcula la media del precio de todos los productos
    select avg(p.precio) as media_precio from producto p;
-- 33. Calcula el precio más barato de todos los productos. No se puede utilizar ordenación de stream.
Select min(p.precio) as precio_minimo from producto p;
-- 34. Calcula la suma de los precios de todos los productos.
    SELECT sum(p.precio) as suma_total from producto p;
-- 35. Calcula el número de productos que tiene el fabricante Asus.
    SELECT count(*) as productos_Asus from producto p  inner join fabricante f on p.codigo_fabricante = f.codigo where f.nombre = 'Asus';
-- 36. Calcula la media del precio de todos los productos del fabricante Asus.

-- 37. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos que tiene el fabricante Crucial. Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
SELECT MAX(p.precio) AS precio_maximo, MIN(p.precio) AS precio_minimo, AVG(p.precio) AS precio_medio, COUNT(*) AS total_productos FROM producto p
JOIN fabricante f ON p.codigo_fabricante = f.codigo
WHERE f.nombre = 'Crucial';
-- 38. Muestra el número total de productos que tiene cada uno de los fabricantes. El listado también debe incluir los fabricantes que no tienen ningún producto. El resultado mostrará dos columnas, una con el nombre del fabricante y otra con el número de productos que tiene. Ordene el resultado descendentemente por el número de productos.
SELECT f.nombre, COUNT(p.codigo) AS total_productos FROM fabricante f
LEFT JOIN producto p ON f.codigo = p.codigo_fabricante
GROUP BY f.nombre
ORDER BY total_productos DESC;
-- 39. Muestra el precio máximo, precio mínimo y precio medio de los productos de cada uno de los fabricantes. El resultado mostrará el nombre del fabricante junto con los datos que se solicitan. Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
SELECT f.nombre, MAX(p.precio) AS precio_maximo, MIN(p.precio) AS precio_minimo, AVG(p.precio) AS precio_medio FROM fabricante f
LEFT JOIN producto p ON f.codigo = p.codigo_fabricante
GROUP BY f.nombre;
-- 40. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos de los fabricantes que tienen un precio medio superior a 200€. No es necesario mostrar el nombre del fabricante, con el código del fabricante es suficiente.
SELECT f.codigo, MAX(p.precio) AS precio_maximo, MIN(p.precio) AS precio_minimo, AVG(p.precio) AS precio_medio, COUNT(*) AS total_productos FROM producto p
JOIN fabricante f ON p.codigo_fabricante = f.codigo
GROUP BY f.codigo
HAVING precio_medio > 200;
-- 41. Devuelve un listado con los nombres de los fabricantes que tienen 2 o más productos.
SELECT f.nombre FROM fabricante f JOIN producto p ON f.codigo = p.codigo_fabricante
GROUP BY f.nombre
HAVING COUNT(*) >= 2;
-- 42. Devuelve un listado con los nombres de los fabricantes y el número de productos que tiene cada uno con un precio superior o igual a 220 €. Ordenado de mayor a menor número de productos.
SELECT f.nombre, COUNT(p.codigo) AS total_productos FROM fabricante f
LEFT JOIN producto p ON f.codigo = p.codigo_fabricante AND p.precio >= 220
GROUP BY f.nombre
ORDER BY total_productos DESC;
-- 43. Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
SELECT f.nombre
FROM fabricante f
JOIN producto p ON f.codigo = p.codigo_fabricante
GROUP BY f.nombre
HAVING SUM(p.precio) > 1000;
-- 44. Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €. Ordenado de menor a mayor por cuantía de precio de los productos.
SELECT f.nombre, SUM(p.precio) AS suma_precios FROM fabricante f
JOIN producto p ON f.codigo = p.codigo_fabricante
GROUP BY f.nombre
HAVING suma_precios > 1000
ORDER BY suma_precios ASC;
-- 45. Devuelve un listado con el nombre del producto más caro que tiene cada fabricante. El resultado debe tener tres columnas: nombre del producto, precio y nombre del fabricante. El resultado tiene que estar ordenado alfabéticamente de menor a mayor por el nombre del fabricante.
SELECT p.nombre AS nombre_producto, p.precio, f.nombre AS nombre_fabricante FROM producto p
JOIN fabricante f ON p.codigo_fabricante = f.codigo
WHERE p.precio = (
    SELECT MAX(precio)
    FROM producto
    WHERE codigo_fabricante = f.codigo
)
ORDER BY f.nombre ASC;
-- 46. Devuelve un listado de todos los productos que tienen un precio mayor o igual a la media de todos los productos de su mismo fabricante. Se ordenará por fabricante en orden alfabético ascendente y los productos de cada fabricante tendrán que estar ordenados por precio descendente.
SELECT p.nombre, p.precio, f.nombre AS nombre_fabricante
FROM producto p
JOIN fabricante f ON p.codigo_fabricante = f.codigo
WHERE p.precio >= (
    SELECT AVG(precio)
    FROM producto
    WHERE codigo_fabricante = p.codigo_fabricante
)
ORDER BY f.nombre ASC, p.precio DESC;

