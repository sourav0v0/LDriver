# LDriver
Applicatoin to transfer files
# Intro :arrow_right:
  The main intend of this project was to share file to your pc over wifi without using the usb and to create a shared pool for your Home where in family members can share file with each other and can view some of the files directly . But we ended up creating for company rather than Family thus,
  LDrive is used for share files with company employee and all from internet rather than an local file system . Since we cannot download files which have been uploaded to the local file system just because it was for within the network whereas LDrive helps you to do same but here you can also upload download from anywhere.The user can not only upload the file but also he can share the file with certain access privileges which are public and private .public mean the every employee can download it and private mean certain employees can download it and on top of that you can also view files which you have uploaded without downloading it.

# Technologies Used
- Backend  - Java
- Database - MYSQL
# Packages (Modules)
- pojo package
  - basic class of the project the main object for the interaction eg - user , admin , suadmin.
- dao package
  - It contains the interfaces and classes .Where in interfaces contains method(Actions) which an class can perform eg files can be added, removed or search.
  - Classes contains the implentation of those interfaces.
-Controller package
  - It contains the contoller classes which are noting but servlet classes which is use to control the flow.
- util package
  - it contains classes related to things like database connection,Encryption do password,Mail of data

# Design Pattern
- MVC - The MVC is the industry standard patten it was a nice way to implement it
   -M stands for model which is in our case POJO.
   -V stands for view which is our JSP(Java Server Page).
   -C stands for controller which is our Servlet.
   
# Use Case Diagram
![sfs](https://user-images.githubusercontent.com/81374980/124015047-dea35280-da01-11eb-9b40-2b9964f16b4f.png)
