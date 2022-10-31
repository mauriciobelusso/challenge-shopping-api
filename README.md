# Challenge-Shopping-Api

## Requisites

* Windows (this readme was elaborated using Windows, but if you use another OS then skip to step Java OpenJDK 8+)
* Chocolatey
* Java OpenJDK 8+
* IDE with support for Java projects
* Maven

## Installation

### Chocolatey

#### Prerequisites

* powershell

#### Installation

Install Chocolatey for Individual Use:
First, ensure that you are using an administrative shell - you can also install as a non-admin, check out Non-Administrative Installation.
Install with powershell.exe

📝 NOTE: Please inspect https://community.chocolatey.org/install.ps1 prior to running any of these scripts to ensure safety. We already know it's safe, but you should verify the security and contents of any script from the internet you are not familiar with. All of these scripts download a remote PowerShell script and execute it on your machine. We take security very seriously. Learn more about our security protocols.
With PowerShell, you must ensure Get-ExecutionPolicy is not Restricted. We suggest using Bypass to bypass the policy to get things installed or AllSigned for quite a bit more security.

Run Get-ExecutionPolicy. If it returns Restricted, then run Set-ExecutionPolicy AllSigned or Set-ExecutionPolicy Bypass -Scope Process.
Now run the following command:

>Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))

Paste the copied text into your shell and press Enter.
Wait a few seconds for the command to complete.
If you don't see any errors, you are ready to use Chocolatey! Type choco or choco -? now, or see Getting Started for usage instructions.

### Java OpenJdk8

#### Installation

Open your shell as administrator and type the following command

> choco install openjdk8

### IDE

You can use your prefered IDE as Eclipse, IntelliJ, NetBeans and others

### Maven

This is a tool for manage your dependencies, build your apps, run unit tests and etc...

You don`t need install this tool

### Project

#### Installation

In the most of IDEs you just need import project as a maven project, so the IDE download dependencies using integrated maven.

To run this project, execute ChallengeShoppingApiApplication.java as a Java Application, this project use embbeded tomcat and provides a simple way to start this project.

For pack this project, run the following command 

> mvn install 

The tests will run and build project to target