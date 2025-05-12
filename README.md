### A) Database Connection 

1) Download JDBC for Sql server from : 
https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver16#download

2) extract :  " sqljdbc_12.10.0.0_enu.zip\sqljdbc_12.10\enu\auth\x64\ file.dll "

then put it in : C:programfiles\JDK{version}\bin

-- Optional : 

if the connection fails, 
1) go to SQL Server Configuration mannager

2) enable TCP/IP 

3) restart " SQLServer(MSSQLSERVER) " from (services)
4) Enable javax.mail in bibs (netbeans)