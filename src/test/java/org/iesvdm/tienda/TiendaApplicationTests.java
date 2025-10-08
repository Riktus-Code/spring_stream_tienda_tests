package org.iesvdm.tienda;

import org.iesvdm.tienda.modelo.Fabricante;
import org.iesvdm.tienda.modelo.Producto;
import org.iesvdm.tienda.repository.FabricanteRepository;
import org.iesvdm.tienda.repository.ProductoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.*;
import java.util.ArrayList;
import static java.util.Comparator.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@SpringBootTest
class TiendaApplicationTests {

	@Autowired
	FabricanteRepository fabRepo;

	@Autowired
	ProductoRepository prodRepo;

	@Test
	void testAllFabricante() {
		var listFabs = fabRepo.findAll();

		listFabs.forEach(f -> {
			System.out.println(">>"+f+ ":");
			f.getProductos().forEach(System.out::println);
		});
	}

	@Test
	void testAllProducto() {
		var listProds = prodRepo.findAll();

		listProds.forEach( p -> {
			System.out.println(">>"+p+":"+"\nProductos mismo fabricante "+ p.getFabricante());
			p.getFabricante().getProductos().forEach(pF -> System.out.println(">>>>"+pF));
		});


	}


	/**
	 * 1. Lista los nombres y los precios de todos los productos de la tabla producto
	 */
	@Test
	void test1() {
		var listProds = prodRepo.findAll();
		//TODO

		var listNomPre = listProds.stream().map((s) -> "Nombre: "+s.getNombre()+" Precio: "+s.getPrecio()).toList();

		listNomPre.forEach(s -> System.out.println(s));

        Assertions.assertEquals(11, listNomPre.size());
        Assertions.assertTrue(listNomPre.contains("Nombre: Disco duro SATA3 1TB Precio: 86.99"));

		Assertions.assertEquals(11, listNomPre.size());
		Assertions.assertTrue(listNomPre.contains("Nombre: Disco duro SATA3 1TB Precio: 86.99"));
	}


	/**
	 * 2. Devuelve una lista de Producto completa con el precio de euros convertido a dólares .
	 */
	@Test
	void test2() {
		var listProds = prodRepo.findAll();
		//TODO

        var listNomPre = listProds.stream().map(s -> s.getNombre() + " con precio "
		+ BigDecimal.valueOf(s.getPrecio()*1.17)
																		.setScale(2,RoundingMode.HALF_UP)+"$"
		)
		.toList();

        //lo podemos simplificar más aun usando esto.
        listNomPre.forEach(System.out::println);
        Assertions.assertEquals(11, listNomPre.size());
        Assertions.assertTrue(listNomPre.contains("Impresora HP Laserjet Pro M26nw con precio 210.60$"));


	}

	/**
	 * 3. Lista los nombres y los precios de todos los productos, convirtiendo los nombres a mayúscula.
	 */
	@Test
	void test3() {
		var listProds = prodRepo.findAll();
		//TODO

        var listNomPre = listProds.stream().map((s) -> "Nombre: "+(s.getNombre().toUpperCase())+" Precio: "+(s.getPrecio())).toList();
        //lo podemos simplificar más aun usando esto.
        listNomPre.forEach(System.out::println);

        Assertions.assertEquals(11, listNomPre.size());
        Assertions.assertTrue(listNomPre.contains("Nombre: DISCO DURO SATA3 1TB Precio: 86.99"));
	}

	/**
	 * 4. Lista el nombre de todos los fabricantes y a continuación en mayúsculas los dos primeros caracteres del nombre del fabricante.
	 */
	@Test
	void test4() {
		var listFabs = fabRepo.findAll();
		//TODO

        var listaFaNom= listFabs.stream().map((s)-> { String nombre = s.getNombre();
            String iniciales = nombre.substring(0, 2).toUpperCase();
            return "Nombre: "+nombre+" Iniciales: "+iniciales;

        }).toList();
        listaFaNom.forEach(System.out::println);

        Assertions.assertEquals(9,listaFaNom.size());
        Assertions.assertTrue(listaFaNom.contains("Nombre: Asus Iniciales: AS"));

    }

	/**
	 * 5. Lista el código de los fabricantes que tienen productos.
	 */
	@Test
	void test5() {
		var listFabs = fabRepo.findAll();
        var listaNumFab = listFabs.stream().filter(f -> !f.getProductos().isEmpty())
                .map(s->s.getCodigo())
                .toList();

		listaNumFab.forEach(System.out::println);

		//TODO
        Assertions.assertEquals(7,listaNumFab.size());
        Assertions.assertTrue(listaNumFab.contains(7));

	}

