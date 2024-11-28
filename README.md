# Sofia Mobile 💜

Sofia é um Software Orientado por Inteligência Artificial para Auxílio ao Pré-diagnóstico de Crianças de 0 a 2 Anos com Manifestações Comportamentais do Transtorno do Espectro Autista (TEA). O aplicativo mobile CAD (_Computer-aided Diagnosis_) é destinado ao auxílio de profissionais da saúde na triagem e identificação de sinais do TEA. <strong> 💜 Nosso Objetivo 💜 </strong> é promover a acessibilidade ao diagnóstico precoce de TEA! 💜

<div align="center">
  <img src="https://github.com/aasjunior/com.sofia.mobile/assets/85968113/ce5ba98e-c63a-4fb7-a311-ced454084bc7" width="700" alt="ilustracao">
</div>

###### FETEPS 2024
Este projeto participou da **15ª Feira Tecnológica do Centro Paula Souza** - <a href='https://feteps.cpscetec.com.br/docs/2024_feteps_publicacao.pdf' target='_blank'>FETEPS</a>.

### Descrição do projeto 💜

<p align="justify">
O Sofia Mobile é um aplicativo desenvolvido com Kotlin, Android Studio e Jetpack Compose, com banco de dados Mongodb.
</p>

<p align="center">
  <img src="https://github.com/aasjunior/com.sofia.mobile/assets/85968113/35a123c0-4e46-4803-b91a-3e306a18d657" width="200" alt="logo">
  <img src="https://github.com/aasjunior/com.sofia.mobile/assets/85968113/1aa4def9-496e-4285-bccc-a16f2b3a5ae8" width="200" alt="bem vindo">
   
  <img src="https://github.com/aasjunior/com.sofia.mobile/assets/85968113/55f28fab-5d3f-4fb3-9921-4d94e592be53" width="200" alt="home">
  <img src="https://github.com/aasjunior/com.sofia.mobile/assets/85968113/662fb921-1500-419c-92ba-f3a8b3ef59e2" width="200" alt="cadastro">
</p>
<br>

Ficou interessado? Veja mais no nosso [pitch](https://www.youtube.com/watch?v=wSeBx_eXvcY) de apresentação da SOFIA, ou acesse o nosso [site](https://sofia-aja.vercel.app/). 💜


## Pré-Requisitos 💜

* JDK (Java Development Kit)
* MongoDB 
* Git
* Android Studio
* VSCode
<br>

## Dependências 💜

Este projeto depende das seguintes APIs:

* [Sofia API](https://github.com/aasjunior/com.sofia.restapi.git)
* [Sofia api-flask (Neural Network)](https://github.com/mandis-ncs/api-flask.git)
  <br>

## Configuração do Projeto 💜

1. Clone o repositório para sua máquina local usando o seguinte comando
```
git clone https://github.com/aasjunior/com.sofia.mobile.git
```

2. Abra o projeto pelo Android Studio

3. Certifique-se de que o JDK está instalado e configurado corretamente

4. Certifique-se de que o Mongodb está instalado e funcionando corretamente

5. Configure a API Sofia pelo VS Code ou IDE de sua preferência. A API estará rodando em `http://localhost:8080`

6. Execute o Emulador do Android Studio

### Provisionamento do back-end com Docker Compose 💜

##### Requisitos
- **Docker**
- **Docker Compose**

Para provisionar o back-end completo do **SOFIA**:

1. Clone o repositório da [Sofia API](https://github.com/aasjunior/com.sofia.restapi.git) para sua máquina local usando o seguinte comando
```
git clone https://github.com/aasjunior/com.sofia.restapi.git
```

1. Navegue até o diretório `/infra`:

```bash
cd infra
```

2. Execute o comando para subir todos os serviços (API, FastAPI e NGINX):

```bash
docker-compose up
```

## Sofia-Server 💜

Para provisionar um servidor **Ubuntu** e configurar toda a infraestrutura do SOFIA, consulte a [documentação do Sofia-Server](https://github.com/aasjunior/com.sofia.restapi/tree/main/infra/README.md).

Esta documentação inclui:

- Passos para criar um servidor Ubuntu na AWS ou localmente.
- Instalação do **Docker** e **Docker Compose**.
- Execução do ambiente completo de back-end utilizando o arquivo `docker-compose.yml`, disponívem em [infra/docker-compose.yml](https://github.com/aasjunior/com.sofia.restapi/blob/main/infra/docker-compose.yml).

## Problemas Conhecidos 💜

### Erro de Caracteres Não-ASCII 

Se você encontrar um erro relacionado a caracteres não-ASCII durante a execução ou compilação do projeto, existem duas soluções possíveis:

1. **Mover o projeto para um diretório diferente**: Certifique-se de que o novo diretório não contém caracteres não-ASCII no caminho.

2. **Adicionar uma linha ao arquivo gradle.properties**: Você pode adicionar a linha 'android.overridePathCheck=true' ao arquivo gradle.properties no diretório do projeto. Isso desativará a verificação do caminho do projeto.
<br>

## UI
A interface do usuário deste aplicativo foi construída usando **Jetpack Compose** e **Material 3**.

O Jetpack Compose é uma moderna toolkit de UI para Android que simplifica e acelera o desenvolvimento da interface do usuário. Ele permite a criação de interfaces de usuário concisas e idiomáticas com menos código e ferramentas poderosas para visualização de layout.

<div>O Material 3 é a mais recente versão do Material Design, que introduz novos componentes, estilos e recursos para ajudar a criar experiências de usuário mais expressivas e dinâmicas.
<br> </div>

## Tecnologias 💜
<p align="center">
   <img src="https://github.com/aasjunior/com.sofia.mobile/assets/61213599/db90a6e0-3c46-4891-ad39-8405d499bea9" width="600px" alt="Android"/>
<div align="center"><img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"/>
   <img src="https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white"/>
   <img src="https://img.shields.io/badge/Jetpack%20Compose-FF4081?style=for-the-badge&logo=jetpack&logoColor=white" alt="Jetpack Compose"/></div>
</p>

## Nosso Time AJA 💜
You can see more about us in our profile:
* [Amanda](https://github.com/mandis-ncs)
* [Junior](https://github.com/aasjunior)
* [Aline](https://github.com/AlineLauriano)

###### Aviso
Esta é uma iniciativa acadêmica, sendo assim, não possui todas as funcionalidades e características de uma aplicação real.
