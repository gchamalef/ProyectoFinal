# Proyecto Final
### Empresa Web

------------


### Integrantes:
- Diego Fernando Velásquez Pichilla, 7690-16-3882
- Ana María Montufar Aguirre, 7691-18-26253
- Luis Enrique Sazo Rosales, 7691-21-1778
- Cristhian Alejandro Barrientos Moya, 7691-20-3889
- Guillermo Sebastian Chamalé Flores, 7691-21-7580

------------


## Lineamientos:
1. Crear una base de datos en **mysql**

2. Crear el mantenimiento web (CRUD) de la tabla **Puestos**.

3. Crear el mantenimiento web (CRUD) de la tabla **Empleados** la cual deberá de mostrar un combo con los **puestos** de la tabla **Puestos** y un hipervínculo que redirecciones al mantenimiento de **Puestos** y viceversa.

4. Crear el mantenimiento web (CRUD) de la tabla **Clientes**.

5. Crear el mantenimiento web (CRUD) de la tabla **Proveedores**.

6. Crear el mantenimiento web (CRUD) de la tabla **Marcas**.

7. Crear el mantenimiento web (CRUD) de la tabla **Productos** el cual deberá de mostrar un combo con las **marcas** de la tabla **Marcas** y un hipervínculo que redirecciones al mantenimiento de **Marcas** y viceversa. Este mantenimiento deberá de permitir Guardar una **IMAGEN** del producto en el servidor,(no en la base de datos ahí solo deberá de estar la URL de la IMAGEN) y cuando se realice una búsqueda del producto esta deberá demostrar la imagen almacenada.

8. Crear un mantenimiento web (CRUD) de tipo **MAESTRO DETALLE** de las tablas **Ventas y Ventas_Detalle**, es decir en un solo mantenimiento se deberá de guardar en las dos tablas. El mantenimiento deberá de mostrar un combo con los nombres y nit de los **clientes** de la tabla **Clientes** y un hipervínculo que redirecciones al mantenimiento de **Clientes** y viceversa. El mantenimiento deberá de mostrar un combo con los nombres de los **empleados** de la tabla **Empleados** y un hipervínculo que redirecciones al mantenimiento de **Empleados** y viceversa. Cuando se ingrese una venta el saldo del producto de la tabla **Producto** deberá de disminuir.

9. Crear un mantenimiento web (CRUD) de tipo **MAESTRO DETALLE** de las tablas **Compras y Compras_Detalle**, es decir en un solo mantenimiento se deberá de guardar en las dos tablas. El mantenimiento deberá de mostrar un combo con los nombres de los **proveedores** de la tabla **Proveedores** y un hipervínculo que redirecciones al mantenimiento de **Proveedores** y viceversa. Cuando se ingrese una compra el saldo del producto de la tabla **Producto** deberá de aumentar y el precio_costo deberá de actualizarse, así como el precio_venta pero este con un 25% más del precio_costo.

10. Deberá de crear un login para ingresar a la aplicación (Crear una tabla en la base de datos usuarios para almacenar el usuario y contraseña).

11. Deberá de crear un menú principal **DINAMICO** (Crear una tabla en la base de Datos para los menús) por medio de Arboles con la siguiente estructura.
	1. Productos
		1.1. Marcas
	2. Ventas
		2.1. Clientes
		2.2. Empleados
		2.2.1. Puestos
	3. Compras
		3.1. Proveedores
	4. Reportes

12. Todos los mantenimientos deberán de llevar las validaciones básicas para el ingreso de datos.

13. Crear como mínimo 5 reportes básicos con JasperReports u otra alternativa a su elección.

------------

## Base de datos:
```sql
create database proyecto_db;
use proyecto_db;

create table clientes(
id_cliente int primary key not null auto_increment,
nombres varchar(60),
apellidos varchar(60),
nit varchar(12),
genero bit,
telefono varchar(25),
correo_electronico varchar(45),
fecha_ingreso datetime
);

create table puestos(
id_puesto smallint not null primary key auto_increment,
puesto varchar(50)
);

create table proveedores(
id_proveedor int not null primary key auto_increment,
proveedor varchar(60),
nit varchar(12),
direccion varchar(80),
telefono varchar(25)
);

create table marcas(
id_marca smallint not null auto_increment primary key,
marca varchar(50)
);

create table empleados(
id_empleado int not null primary key auto_increment,
nombres varchar(60),
apellidos varchar(60),
direccion varchar(80),
telefono varchar(25),
dpi varchar(15),
genero bit,
fecha_nacimiento date,
id_puesto smallint not null,
fecha_inicio_labores date,
fecha_ingreso datetime,
constraint fk_empleados_id_puesto foreign key (id_puesto) references puestos(id_puesto)
);

create table productos(
id_producto int not null auto_increment primary key,
producto varchar(50),
id_marca smallint not null,
descripcion varchar(100),
imagen varchar(30),
precio_costo decimal(8,2),
precio_venta decimal(8,2),
existencia int,
fecha_ingreso datetime,
constraint fk_productos_id_marca foreign key (id_marca) references marcas(id_marca)
);

create table compras(
id_compra int not null auto_increment primary key,
no_orden_compra int,
id_proveedor int not null,
fecha_orden date,
fecha_ingreso datetime,
constraint fk_compras_id_proveedor foreign key (id_proveedor) references proveedores(id_proveedor) 
);

create table ventas(
id_venta int not null auto_increment primary key,
no_factura int,
serie char(1),
fecha_factura date,
id_cliente int not null,
id_empleado int not null,
fecha_ingreso datetime,
constraint fk_ventas_id_cliente foreign key (id_cliente) references clientes(id_cliente),
constraint fk_ventas_id_empleado foreign key (id_empleado) references empleados(id_empleado)
);

create table ventas_detalle(
id_venta_detalle bigint not null auto_increment primary key,
id_venta int not null,
id_producto int not null,
cantidad varchar(45),
precio_unitario decimal(8,2),
constraint fk_ventas_detalle_id_venta foreign key (id_venta) references ventas(id_venta),
constraint fk_ventas_detalle_id_producto foreign key (id_producto) references productos(id_producto)
);

create table compras_detalle(
id_compra_detalle bigint not null auto_increment primary key,
id_compra int not null,
id_producto int not null,
cantidad int,
precio_costo_unitario decimal(8,2),
constraint fk_compras_detalle_id_compra foreign key (id_compra) references compras(id_compra),
constraint fk_compras_detalle_id_producto foreign key (id_producto) references productos(id_producto)
);
```

------------