	/**
	 * 6. Lista los nombres de los fabricantes ordenados de forma descendente.
	 */
	@Test
	void test6() {
		var listFabs = fabRepo.findAll();

        var listaNombres = listFabs.stream().
                sorted(
                        comparing((Fabricante f) ->  f.getNombre(), reverseOrder()))
                .map(f->f.getNombre()).
                toList();

        listaNombres.forEach(System.out::println);
		//TODO
        Assertions.assertEquals(9,listaNombres.size());
        Assertions.assertTrue(listaNombres.contains("Seagate"));
	}

	/**
	 * 7. Lista los nombres de los productos ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
	 */
	@Test
	void test7() {
		var listProds = prodRepo.findAll();
        var listaNombres =listProds.stream().
                sorted(comparing((Producto p) -> p.getNombre())
                        .thenComparing((Producto p) ->p.getPrecio(),reverseOrder()))
                .map(p->p.getNombre() +" "+p.getPrecio()).
                toList();



        listaNombres.forEach(s->System.out.println(s));



        Assertions.assertEquals(11, listaNombres.size());
        Assertions.assertTrue(listaNombres.contains("Portátil Yoga 520 559.0"));


        //TODO
	}

	/**
	 * 8. Devuelve una lista con los 5 primeros fabricantes.
	 */
	@Test
	void test8() {
		var listFabs = fabRepo.findAll();
        var lista5Fabs = listFabs.stream()
                .limit(5)
                .map(s->s.getNombre())
                .toList();

        lista5Fabs.forEach(s-> System.out.println(s));

        Assertions.assertEquals(5,lista5Fabs.size());
        Assertions.assertTrue(lista5Fabs.contains("Asus"));
		//TODO
	}

	/**
	 * 9.Devuelve una lista con 2 fabricantes a partir del cuarto fabricante. El cuarto fabricante también se debe incluir en la respuesta.
	 */
	@Test
	void test9() {
		var listFabs = fabRepo.findAll();
        var lista4Fab = listFabs.stream()
                .skip(3)
                .limit(2)
                .map(s->s.getNombre())
                .toList();

        lista4Fab.forEach(s-> System.out.println(s));
        Assertions.assertEquals(2,lista4Fab.size());
        Assertions.assertTrue(lista4Fab.contains("Samsung"));


        //TODO
	}

	/**
	 * 10. Lista el nombre y el precio del producto más barato
	 */
	@Test
	void test10() {
		var listProds = prodRepo.findAll();
        var listaProBar = listProds.stream()
                .sorted(comparing((Producto producto )-> producto.getPrecio()))
                .limit(1)
                .map(p->p.getNombre()+ " "+p.getPrecio())
                .toList();

        listaProBar.forEach(s-> System.out.println(s));

        Assertions.assertEquals(1,listaProBar.size());
        Assertions.assertTrue(listaProBar.contains("Impresora HP Deskjet 3720 59.99"));
		//TODO
	}

	/**
	 * 11. Lista el nombre y el precio del producto más caro
	 */
	@Test
	void test11() {
		var listProds = prodRepo.findAll();

        var listaNomCar = listProds.stream()
                .sorted(comparing((Producto p)->p.getPrecio(),reverseOrder()))
                .limit(1)
                .map(p->p.getNombre()+" "+p.getPrecio())
                .toList();
        listaNomCar.forEach(System.out::println);
        Assertions.assertEquals(1,listaNomCar.size());
        Assertions.assertTrue(listaNomCar.contains("GeForce GTX 1080 Xtreme 755.0"));
        //Otro tipo de hacerlo
        /*
        * Optional <Producto> prodOut = listProds.stream()
        * .sorted(
        *        comparing(p-> p.getPrecio(), reverseOrder())
        * )findFirst();
        * al hacer el findFirst tenemos que meter lo que nos devuelva en un Optional
        * luego hacemos al condicion isPresent para saber si hay algo e imprimirlo
        * if(prodOut.isPresent()){
        * Producto prod = prodOut.get();
        * System.out.println(prod.getNombre()+ " " +prod.getPrecio());
        * }
        * */

		//TODO
	}

