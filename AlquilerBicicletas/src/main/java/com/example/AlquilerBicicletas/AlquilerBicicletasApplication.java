package com.example.AlquilerBicicletas;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.OutputStream;

@SpringBootApplication
public class AlquilerBicicletasApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AlquilerBicicletasApplication.class, args);

		Scanner sc = new Scanner(System.in);

		try {
    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

    server.createContext("/formulario", (HttpExchange exchange) -> {
        String html = "<html><body>" +
                "<h1>Buscar alquileres por usuario</h1>" +
                "<form action='/resultados'>" +
                "ID Usuario: <input name='id'><br>" +
                "<button type='submit'>Buscar</button>" +
                "</form></body></html>";

        exchange.sendResponseHeaders(200, html.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(html.getBytes());
        }
    });

    server.createContext("/resultados", (HttpExchange exchange) -> {
        String query = exchange.getRequestURI().getQuery();
        int id = Integer.parseInt(query.split("=")[1]);

        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();

        List<Alquiler> lista = session.createQuery(
                "FROM Alquiler a WHERE a.id_usuario.id = :id", Alquiler.class)
                .setParameter("id", id)
                .list();

        session.close();

        StringBuilder html = new StringBuilder("<html><body><h1>Resultados</h1>");

        for (Alquiler a : lista) {
            html.append("Alquiler ")
                    .append(a.getId())
                    .append(" - Inicio: ").append(a.getFecha_inicio())
                    .append(" - Fin: ").append(a.getFecha_fin())
                    .append("<br>");
        }

        html.append("</body></html>");

        exchange.sendResponseHeaders(200, html.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(html.toString().getBytes());
        }
    });

    server.start();
    System.out.println("Servidor web iniciado en http://localhost:8080/formulario");
} catch (IOException e) {
    e.printStackTrace();
}


		while (true) {
			System.out.println("==== MENÚ PARA CREAR Y ALQUILAR ====");
			System.out.println("1. Agregar un usuario");
			System.out.println("2. Agregar una estacion");
			System.out.println("3. Agregar una bicicleta");
			System.out.println("4. Alquilar una bicicleta");
			System.out.println("");
			System.out.println("==== MENÚ PARA CONSULTAR ====");
			System.out.println("5. Consultar alquileres");
			System.out.println("");
			System.out.println("==== MENÚ PARA ELIMINAR ALQUILER ====");
			System.out.println("6. Eliminar un alquiler");
			System.out.println("");
			System.out.println("==== MENÚ PARA MODIFICAR ALQUILER ====");
			System.out.println("7. Modificar fecha de alquiler");

			int eleccion = sc.nextInt();

			if (eleccion == 1) {
				System.out.println("Escriba el nombre:");
				String nombre = sc.next();

				System.out.println("Escriba el telefono:");
				int telefono = sc.nextInt();

				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				session.beginTransaction();

				Usuario usuario = new Usuario(nombre, telefono);
				session.persist(usuario);

				session.getTransaction().commit();
				session.close();
			}

			else if (eleccion == 2) {
				System.out.println("Escriba el nombre:");
				String nombre = sc.next();

				System.out.println("Escriba la direccion:");
				String direccion = sc.next();

				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				session.beginTransaction();

				Estacion estacion = new Estacion(nombre, direccion);
				session.persist(estacion);

				session.getTransaction().commit();
				session.close();
			}

			else if (eleccion == 3) {
				System.out.println("Inserte id estacion:");
				int id_estacion = sc.nextInt();

				System.out.println("Estado:");
				String estado = sc.next();

				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				session.beginTransaction();

				Estacion e = session.get(Estacion.class, id_estacion);
				Bicicleta bicicleta = new Bicicleta(e, estado);
				session.persist(bicicleta);

				session.getTransaction().commit();
				session.close();
			}

			else if (eleccion == 4) {
				System.out.println("Inserte id del usuario:");
				int id_usuario = sc.nextInt();

				System.out.println("Inserte el id de la bicicleta:");
				int id_bicicleta = sc.nextInt();

				System.out.println("Fecha inicio:");
				String fechainicio = sc.next();

				System.out.println("Fecha final:");
				String fechaFinal = sc.next();

				System.out.println("Precio:");
				int precio = sc.nextInt();

				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				session.beginTransaction();

				Bicicleta b = session.get(Bicicleta.class, id_bicicleta);
				Usuario u = session.get(Usuario.class, id_usuario);

				Alquiler alquiler = new Alquiler("12", "13", u, b, precio);

				session.persist(alquiler);
				session.getTransaction().commit();
				session.close();
			}

			else if (eleccion == 5) {

				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();

				System.out.println("Introduce el id del usuario: ");
				int idusuario = sc.nextInt();
				sc.nextLine();

				List<Alquiler> pedidos = session.createQuery(
						"FROM Alquiler a WHERE a.id_usuario.id = :id ORDER BY a.fecha_inicio DESC",
						Alquiler.class)
						.setParameter("id", idusuario)
						.getResultList();

				for (Object obj : pedidos) {
					Alquiler al = (Alquiler) obj;
					System.out.println(
							"Alquiler " + al.getId() +
									" - Fecha inicio: " + al.getFecha_inicio() +
									" - Fecha fin: " + al.getFecha_fin());
				}
			}

			else if (eleccion == 6) {
				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();

				System.out.println("Inserte el id del alquiler que quieres borrar: ");
				int idborraralquiler = sc.nextInt();
				Alquiler alquiler = session.get(Alquiler.class, idborraralquiler);

				session.delete(alquiler);
				session.beginTransaction();
				session.getTransaction().commit();
			}

			else if (eleccion == 7) {

				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();

				System.out.println("Introduce el id del alquiler: ");
				int idalquiler = sc.nextInt();

				System.out.println("Introduce fecha inicio actualizada: ");
				String fechainicio = sc.next();

				System.out.println("Introduce fecha fin actualizada: ");
				String fechafin = sc.next();

				Query q = session.createQuery("update Alquiler set fecha_inicio = :fi, fecha_fin = :ff where id = :id");
				q.setParameter("fi", fechainicio);
				q.setParameter("ff", fechafin);
				q.setParameter("id", idalquiler);
				session.beginTransaction();
				q.executeUpdate();
				session.getTransaction().commit();
			}
		}

	}

}
