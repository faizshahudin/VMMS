Vending Machine Management System using Model View Controller (MVC). 

This project is part of InfoSys course in my university.

I run this project on Windows environment so does below instructions belong to.

To run this project, we need to do several preparations:

1. Install Xampp or WampServer to start web services (apache and mysql) or if you like challenge, you can start the web services through command line.
      <p>For Xampp, you can download it <a href="https://www.apachefriends.org/download.html">here.</a></p>
      <p> For WampServer, you can download it <a href="http://www.wampserver.com/en/">here.</a>
2. Once install and the application launched, start apache and mysql services. 
3. Open the admin site for our mysql services which hosted on our local mysql server //localhost:3306/ and  create a new database with 'vdms' as its name.
4. Create 3 tables which are product, money and sales
      <p> For product:</p> 
      <pre>
            <code>
            CREATE TABLE `vdms`.`product` ( `productID` INT NOT NULL AUTO_INCREMENT , `name` VARCHAR(225) NOT NULL , `price` DOUBLE NOT NULL , `productQuantity` INT NOT NULL , PRIMARY KEY (`productID`)) ENGINE = InnoDB;
            </code>
      </pre>
      <p> Then, populate the table with reasonable information.</p>
      <p> For money: </p>
      <pre>
            <code>
            CREATE TABLE `vdms`.`money` ( `moneyID` INT NOT NULL AUTO_INCREMENT , `moneyQuantity` INT NOT NULL , `value` INT NOT NULL , PRIMARY KEY (`moneyID`)) ENGINE = InnoDB;
            </code>
      </pre>
      <p> Then, insert a reasonable coin like 10cent, 20cent, 50cent and 1 dollar.</p>
      <p>For sale:</p>
      <pre>
            <code>
            CREATE TABLE `vdms`.`sale` ( `saleID` INT NOT NULL AUTO_INCREMENT , `productID` INT NOT NULL , `saleQuantity` INT NOT NULL , `amount` DOUBLE NOT NULL , `saleTime` TIMESTAMP NOT NULL , PRIMARY KEY (`saleID`)) ENGINE = InnoDB;
            </code>
      </pre>
      <p>For sale table, just let it be, we don't need to populate it. Everytime user make a purchase the system will automatically update the table.</p>
5. Last thing we need is the java mysql connector. This java library acts like a bridge between our java application and our local mysql server.
      <p>You can download the connector <a href="https://dev.mysql.com/downloads/connector/j/5.1.html">here.</a></p>
      <p>Lastly, make sure you only take the .jar file and add it to external library in your project environment.</p>
      
      
