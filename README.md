🚀 Arisan Digital API

🎯 Deskripsi Proyek
Proyek Arisan Digital API adalah backend service yang dirancang untuk mengelola kelompok arisan (tabungan bergilir) secara digital. 
Dibangun menggunakan Spring Boot 3 dengan arsitektur (Clean Architecture / Hexagonal) untuk memisahkan logika bisnis (Domain) dari detail implementasi (Infrastructure).

🛠️ Stack Teknologi
1.Bahasa: Java 17
2.Framework: Spring Boot 3.2+
3.Database: Spring Data JPA (dengan Hibernate)
4.Database Development: H2 Database (In-Memory)
5.Tools: Lombok, Maven

Arsitektur: Domain-Driven Design (DDD) / Clean Architecture

Cara Menjalankan Proyek
Prasyarat
Java Development Kit (JDK) 17 atau lebih tinggi.

Apache Maven 3.6 atau lebih tinggi.

Langkah-Langkah

Bash
git clone [URL_REPO_]

Bash
./mvnw spring-boot:run

Akses Database H2 Console Saat aplikasi berjalan, lo bisa memantau in-memory database di:
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:arisandb
