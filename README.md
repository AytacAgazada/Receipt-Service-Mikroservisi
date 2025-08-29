
---

# **Receipt Service Mikroservisi**

`Receipt Service`, dərman reseptlərinin idarə olunması üçün hazırlanmış xidmətdir. Bu mikroservis, vətəndaşlar (citizens) və həkimlər (doctors) tərəfindən yaradılan reseptləri saxlamaq, oxumaq, yeniləmək və silmək funksiyalarını yerinə yetirir.

### **Əsas Xüsusiyyətlər**

* **Reseptlərin yaradılması və idarə edilməsi:** Vətəndaş və həkim ID-lərinə əsaslanaraq yeni reseptlər yaratmaq.
* **Dərmanların əlaqələndirilməsi:** Hər bir reseptə birdən çox dərman (drugs) əlavə etmək.
* **API son nöqtələri:** Reseptləri və dərmanları listələmək, tək bir reseptin məlumatını almaq və s.
* **Validasiya:** Göndərilən məlumatların düzgünlüyünü yoxlamaq (məsələn, son istifadə tarixinin gələcəkdə olması).

### **Texnologiyalar**

* **Java 17**
* **Spring Boot:** Mikroservis memarlığı üçün əsas freymvork.
* **Maven:** Layihənin asılılıqlarını və qurulmasını idarə etmək üçün.
* **Spring Data JPA:** Verilənlər bazası ilə qarşılıqlı əlaqə üçün.
* **PostgreSql Database:** Yaddaşda saxlanılan verilənlər bazası.
* **MapStruct:** DTO və Entity obyektləri arasında avtomatik çevrilmə üçün.
* **Lombok:** Boilerplate kodunu (getter, setter və s.) azaltmaq üçün.
* **Spring Validation:** Məlumatların validasiyası üçün.

### **Layihəni Necə Qurmaq?**

Tətbiq işə düşdükdən sonra, **`http://localhost:9999`** ünvanında əlçatan olacaq.

---

### **`citizenId` və `doctorId` Niyə İstifadə Olunur?**

Bu mikroservis `receipt` (resept) məlumatlarını idarə etdiyi üçün, hər bir reseptin hansı vətəndaşa aid olduğunu və hansı həkim tərəfindən yazıldığını bilmək vacibdir.

* `citizenId` (vətəndaş ID-si) reseptin kimə yazıldığını göstərir.
* `doctorId` (həkim ID-si) resepti yazan həkimi göstərir.

Bu sahələr, **digər mikroservislər** (məsələn, `user-service` və ya `citizen-service`) ilə əlaqə qurmaq üçün açar rolunu oynayır. Hər bir mikroservis öz məsuliyyət sahəsini idarə edir. Bu yanaşma, monolit tətbiqlərdə rast gəlinən `JSON` rekursiv dövrü və məlumatların çoxluğunu aradan qaldırır.

### **Vətəndaş və Həkim Məlumatları Necə Əldə Edilir?**

Bu `receipt-service` mikroservisi yalnız resept məlumatlarını saxlayır. Vətəndaş və ya həkimin **tam məlumatlarını** (ad, soyad, ünvan və s.) almaq üçün aşağıdakı üsullardan biri istifadə olunmalıdır:

1.  **API Zəngi (Microservices)**: Əsas ssenari budur. `receipt-service`, `citizenId` və ya `doctorId` əsasında ayrı bir **`citizen-service`** və ya **`doctor-service`** mikroservisinə **API zəngi** göndərir. Bu xidmətlər ID-yə uyğun olaraq tam məlumatları qaytarır. Məsələn, `GET /api/citizens/{citizenId}` kimi bir sorğu göndərilə bilər.
2.  **Mesajlaşma Növbəsi (Message Queue)**: Daha mürəkkəb sistemlərdə, məlumatlar real vaxtda mesajlaşma platformaları (məsələn, RabbitMQ, Kafka) vasitəsilə sinxronlaşdırıla bilər. Bu halda, `receipt-service` yalnız lazımi `ID` məlumatlarını qeyd edərək yaddaşa qənaət edir.

Bu yanaşma sayəsində hər bir mikroservis öz vəzifəsinə fokuslanmış olur, bu da sistemin daha çevik, asan idarə olunan və miqyaslana bilən olmasına imkan verir.