	/**
	 * 12. Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.
	 *
	 */
	@Test
	void test12() {
		var listProds = prodRepo.findAll();
        var listaPro = listProds.stream()
                .filter(f->f.getFabricante().getCodigo()==2)
                .map(p-> p.getNombre())
                .toList();

        listaPro.forEach(System.out::println);

        Assertions.assertEquals(2,listaPro.size());
        Assertions.assertTrue(listaPro.contains("Portátil Yoga 520"));

		//TODO
	}

	/**
	 * 13. Lista el nombre de los productos que tienen un precio menor o igual a 120€.
	 */
	@Test
	void test13() {
		var listProds = prodRepo.findAll();
        var listaFiltrada = listProds.stream()
                .filter(p->p.getPrecio()<=120.0)
                .map(p->p.getNombre())
                .toList();
        listaFiltrada.forEach(p-> System.out.println(p));
        Assertions.assertEquals(3,listaFiltrada.size());
        Assertions.assertTrue(listaFiltrada.contains("Disco duro SATA3 1TB"));
		//TODO
	}

	/**
	 * 14. Lista los productos que tienen un precio mayor o igual a 400€.
	 */
	@Test
	void test14() {
		var listProds = prodRepo.findAll();
		//TODO
        var listaFiltrada2 = listProds.stream()
                .filter(p->p.getPrecio()>=400.0)
                .map(p->p.getNombre())
                .toList();
        listaFiltrada2.forEach(p-> System.out.println(p));
        Assertions.assertEquals(3,listaFiltrada2.size());
        Assertions.assertTrue(listaFiltrada2.contains("Portátil Yoga 520"));
	}

	/**
	 * 15. Lista todos los productos que tengan un precio entre 80€ y 300€.
	 */
	@Test
	void test15() {
		var listProds = prodRepo.findAll();
        var listaFiltrada3 = listProds.stream()
                .filter(p->p.getPrecio()>=80.0 && p.getPrecio()<=300.0)
                .map(p->p.getNombre())
                .toList();
        listaFiltrada3.forEach(System.out::println);
        Assertions.assertEquals(7,listaFiltrada3);
        Assertions.assertTrue(listaFiltrada3.contains("Disco duro SATA3 1TB"));
		//TODO
	}

	/**
	 * 16. Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.
	 */
	@Test
	void test16() {
		var listProds = prodRepo.findAll();
        var listaFiltrada4 = listProds.stream()
                .filter(p->p.getPrecio()>=200.0 && p.getFabricante().getCodigo()==6)
                .map(p->p.getNombre())
                .toList();
        listaFiltrada4.forEach(p-> System.out.println(p));
        Assertions.assertEquals(1,listaFiltrada4);
        Assertions.assertTrue(listaFiltrada4.contains("GeForce GTX 1080 Xtreme"));
		//TODO
	}

	/**
	 * 17. Lista todos los productos donde el código de fabricante sea 1, 3 o 5 utilizando un Set de codigos de fabricantes para filtrar.
	 */
	@Test
	void test17() {
		var listProds = prodRepo.findAll();
		final Set<Integer> setFab = new HashSet<>();
        setFab.add(1);
        setFab.add(3);
        setFab.add(5);
        var listaFiltradaSet = listProds.stream()
                .filter(p->setFab.contains(p.getFabricante().getCodigo())).toList();

        listaFiltradaSet.forEach(s-> System.out.println(s));

	}

	/**
	 * 18. Lista el nombre y el precio de los productos en céntimos.
	 */
	@Test
	void test18() {
		var listProds = prodRepo.findAll();
        var listaCents = listProds.stream()
                .map(p->p.getNombre()+" "+(p.getPrecio()*100)+" Centimos")
                .toList();
        listaCents.forEach(s-> System.out.println(s));

		//TODO
	}


	/**
	 * 19. Lista los nombres de los fabricantes cuyo nombre empiece por la letra S
	 */
	@Test
	void test19() {
		var listFabs = fabRepo.findAll();
        var listaNomb = listFabs.stream()
                .filter(f -> f.getNombre().charAt(0)=='S')
                .map(f-> f.getNombre())
                .toList();

            listaNomb.forEach(System.out::println);
		//TODOS
	}

	/**
	 * 20. Devuelve una lista con los productos que contienen la cadena Portátil en el nombre.
	 */
	@Test
	void test20() {
		var listProds = prodRepo.findAll();
        var listaNomPr = listProds.stream()
                .filter(p->p.getNombre().contains("Portátil"))
                .map(p->p.getNombre())
                .toList();

        listaNomPr.forEach(System.out::println);
        Assertions.assertEquals(2,listaNomPr.size());
        Assertions.assertTrue(listaNomPr.contains("Portátil Yoga 520"));
		//TODO
	}

