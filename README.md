Vending Machine Management System using Model View Controller (MVC). 

This project is part of InfoSys course in my university.

I run this project on Windows environment so does below instructions belong to.

To run this project, you need to do several preparations:

1. Install Xampp or WampServer to start web services (apache and mysql) or if you like challenge, you can start the web services through command line.
      <p>For Xampp, you can download it <a href="https://www.apachefriends.org/download.html">here.</a></p>
      <p> For WampServer, you can download it <a href="http://www.wampserver.com/en/">here.</a>
2. Once install and the application launched, start apache and mysql services. 
3. Open the admin site for your mysql services which hosted on your //localhost:3306/ and  create a new database with 'vdms' as its name.
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
            
            </code>
      </pre>
      
      
