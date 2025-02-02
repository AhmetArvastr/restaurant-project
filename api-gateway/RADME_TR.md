
# API Gateway Servisi

[İngilizce README'ye git](README.md)

API Gateway, microservice mimarimizdeki tüm servislerin merkezi bir giriş noktasıdır. Bu servis, gelen istekleri ilgili mikroservislere yönlendirir.

## Başlıca Özellikler

- Restaurant ve User servislerine yönlendirme yapar.
- Eureka Server ile entegre çalışır.
- Zipkin ile izleme/tracing desteği sağlar.

## Yapılandırma

`application.yml` dosyasında API Gateway'in yapılandırması bulunur. Bu dosya, servisin hangi portta çalışacağını, hangi servislere yönlendirme yapacağını ve Eureka ve Zipkin ile nasıl entegre olacağını belirler.

## Docker

Bu servis, Docker üzerinde çalışacak şekilde paketlenmiştir. `Dockerfile` dosyası, Docker imajının nasıl oluşturulacağını belirtir.

## Çalıştırma

Docker imajını oluşturmak ve çalıştırmak için aşağıdaki komutları kullanabilirsiniz:

```bash
docker build -t api-gateway .
docker run -p 8080:8080 api-gateway
```

Bu komutlar, öncelikle Docker imajını oluşturur ve ardından `api-gateway` servisini 8080 portunda çalıştırır.

## Bağımlılıklar

- Restaurant Service: API Gateway, Restaurant Service'e `/api/v1/restaurants/**` path'indeki istekleri yönlendirir.
- User Service: API Gateway, User Service'e `/api/v1/users/**` path'indeki istekleri yönlendirir.
- Eureka Service: API Gateway, servis keşfi için Eureka Discovery Server ile entegre çalışır.
- Zipkin: API Gateway, izleme/tracing için Zipkin ile entegre çalışır.

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