	/**
	 * 21. Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.
	 */
	@Test
	void test21() {
		var listProds = prodRepo.findAll();
        var listaProdNomPre = listProds.stream()
                .filter(p->p.getNombre().contains("Monitor") && p.getPrecio()<215)
                .map(p->p.getNombre())
                .toList();

        listaProdNomPre.forEach(System.out::println);
        Assertions.assertEquals(1,listaProdNomPre.size());
        Assertions.assertTrue(listaProdNomPre.contains("Monitor 24 LED Full HD"));
	}

	/**
	 * 22. Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€.
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente).
	 */
    @Test
	void test22() {
		var listProds = prodRepo.findAll();

        var listaFiltrada = listProds.stream().filter(p->p.getPrecio()>=180)
                .sorted(comparing((Producto p) ->p.getPrecio(),reverseOrder() )
                        .thenComparing((Producto p)-> p.getNombre()))
                .map(p-> p.getNombre()+" "+p.getPrecio())
                .toList();

        listaFiltrada.forEach(System.out::println);
        Assertions.assertEquals(7,listaFiltrada.size());
        Assertions.assertTrue(listaFiltrada.contains("GeForce GTX 1080 Xtreme 755.0"));
	}

	/**
	 * 23. Devuelve una lista con el nombre del producto, precio y nombre de fabricante de todos los productos de la base de datos.
	 * Ordene el resultado por el nombre del fabricante, por orden alfabético.
	 */
	@Test
	void test23() {
		var listProds = prodRepo.findAll();
        var listaAlf = listProds.stream()
                .sorted(comparing((Producto f)-> f.getFabricante().getNombre()))
                .map((p)-> p.getFabricante().getNombre()+" "+p.getNombre()+" "+p.getPrecio())
                .toList();

        listaAlf.forEach(System.out::println);
        Assertions.assertEquals(11,listaAlf.size());
        Assertions.assertTrue(listaAlf.contains("Crucial GeForce GTX 1080 Xtreme 755.0"));
		//TODO
	}

	/**
	 * 24. Devuelve el nombre del producto, su precio y el nombre de su fabricante, del producto más caro.
	 */
	@Test
	void test24() {
		var listProds = prodRepo.findAll();
        var proCaro = listProds.stream()
                .sorted(comparing((Producto p)->p.getPrecio(),reverseOrder()))
                .limit(1)
                .map(p-> p.getNombre()+" "+p.getPrecio()+" "+p.getFabricante().getNombre())
                .toList();
        proCaro.forEach(System.out::println);

        Assertions.assertEquals(1,proCaro.size());
        Assertions.assertTrue(proCaro.contains("GeForce GTX 1080 Xtreme 755.0 Crucial"));

		//TODO
	}

	/**
	 * 25. Devuelve una lista de todos los productos del fabricante Crucial que tengan un precio mayor que 200€.
	 */
	@Test
	void test25() {
		var listProds = prodRepo.findAll();
		var listaCrucial = listProds.stream()
                .filter(p->p.getFabricante().getNombre().equalsIgnoreCase("Crucial")
                && p.getPrecio()>200)
                .map(p->p.getNombre())
                .toList();
        listaCrucial.forEach(s-> System.out.println(s));
        Assertions.assertEquals(1,listaCrucial.size());
        Assertions.assertTrue(listaCrucial.contains("GeForce GTX 1080 Xtreme"));

	}

	/**
	 * 26. Devuelve un listado con todos los productos de los fabricantes Asus, Hewlett-Packard y Seagate
	 */
	@Test
	void test26() {
		var listProds = prodRepo.findAll();
        var listaEspedifica = listProds.stream()
                .filter(p->p.getFabricante().getNombre().equalsIgnoreCase("Asus")
                        || p.getFabricante().getNombre().equalsIgnoreCase("Hewlett-Packard")
                        || p.getFabricante().getNombre().equalsIgnoreCase("Seagate"))
                .map(Producto::getNombre)
                .toList();
        listaEspedifica.forEach(System.out::println);
        Assertions.assertEquals(5,listaEspedifica.size());
        Assertions.assertTrue(listaEspedifica.contains("Disco duro SATA3 1TB"));
		//TODO
	}

