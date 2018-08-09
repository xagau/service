# README Service

A herc backend services for price aggregator and feed provider. 

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Support](#support)
- [Contributing](#contributing)

## Installation

Download to your project directory, add `service`:

```sh
git clone https://www.github.com/xagau/service.git
```

```
wget http://mirrors.ibiblio.org/apache/tomcat/tomcat-8/v8.0.9/bin/apache-tomcat-8.0.9.tar.gz
tar xvzf apache-tomcat-8.0.9.tar.gz
mv apache-tomcat-8.0.9 /opt/tomcat
```

Step 2
Step 2: Install Java 7

Before you can use Tomcat you’ll have to install the Java Development Kit (JDK) 7. First let’s check to see if Java is installed:
```
java -version
```

If that returns the following then Java hasn’t yet been installed:
```
The program 'java' can be found in the following packages:
```
To install Java, simply run the following command (and at the prompt enter Y to continue):
```
apt-get install openjdk-7-jdk
```
or
```
yum install openjdk-7-jdk
```
Step 3: Configure .bashrc

Now let’s set the environment variables in .bashrc:
```
vim ~/.bashrc
```

```
export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64
export CATALINA_HOME=/opt/tomcat
```

Now we need to add the cronjob...
Use crontab -e to add the following cron, adjust the frequency to what you like.
This is "poll once per minute."
```
* * * * * ~/cronme.sh >/dev/null 2>&1
```

Contents of cronme.sh, placed in root (change if you like)
```
cd /opt/tomcat/webapps/service-1.0-SNAPSHOT/WEB-INF/classes/
java -cp ".:../lib/gson-2.8.0.jar:../lib/c3p0-0.9.1.2.jar:../lib/mysql-connector-java-8.0.11.jar" com.Aggregator
```
We are using cronme-MINUTE_5.sh, cronme-MINUTE_15.sh, etc that are utilizing the Rake class. 
```
If you need to "rake" price data together, you can use the Rake class to accomplish this.
The Rake class will use computation to produce OHLC values using input MINUTE as the basis.

java -cp ".:../lib/gson-2.8.0.jar:../lib/c3p0-0.9.1.2.jar:../lib/mysql-connector-java-8.0.11.jar" com.Rake MINUTE_5
java -cp ".:../lib/gson-2.8.0.jar:../lib/c3p0-0.9.1.2.jar:../lib/mysql-connector-java-8.0.11.jar" com.Rake MINUTE_15
java -cp ".:../lib/gson-2.8.0.jar:../lib/c3p0-0.9.1.2.jar:../lib/mysql-connector-java-8.0.11.jar" com.Rake MINUTE_30
java -cp ".:../lib/gson-2.8.0.jar:../lib/c3p0-0.9.1.2.jar:../lib/mysql-connector-java-8.0.11.jar" com.Rake HOUR_1 

etc

You will need to create your SQL table
```
 create table history ( symbol varchar (16), source varchar(256), ts datetime, bid double, ask double, volume long, o double, h double, l double, c double, mrange varchar (16) );
 ```

Ideally, add indexes as you see fit. If you are running mysql you will need to adjust wait_timeout and interactive_timeout to avoid heapspace issues.

## Example of usage (HERC) chart
https://chart.anthemgold.com/service-1.0-SNAPSHOT/chart.jsp?symbol=XAU

## Example of usage (HERC) service
https://jsondata.herc.one/service-1.0-SNAPSHOT/HERC

## Usage

Intended to be of use for providing the following services:

- Data Capture (Price Information)
- Price Data Rendering (Charting)
- Installation instructions
- Setup and install apache-tomcat
- Configure cronjob
- Deploy service-1.0-SNAPSHOT war to webapps.
- Create DB tables
- Support instructions
- Contributing instructions

Feel free to remove any sections that aren't applicable to your project.

## Support

Please [open an issue](https://github.com/xagau/service/issues/new) for support.

## Contributing

Please contribute using [Github Flow](https://guides.github.com/introduction/flow/). Create a branch, add commits, and [open a pull request](https://github.com/fraction/readme-boilerplate/compare/).
