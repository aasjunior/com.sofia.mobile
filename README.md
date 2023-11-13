# Sofia Mobile 

<p align="center">
   <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge" #vitrinedev/>
</p>
<br>

## Descrição do projeto 

<p align="justify">
O Sofia Mobile é um aplicativo desenvolvido com Kotlin, Android Studio e Jetpack Compose, projetado para integrar ao Sofia: Software Orientado por Inteligência Artificial para Auxílio ao Pré-diagnóstico de Crianças com Manifestações Comportamentais do Transtorno do Espectro Autista (TEA). O aplicativo será utilizado e integrado ao sistema CAD para auxiliar profissionais a coletar informações sobre os pacientes e na utilização de ferramentas de triagem para melhorar acurácia na identificação de sinais do TEA.
</p>
<br>

## Pré-Requisitos

* JDK (Java Development Kit)
* MySQL
* Git
* Android Studio
* VSCode
<br>

## Dependências

Este projeto depende da seguinte API:

* [Sofia API](https://github.com/aasjunior/com.sofia.restapi.git)
<br>

## Configuração do Projeto

1. Clone o repositório para sua máquina local usando o seguinte comando
```
git clone https://github.com/aasjunior/com.sofia.mobile.git
```

2. Abra o projeto pelo Android Studio

3. Certifique-se de que o JDK está instalado e configurado corretamente

4. Certifique-se de que o MySQL está instalado e funcionando corretamente

5. Configure a API Sofia pelo VS Code. A API estará rodando em `http://localhost:8080`

6. Execute o Emulador do Android Studio
<br>

## Problemas Conhecidos

### Erro de Caracteres Não-ASCII

Se você encontrar um erro relacionado a caracteres não-ASCII durante a execução ou compilação do projeto, existem duas soluções possíveis:

1. **Mover o projeto para um diretório diferente**: Certifique-se de que o novo diretório não contém caracteres não-ASCII no caminho.

2. **Adicionar uma linha ao arquivo gradle.properties**: Você pode adicionar a linha 'android.overridePathCheck=true' ao arquivo gradle.properties no diretório do projeto. Isso desativará a verificação do caminho do projeto.
<br>

## UI
A interface do usuário deste aplicativo foi construída usando **Jetpack Compose** e **Material 3**.

O Jetpack Compose é uma moderna toolkit de UI para Android que simplifica e acelera o desenvolvimento da interface do usuário. Ele permite a criação de interfaces de usuário concisas e idiomáticas com menos código e ferramentas poderosas para visualização de layout.

O Material 3 é a mais recente versão do Material Design, que introduz novos componentes, estilos e recursos para ajudar a criar experiências de usuário mais expressivas e dinâmicas.
<br>

## Tecnologias

<p align="center">
   <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"/>
   <img src="https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white"/>
   <img src="https://img.shields.io/badge/Jetpack%20Compose-FF4081?style=for-the-badge&logo=jetpack&logoColor=white" alt="Jetpack Compose"/>
</p>