	/**
	 * 27. Devuelve un listado con el nombre de producto, precio y nombre de fabricante, de todos los productos que tengan un precio mayor o igual a 180€.
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre.
	 * El listado debe mostrarse en formato tabla. Para ello, procesa las longitudes máximas de los diferentes campos a presentar y compensa mediante la inclusión de espacios en blanco.
	 * La salida debe quedar tabulada como sigue:

Producto                Precio             Fabricante
-----------------------------------------------------
GeForce GTX 1080 Xtreme|611.5500000000001 |Crucial
Portátil Yoga 520      |452.79            |Lenovo
Portátil Ideapd 320    |359.64000000000004|Lenovo
Monitor 27 LED Full HD |199.25190000000003|Asus

	 */
	@Test
	void test27() {
		var listProds = prodRepo.findAll();
        System.out.println("Producto                Precio             Fabricante");
        System.out.println("-----------------------------------------------------");
        var listaPro = listProds.stream()
                .filter(p->p.getPrecio()>=180)
                .sorted(comparing((Producto p)->p.getPrecio(),reverseOrder())
                        .thenComparing((Producto p)->p.getNombre()))
                .map(p->String.format("%-22s|%-18s|%s",
                        p.getNombre(),
                        p.getPrecio(),
                        p.getFabricante().getNombre()))
                .toList();

        listaPro.forEach(s-> System.out.println(s));


	}

	/**
	 * 28. Devuelve un listado de los nombres fabricantes que existen en la base de datos, junto con los nombres productos que tiene cada uno de ellos.
	 * El listado deberá mostrar también aquellos fabricantes que no tienen productos asociados.
	 * SÓLO SE PUEDEN UTILIZAR STREAM, NO PUEDE HABER BUCLES
	 * La salida debe queda como sigue:
Fabricante: Asus

            	Productos:
            	Monitor 27 LED Full HD
            	Monitor 24 LED Full HD

Fabricante: Lenovo

            	Productos:
            	Portátil Ideapd 320
            	Portátil Yoga 520

Fabricante: Hewlett-Packard

            	Productos:
            	Impresora HP Deskjet 3720
            	Impresora HP Laserjet Pro M26nw

Fabricante: Samsung

            	Productos:
            	Disco SSD 1 TB

Fabricante: Seagate

            	Productos:
            	Disco duro SATA3 1TB

Fabricante: Crucial

            	Productos:
            	GeForce GTX 1080 Xtreme
            	Memoria RAM DDR4 8GB

Fabricante: Gigabyte

            	Productos:
            	GeForce GTX 1050Ti

Fabricante: Huawei

            	Productos:


Fabricante: Xiaomi

            	Productos:

	 */
	@Test
	void test28() {
		var listFabs = fabRepo.findAll();

        var listaFil = listFabs.stream()
                .map(f-> "Fabricante: "+f.getNombre()+"\n\tProductos: \n\t"+f.getProductos()
                        .stream()
                        .map(p->p.getNombre())
                        .collect(Collectors.joining("\n\t")))
                .toList();

        listaFil.forEach(s-> System.out.println(s));
        Assertions.assertEquals(9,listaFil.size());

		//TODO
	}

	/**
	 * 29. Devuelve un listado donde sólo aparezcan aquellos fabricantes que no tienen ningún producto asociado.
	 */
	@Test
	void test29() {
		var listFabs = fabRepo.findAll();
        var listaFabNoPro = listFabs.stream().filter(x->x.getProductos().isEmpty())
                .map(f->f.getNombre())
                .toList();
        listaFabNoPro.forEach(System.out::println);
        Assertions.assertEquals(2,listaFabNoPro.size());
        Assertions.assertTrue(listaFabNoPro.contains("Huawei"));
		//TODO
	}

	/**
	 * 30. Calcula el número total de productos que hay en la tabla productos. Utiliza la api de stream.
	 */
	@Test
	void test30() {
		var listProds = prodRepo.findAll();

        var total = listProds.stream()
                .count();

        System.out.println("Total: "+total);
        Assertions.assertEquals(11,total);
		//TODO
	}


	/**
	 * 31. Calcula el número de fabricantes con productos, utilizando un stream de Productos.
	 */
	@Test
	void test31() {
		var listProds = prodRepo.findAll();
        var numFab = listProds.stream()
                .mapToInt(p->p.getFabricante().getCodigo())
                .max();
        if(numFab.isPresent()) {
            System.out.println(numFab.getAsInt());
            //TODO
        }
	}


