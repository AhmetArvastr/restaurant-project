# Eureka Servisi

[İngilizce README'ye git](README.MD)

Eureka Servisi, mikroservis mimarimizin anahtar bir bileşenidir. Mikroservislerin kaydını sağlar ve hizmet keşfi sağlar, bu da hizmetlerin yük dengelemesi ve failover için önemlidir.


## Ana Özellikler

- Tüm mikroservisleri kaydeder.
- Yük dengelemesi ve failover için hizmet keşfini sağlar.
- Spring Boot ve Eureka ile entegre olur.

## Yapılandırma

Eureka Servisi yapılandırması `application.yml` dosyasında bulunur. Bu dosya, servisin hangi portta çalışacağını ve Eureka istemci yapılandırmasını belirtir.



## Docker

Bu servis, Docker'da çalışacak şekilde paketlenmiştir. Dockerfile, Docker imajının nasıl oluşturulacağını belirtir.

## Çalıştırma

Docker imajını oluşturmak ve çalıştırmak için aşağıdaki komutları kullanabilirsiniz:

```bash
docker build -t discovery-service .
docker run -p 8761:8761 discovery-service
```

Bu komutlar önce Docker imajını oluşturur ve ardından eureka-service'i 8761 portunda çalıştırır.

## Bağımlılıklar

- **Spring Boot:** Eureka Servisi, Spring Boot kullanılarak oluşturulmuştur.
- **Eureka Server:** Eureka Servisi, hizmet kaydı ve keşfi için Eureka Server'ı kullanır.

Lütfen tüm mikroservislerin hizmet keşfinin doğru bir şekilde çalışması için Eureka Servisi'ne kaydolması gerektiğini unutmayın.

## İletişim

### Seyid Ahmet ARVAS

<a href="https://github.com/ahmetarvastr" target="_blank">
<img  src=https://img.shields.io/badge/github-%2324292e.svg?&style=for-the-badge&logo=github&logoColor=white alt=github style="margin-bottom: 20px;" />
</a>
<a href = "mailto:example@gmail.com?subject = Geri Bildirim&body = Mesaj">
<img src=https://img.shields.io/badge/send-email-email?&style=for-the-badge&logo=microsoftoutlook&color=CD5C5C alt=gmail style="margin-bottom: 20px; margin-left:20px" />
</a>
<a href="https://linkedin.com/in/seyidahmetarvas" target="_blank">
<img src=https://img.shields.io/badge/linkedin-%231E77B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white alt=linkedin style="margin-bottom: 20px; margin-left:20px" />
</a>  

## Seyid Ahmet ARVAS - Restaurant Project

<div align="center">
<img src="../img/java.png" alt="Logo" width="220" height="140">
<h3 align="center">Java</h3>
</div>

<div align="center">
<img src="../img/spring.png" alt="Logo" width="220" height="140">
<h3 align="center">SpringBoot</h3>   
</div>
