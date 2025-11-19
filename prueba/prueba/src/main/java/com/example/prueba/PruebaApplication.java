package com.example.prueba;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.Query;

public class PruebaApplication {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Crear un customer

		/*
		 * Pedido p1 = new Pedido();
		 * Producto pr1 = new Producto(1, "Movil", 12);
		 * 
		 * //Almacenar el cliente en la base de datos
		 * 
		 * session.persist(p1);
		 * session.persist(pr1);
		 */
		// Confirmar y cerrar

		while (true) {
			System.out.println("0. Salir");
			System.out.println("1. Crear cliente");
			System.out.println("2. Crear producto");
			System.out.println("3. Crear pedido");
			System.out.println("4. Lista de pedidos");
			System.out.println("5. Actualizar precio producto");
			System.out.println("6. Borrar pedido");

			int eleccion = sc.nextInt();

			if (eleccion == 1) {
				System.out.println("Escriba el nombre:");
				String nombre = sc.next();

				System.out.println("Escriba el apellido:");
				String apellido = sc.next();

				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				session.beginTransaction();

				Customer c1 = new Customer(nombre, apellido);
				session.persist(c1);

				session.getTransaction().commit();
				session.close();

			}

			else if (eleccion == 2) {

				System.out.println("Escriba el nombre del producto:");
				String nombre = sc.nextLine();
				sc.nextLine();

				System.out.println("Escriba el precio:");
				int precio = sc.nextInt();

				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				session.beginTransaction();

				Producto p1 = new Producto(nombre, precio);
				session.persist(p1);

				session.getTransaction().commit();
				session.close();
			}

			else if (eleccion == 3) {

				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();

				System.out.println("Introduce la cantidad del producto: ");
				int cantidad = sc.nextInt();

				System.out.println("Introduce el id del cliente: ");
				int idcliente = sc.nextInt();
				sc.nextLine();

				Customer c = session.get(Customer.class, idcliente);

				System.out.println("Introduce el id del producto: ");
				int idproducto = sc.nextInt();
				sc.nextLine();

				Producto p = session.get(Producto.class, idproducto);

				Double importe = p.getPrecio() * cantidad;

				session.beginTransaction();

				Pedido pedido1 = new Pedido(new Date(), cantidad, importe, c, p);

				session.persist(pedido1);

				session.getTransaction().commit();

			}

			else if (eleccion == 4) {

				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();

				System.out.println("Introduce el id del cliente: ");
				int idcliente = sc.nextInt();
				sc.nextLine();

				List<Pedido> pedidos = session.createQuery(
						"FROM Pedido o WHERE o.customer.id = :id ORDER BY o.fecha DESC",
						Pedido.class)
						.setParameter("id", idcliente)
						.getResultList();

				for (Object obj : pedidos) {
					Pedido o = (Pedido) obj;
					System.out.println(
							"Pedido " + o.getId() +
									" - Producto: " + o.getProducto().getNombre() +
									" - Importe: " + o.getImporte());
				}
			}

			else if (eleccion == 5) {

				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();

				System.out.println("Introduce el id del producto: ");
				int idproducto = sc.nextInt();

				System.out.println("Introduce el precio actualizado: ");
				int precioActualizado = sc.nextInt();

				Query q = session.createQuery("update Producto set precio=:p where id=:idproducto");
				q.setParameter("p", precioActualizado);
				q.setParameter("idproducto", idproducto);
				session.beginTransaction();
				q.executeUpdate();
				session.getTransaction().commit();
			}

			else if (eleccion == 6) {
				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				
				System.out.println("Inserte el id del pedido que quieres borrar: ");
				int idborrarpedido = sc.nextInt();
				Pedido pedido1 = session.get(Pedido.class, idborrarpedido);

				session.delete(pedido1);
				session.beginTransaction();
				session.getTransaction().commit();
			}
		}

	}

}