	/**
	 * 32. Calcula la media del precio de todos los productos
	 */
	@Test
	void test32() {
		var listProds = prodRepo.findAll();
        var contarPro = listProds.stream()
                .count();

        var sumaPrecio = listProds.stream()
                .mapToDouble(p->p.getPrecio())
                .sum();

        System.out.println((sumaPrecio/contarPro));
		//TODO
	}

	/**
	 * 33. Calcula el precio más barato de todos los productos. No se puede utilizar ordenación de stream.
	 */
	@Test
	void test33() {
		var listProds = prodRepo.findAll();
		//TODO
	}

	/**
	 * 34. Calcula la suma de los precios de todos los productos.
	 */
	@Test
	void test34() {
		var listProds = prodRepo.findAll();
		//TODO
	}

	/**
	 * 35. Calcula el número de productos que tiene el fabricante Asus.
	 */
	@Test
	void test35() {
		var listProds = prodRepo.findAll();
		//TODO
	}

	/**
	 * 36. Calcula la media del precio de todos los productos del fabricante Asus.
	 */
	@Test
	void test36() {
		var listProds = prodRepo.findAll();
		//TODO
	}


	/**
	 * 37. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos que tiene el fabricante Crucial.
	 *  Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 */
	@Test
	void test37() {
		var listProds = prodRepo.findAll();
		//TODO
	}

	/**
	 * 38. Muestra el número total de productos que tiene cada uno de los fabricantes.
	 * El listado también debe incluir los fabricantes que no tienen ningún producto.
	 * El resultado mostrará dos columnas, una con el nombre del fabricante y otra con el número de productos que tiene.
	 * Ordene el resultado descendentemente por el número de productos. Utiliza String.format para la alineación de los nombres y las cantidades.
	 * La salida debe queda como sigue:

     Fabricante     #Productos
-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
           Asus              2
         Lenovo              2
Hewlett-Packard              2
        Samsung              1
        Seagate              1
        Crucial              2
       Gigabyte              1
         Huawei              0
         Xiaomi              0

	 */
	@Test
	void test38() {
		var listFabs = fabRepo.findAll();
		//TODO
	}

	/**
	 * 39. Muestra el precio máximo, precio mínimo y precio medio de los productos de cada uno de los fabricantes.
	 * El resultado mostrará el nombre del fabricante junto con los datos que se solicitan. Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 * Deben aparecer los fabricantes que no tienen productos.
	 */
	@Test
	void test39() {
		var listFabs = fabRepo.findAll();
		//TODO
	}

	/**
	 * 40. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos de los fabricantes que tienen un precio medio superior a 200€.
	 * No es necesario mostrar el nombre del fabricante, con el código del fabricante es suficiente.
	 */
	@Test
	void test40() {
		var listFabs = fabRepo.findAll();
		//TODO
	}

	/**
	 * 41. Devuelve un listado con los nombres de los fabricantes que tienen 2 o más productos.
	 */
	@Test
	void test41() {
		var listFabs = fabRepo.findAll();
		//TODO
	}

	/**
	 * 42. Devuelve un listado con los nombres de los fabricantes y el número de productos que tiene cada uno con un precio superior o igual a 220 €.
	 * Ordenado de mayor a menor número de productos.
	 */
	@Test
	void test42() {
		var listFabs = fabRepo.findAll();
		//TODO
	}

	/**
	 * 43.Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 */
	@Test
	void test43() {
		var listFabs = fabRepo.findAll();
		//TODO
	}

	/**
	 * 44. Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 * Ordenado de menor a mayor por cuantía de precio de los productos.
	 */
	@Test
	void test44() {
		var listFabs = fabRepo.findAll();
		//TODO
	}

	/**
	 * 45. Devuelve un listado con el nombre del producto más caro que tiene cada fabricante.
	 * El resultado debe tener tres columnas: nombre del producto, precio y nombre del fabricante.
	 * El resultado tiene que estar ordenado alfabéticamente de menor a mayor por el nombre del fabricante.
	 */
	@Test
	void test45() {
		var listFabs = fabRepo.findAll();
		//TODO
	}

	/**
	 * 46. Devuelve un listado de todos los productos que tienen un precio mayor o igual a la media de todos los productos de su mismo fabricante.
	 * Se ordenará por fabricante en orden alfabético ascendente y los productos de cada fabricante tendrán que estar ordenados por precio descendente.
	 */
	@Test
	void test46() {
		var listFabs = fabRepo.findAll();
		//TODO
	}

}
